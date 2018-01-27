package com.selfarm.api.controller;

import com.selfarm.api.domain.PurchaseGoods;
import com.selfarm.api.domain.SaleGoods;
import com.selfarm.api.service.PurchaseGoodsService;
import com.selfarm.api.service.SaleGoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by ByeongChan on 2018. 1. 27..
 */

@CrossOrigin(origins = "*")
@Api(value = "purchase goods API", description = "purchase goods API", basePath = "/api/v1/purchase")
@RestController
@RequestMapping("/api/v1/purchase")
public class PurchaseController {

    @Autowired
    PurchaseGoodsService purchaseGoodsService;

    @ApiOperation(value = "save", notes = "save purchase goods")
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String savePurchaseGoods(@RequestBody PurchaseGoods purchaseGoods){
        try {
            PurchaseGoods goods = PurchaseGoods.build(purchaseGoods);
            purchaseGoodsService.save(purchaseGoods);
            return "save success";
        } catch (Exception e) {
            e.printStackTrace();
            return "save fail";
        }
    }

    @ApiOperation(value = "findAll", notes = "select all sale goods")
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    public List<PurchaseGoods> findAll(){ return purchaseGoodsService.findAll(); }
}
