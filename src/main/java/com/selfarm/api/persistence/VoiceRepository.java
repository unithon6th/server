package com.selfarm.api.persistence;

import com.selfarm.api.domain.Voice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by ByeongChan on 2018. 1. 27..
 */

@Repository
public interface VoiceRepository extends JpaRepository<Voice, Long> {

    @Query(value = "SELECT * FROM voice WHERE status=:status ORDER BY rand() LIMIT 1", nativeQuery = true)
    Voice findByStatus(@Param("status") String status);
}
