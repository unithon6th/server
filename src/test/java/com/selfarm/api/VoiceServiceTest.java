package com.selfarm.api;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

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

    @Test
    public void weatherTest() throws Exception{
        JSONObject jsonObject = readJsonFromUrl(uriBase + key);
        JSONObject jsonObject1 = jsonObject.getJSONObject("main");

        int temp = jsonObject1.getInt("temp");
        //273.15
        System.out.println(temp - 273);
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
