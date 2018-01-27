package com.selfarm.api.service;

import com.selfarm.api.domain.PurchaseGoods;
import com.selfarm.api.domain.SaleGoods;
import com.selfarm.api.domain.Voice;
import com.selfarm.api.persistence.SaleGoodsRepository;
import com.selfarm.api.persistence.VoiceRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by ByeongChan on 2018. 1. 27..
 */

@Service
public class VoiceService {

    @Autowired
    VoiceRepository voiceRepository;

    @Autowired
    SaleGoodsRepository saleGoodsRepository;

    @Value("${weatherApi.key}")
    private String key;

    @Value("${weatherApi.uriBase}")
    private String uriBase;

    public void save(Voice voice){ voiceRepository.save(voice); }

    public String textSampling(PurchaseGoods goods) throws Exception{
        SaleGoods saleGoods = saleGoodsRepository.findBySgid(goods.getSgid());

        long calDate = saleGoods.getHarvest_date().getTime() - goods.getPurchase_date().getTime();
        long calDateDays = calDate / (24 * 60 * 60 * 1000);

        int dday = (int)(Math.abs(calDateDays));
        int random;

        if(dday == 0 || dday == 1 || dday == 50 || dday == 100){
            random = (int)(Math.random() * 3);
        } else{
            random = (int)(Math.random() * 2);
        }

        String voiceText = null;

        switch (random){
            case 1:
                voiceText = weatherText();
                break;

            case 2:
                voiceText = tempText();
                break;

            case 3:
                voiceText = DdayText(dday);
                break;

            default:
                break;
        }

        return voiceText;
    }

    public String tempText() throws IOException{
        JSONObject jsonObject = readJsonFromUrl(uriBase + key).getJSONObject("main");

        int temp = jsonObject.getInt("temp") - 273; // 절대 온도 데이터이므로 -273
        // System.out.println(temp - 273);
        String statusParam;

        if(temp < 0){
            statusParam = "0C";
        } else if(temp < 10){
            statusParam = "10C";
        } else if(temp < 20){
            statusParam = "20C";
        } else{
            statusParam = "30C";
        }

        Voice voice = voiceRepository.findByStatus(statusParam);

        return voice.getVoicetext();
    }

    public String weatherText() throws IOException{
        JSONObject jsonObject = readJsonFromUrl(uriBase + key);
        JSONArray jsonArray = jsonObject.getJSONArray("weather");

        JSONObject tempObject = jsonArray.getJSONObject(0);
        String weather = tempObject.getString("main");

        String statusParam = weather;

        Voice voice = voiceRepository.findByStatus(statusParam);

        return voice.getVoicetext();
    }

    public String DdayText(int dday){
        String statusParam = "D" + Integer.toString(dday);

        Voice voice = voiceRepository.findByStatus(statusParam);

        if(voice.getVoicetext() == null){

        }

        return voice.getVoicetext();
    }

    private JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();

        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    private String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
}
