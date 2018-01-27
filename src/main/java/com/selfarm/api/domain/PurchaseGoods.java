package com.selfarm.api.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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
@Table(name="purchase_goods")
public class PurchaseGoods implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long pgid;

    @Column
    private Long sgid; // Foreign key

    @Column
    private String nickname;

    @Column
    private String status;

    @Column
    private int purchase_amount;

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date purchase_date;

    @Column
    private String image;

    public static PurchaseGoods build(Long sgid, String nickname, String status, int purchase_amount, Date purchase_date, String image) {
        return PurchaseGoods.builder()
                .sgid(sgid)
                .nickname(nickname)
                .status(status)
                .purchase_amount(purchase_amount)
                .purchase_date(purchase_date)
                .image(image)
                .build();
    }

    public static PurchaseGoods build(PurchaseGoods purchaseGoods) {
        return PurchaseGoods.builder()
                .sgid(purchaseGoods.sgid)
                .nickname(purchaseGoods.nickname)
                .status(purchaseGoods.status)
                .purchase_amount(purchaseGoods.purchase_amount)
                .purchase_date(purchaseGoods.purchase_date)
                .image(purchaseGoods.image)
                .build();
    }
}
