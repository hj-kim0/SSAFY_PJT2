package com.perfectrum.backend.domain.repository;

import com.perfectrum.backend.domain.entity.AccordClassEntity;
import com.perfectrum.backend.domain.entity.AccordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccordRepository extends JpaRepository<AccordEntity,Integer> {

    AccordEntity findByIdx(Integer accordIdx);

    List<AccordEntity> findByAccordClass(AccordClassEntity idx);
}
