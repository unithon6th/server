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
@Table(name="sale_goods")
public class SaleGoods implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long sgid;

    @Column
    private String name;

    @Column
    private int sales_price;

    @Column
    private int sales_amount;

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date harvest_date;

    @Column
    private String image;

    public static SaleGoods build(String name, int sales_price, int sales_amount, Date harvest_date, String image) {
        return SaleGoods.builder()
                .name(name)
                .sales_price(sales_price)
                .sales_amount(sales_amount)
                .harvest_date(harvest_date)
                .image(image)
                .build();
    }

    public static SaleGoods build(SaleGoods saleGoods) {
        return SaleGoods.builder()
                .name(saleGoods.name)
                .sales_price(saleGoods.sales_price)
                .sales_amount(saleGoods.sales_amount)
                .harvest_date(saleGoods.harvest_date)
                .image(saleGoods.image)
                .build();
    }
}
