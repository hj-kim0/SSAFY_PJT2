package com.perfectrum.backend.domain.repository;

import com.perfectrum.backend.domain.entity.ReviewEntity;
import com.perfectrum.backend.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Integer> {
    List<ReviewEntity> findByUser(UserEntity userEntity);

    Integer countByUser(UserEntity userEntity);

    @Query(value = "SELECT SUM(r.totalScore) FROM ReviewEntity r WHERE r.user = :userEntity")
    Double sumByUser(UserEntity userEntity);
}
