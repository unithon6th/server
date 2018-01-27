package com.selfarm.api.domain;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * Created by ByeongChan on 2018. 1. 27..
 */

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(value = { AuditingEntityListener.class })
@Table(name="voice")
public class Voice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @Column
    private String status;

    @Column
    private String voicetext;

    public static Voice build(String status, String voicetext) {
        return Voice.builder()
                .status(status)
                .voicetext(voicetext)
                .build();
    }

    public static Voice build(Voice voice) {
        return Voice.builder()
                .status(voice.status)
                .voicetext(voice.voicetext)
                .build();
    }
}
