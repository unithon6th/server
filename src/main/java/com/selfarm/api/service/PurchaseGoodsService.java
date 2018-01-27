package com.selfarm.api.service;

import com.selfarm.api.domain.PurchaseGoods;
import com.selfarm.api.persistence.PurchaseGoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ByeongChan on 2018. 1. 27..
 */

@Service
public class PurchaseGoodsService {

    @Autowired
    PurchaseGoodsRepository purchaseGoodsRepository;

    public void save(PurchaseGoods purchaseGoods) {
        purchaseGoodsRepository.save(purchaseGoods);
    }

    public List<PurchaseGoods> findAll() {
        return purchaseGoodsRepository.findAll();
    }
}
