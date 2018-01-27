package com.selfarm.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by ByeongChan on 2018. 1. 28..
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetailResponse {

    private int dday;

    private List<String> voiceTextList;

    public static DetailResponse build(int dday, List<String> voiceTextList) {
        return DetailResponse.builder()
                .dday(dday)
                .voiceTextList(voiceTextList)
                .build();
    }
}
