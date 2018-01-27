package com.selfarm.api.persistence;

import com.selfarm.api.domain.PurchaseGoods;
import com.selfarm.api.domain.Voice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ByeongChan on 2018. 1. 27..
 */

@Repository
public interface PurchaseGoodsRepository extends JpaRepository<PurchaseGoods, Long> {

    @Query(value = "SELECT voicetext FROM voice WHERE status=:weather OR status=:temp OR status=:dday", nativeQuery = true)
    List<String> findByStatus(@Param("weather") String weather, @Param("temp") String temp, @Param("dday") String dday);
}
