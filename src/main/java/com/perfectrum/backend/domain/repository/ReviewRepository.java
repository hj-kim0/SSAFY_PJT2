package com.perfectrum.backend.domain.repository;

import com.perfectrum.backend.domain.entity.ReviewEntity;
import com.perfectrum.backend.domain.entity.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Integer> {
    Slice<ReviewEntity> findByUser(UserEntity userEntity);

    Integer countByUser(UserEntity userEntity);

    @Query(value = "SELECT SUM(r.totalScore) FROM ReviewEntity r WHERE r.user = :userEntity")
    Double sumByUser(UserEntity userEntity);

    ReviewEntity findTop1ByUserOrderByIdxDesc(UserEntity userEntity);

    ReviewEntity findTop1ByUserOrderByTotalScoreDescIdxDesc(UserEntity userEntity);

    @Query(value = "SELECT r FROM ReviewEntity r WHERE r.user = :userEntity AND r.idx < :lastIdx order by r.idx DESC")
    Slice<ReviewEntity> findByUserOrderByIdxDesc(UserEntity userEntity, Integer lastIdx, Pageable pageable);

    @Query(value = "SELECT r FROM ReviewEntity r " +
            "WHERE r.user = :userEntity AND ((r.idx < :lastIdx AND r.totalScore = :lastScore) OR r.totalScore < :lastScore)" +
            "ORDER BY r.totalScore DESC, r.idx DESC")
    Slice<ReviewEntity> findByUserOrderByTotalScoreDescIdxDesc(UserEntity userEntity, Integer lastScore, Integer lastIdx, Pageable pageable);
}
