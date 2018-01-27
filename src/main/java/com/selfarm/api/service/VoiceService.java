package com.selfarm.api.service;

import com.selfarm.api.domain.Voice;
import com.selfarm.api.persistence.VoiceRepository;
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

    @Value("${weatherApi.key}")
    private String key;

    @Value("${weatherApi.uriBase}")
    private String uriBase;

    public void save(Voice voice){ voiceRepository.save(voice); }

    public void weatherTest() throws Exception{
        JSONObject jsonObject = readJsonFromUrl(uriBase + key);
        JSONObject jsonObject1 = jsonObject.getJSONObject("main");

        int temp = jsonObject1.getInt("temp");
        System.out.println(temp - 273);
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
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
