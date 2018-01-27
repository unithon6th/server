package com.selfarm.api;

import com.selfarm.api.domain.PurchaseGoods;
import com.selfarm.api.domain.Voice;
import com.selfarm.api.persistence.VoiceRepository;
import com.selfarm.api.service.VoiceService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.StringTokenizer;

/**
 * Created by ByeongChan on 2018. 1. 27..
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class VoiceServiceTest {

    @Value("${weatherApi.key}")
    private String key;

    @Value("${weatherApi.uriBase}")
    private String uriBase;

    @Autowired
    VoiceRepository voiceRepository;

    @Autowired
    VoiceService voiceService;

    @Test
    public void textSamplingTest(){
    }

    @Test
    public void dummySave() throws Exception{
        run("/Users/ByeongChan/dummyData.txt", "euc-kr");
    }

    private void run(String path, String encoding) {
        BufferedReader br = null;
        String line;
        String splitby = ",";

        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(path), encoding));
            while ((line = br.readLine()) != null) {
                String[] field = line.split(splitby);

                voiceRepository.save(Voice.build(field[0], field[1]));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void weatherTest() throws Exception{
        JSONObject jsonObject = readJsonFromUrl(uriBase + key);
        JSONArray jsonArray = jsonObject.getJSONArray("weather");

        JSONObject temp = jsonArray.getJSONObject(0);
        String tempString = temp.getString("main");

        System.out.println(tempString);
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException{
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

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
}
