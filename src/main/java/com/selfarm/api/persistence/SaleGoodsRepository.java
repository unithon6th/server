package com.selfarm.api.persistence;

import com.selfarm.api.domain.SaleGoods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ByeongChan on 2018. 1. 27..
 */

@Repository
public interface SaleGoodsRepository extends JpaRepository<SaleGoods, Long> {
}
