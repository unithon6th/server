package com.selfarm.api;

import com.selfarm.api.service.PurchaseGoodsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by ByeongChan on 2018. 1. 28..
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class PurchaseServiceTest {

    @Autowired
    PurchaseGoodsService purchaseGoodsService;
}
