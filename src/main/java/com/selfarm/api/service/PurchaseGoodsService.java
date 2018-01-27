package com.selfarm.api.service;

import com.selfarm.api.domain.PurchaseGoods;
import com.selfarm.api.domain.SaleGoods;
import com.selfarm.api.dto.DetailResponse;
import com.selfarm.api.persistence.PurchaseGoodsRepository;
import com.selfarm.api.persistence.SaleGoodsRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.xml.soap.Detail;
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by ByeongChan on 2018. 1. 27..
 */

@Service
public class PurchaseGoodsService {

    @Autowired
    PurchaseGoodsRepository purchaseGoodsRepository;

    @Autowired
    SaleGoodsRepository saleGoodsRepository;

    @Value("${weatherApi.key}")
    private String key;

    @Value("${weatherApi.uriBase}")
    private String uriBase;

    public void save(PurchaseGoods purchaseGoods) {
        purchaseGoodsRepository.save(purchaseGoods);
    }

    public List<PurchaseGoods> findAll() {
        return purchaseGoodsRepository.findAll();
    }

    public DetailResponse goodsDetail(PurchaseGoods goods) throws IOException{
        SaleGoods saleGoods = saleGoodsRepository.findBySgid(goods.getSgid());

        long calDate = saleGoods.getHarvest_date().getTime() - goods.getPurchase_date().getTime();
        long calDateDays = calDate / (24 * 60 * 60 * 1000);

        int dday = (int)(Math.abs(calDateDays));

        JSONObject jsonObject = readJsonFromUrl(uriBase + key);

        String tempParam;
        String weatherParam;
        String ddayParam = Integer.toString(dday);

        int temp = jsonObject.getJSONObject("main").getInt("temp") - 273;

        if(temp < 0){
            tempParam = "0C";
        } else if(temp < 10){
            tempParam = "10C";
        } else if(temp < 20){
            tempParam = "20C";
        } else{
            tempParam = "30C";
        }

        JSONArray jsonArray = jsonObject.getJSONArray("weather");
        JSONObject tempObject = jsonArray.getJSONObject(0);
        weatherParam = tempObject.getString("main");

        ddayParam = "D" + ddayParam;

        List<String> voiceTextList;

        if(dday == 0 || dday == 1 || dday == 50 || dday == 100){
            voiceTextList = purchaseGoodsRepository.findByStatus(weatherParam, tempParam, ddayParam);
        } else{
            voiceTextList = purchaseGoodsRepository.findByStatus(weatherParam, tempParam, null);
        }

        DetailResponse detailResponse = DetailResponse.build(dday, voiceTextList);

        return detailResponse;
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
