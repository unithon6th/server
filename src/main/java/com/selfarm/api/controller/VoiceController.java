package com.selfarm.api.controller;

import com.selfarm.api.domain.PurchaseGoods;
import com.selfarm.api.domain.Voice;
import com.selfarm.api.service.VoiceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by ByeongChan on 2018. 1. 27..
 */

@CrossOrigin(origins = "*")
@Api(value = "voice API", description = "voice API", basePath = "/api/v1/voice")
@RestController
@RequestMapping("/api/v1/voice")
public class VoiceController {

    @Autowired
    VoiceService voiceService;

    @ApiOperation(value = "save", notes = "save voice")
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String saveVoice(@RequestBody Voice voice){
        try {
            voiceService.save(Voice.build(voice));
            return "save success";
        } catch (Exception e) {
            e.printStackTrace();
            return "save fail";
        }
    }

    @ApiOperation(value = "voice text", notes = "return random voice text")
    @RequestMapping(value = "voiceText", method = RequestMethod.POST)
    public String voicePOST(@RequestBody PurchaseGoods goods){
        String voiceText = null;

        try{
            voiceText = voiceService.textSampling(goods);
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }

        return voiceText;
    }
}
