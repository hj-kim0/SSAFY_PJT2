package com.perfectrum.backend.domain.repository;

import com.perfectrum.backend.domain.entity.AccordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccordRepository extends JpaRepository<AccordEntity,Integer> {

    AccordEntity findByIdx(Integer accordIdx);
}
