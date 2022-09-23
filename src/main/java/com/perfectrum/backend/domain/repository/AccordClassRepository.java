package com.perfectrum.backend.domain.repository;

import com.perfectrum.backend.domain.entity.AccordClassEntity;
import com.perfectrum.backend.domain.entity.PerfumeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccordClassRepository extends JpaRepository<AccordClassEntity, Integer> {
    AccordClassEntity findByIdx(Integer accordClass);

//    SELECT ac.idx FROM accords_classes ac
//    WHERE ac.idx in (SELECT a.accord_class FROM accords a
//            WHERE a.idx IN (SELECT pa.accord_idx  FROM perfume_accords pa
//            WHERE pa.perfume_idx = 100));
    @Query(value = "SELECT Ac FROM AccordClassEntity AS Ac " +
            "WHERE Ac.idx IN (SELECT a.accordClass FROM AccordEntity AS a WHERE a.idx IN (SELECT pa.accord FROM PerfumeAccordsEntity AS pa WHERE pa.perfume = :perfume))")
    List<AccordClassEntity> findByPerfumeAccordClass(PerfumeEntity perfume);
}