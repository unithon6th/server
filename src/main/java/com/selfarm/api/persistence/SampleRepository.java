package com.selfarm.api.persistence;

import com.selfarm.api.domain.SampleVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ByeongChan on 2018. 1. 15..
 */

@Repository
public interface SampleRepository extends JpaRepository<SampleVO, Long> {
    SampleVO findByVal1(String val1);
}
