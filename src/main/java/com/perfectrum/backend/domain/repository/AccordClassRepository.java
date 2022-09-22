package com.perfectrum.backend.domain.repository;

import com.perfectrum.backend.domain.entity.AccordClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccordClassRepository extends JpaRepository<AccordClassEntity, Integer> {
    AccordClassEntity findByIdx(Integer accordClass);
}