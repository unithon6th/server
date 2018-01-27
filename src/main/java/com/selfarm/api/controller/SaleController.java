package com.selfarm.api.controller;

import com.selfarm.api.domain.SaleGoods;
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
@Api(value = "sale goods API", description = "sale goods API", basePath = "/api/v1/sales")
@RestController
@RequestMapping("/api/v1/sales")
public class SaleController {

    @Autowired
    SaleGoodsService saleGoodsService;

    @ApiOperation(value = "save", notes = "save sales goods")
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String saveSaleGoods(@RequestBody SaleGoods saleGoods){
        try {
            SaleGoods goods = SaleGoods.build(saleGoods);
            saleGoodsService.save(saleGoods);
            return "save success";
        } catch (Exception e) {
            e.printStackTrace();
            return "save fail";
        }
    }

    @ApiOperation(value = "findAll", notes = "select all sale goods")
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    public List<SaleGoods> findAll(){ return saleGoodsService.findAll(); }
}
