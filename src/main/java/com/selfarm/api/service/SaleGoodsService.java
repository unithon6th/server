package com.selfarm.api.service;

import com.selfarm.api.domain.SaleGoods;
import com.selfarm.api.persistence.SaleGoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ByeongChan on 2018. 1. 27..
 */

@Service
public class SaleGoodsService {

    @Autowired
    SaleGoodsRepository saleGoodsRepository;

    public void save(SaleGoods saleGoods) {
        saleGoodsRepository.save(saleGoods);
    }

    public List<SaleGoods> findAll() {
        return saleGoodsRepository.findAll();
    }
}
