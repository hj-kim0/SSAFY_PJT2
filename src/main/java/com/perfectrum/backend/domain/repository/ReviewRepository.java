package com.perfectrum.backend.domain.repository;

import com.perfectrum.backend.domain.entity.PerfumeEntity;
import com.perfectrum.backend.domain.entity.ReviewEntity;
import com.perfectrum.backend.domain.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Integer> {
    Slice<ReviewEntity> findByUser(UserEntity userEntity);

    ReviewEntity findTop1ByUserOrderByIdxDesc(UserEntity userEntity);

    ReviewEntity findTop1ByUserOrderByTotalScoreDescIdxDesc(UserEntity userEntity);

    @Query(value = "SELECT r FROM ReviewEntity r WHERE r.user = :userEntity AND r.idx < :lastIdx order by r.idx DESC")
    Slice<ReviewEntity> findByUserOrderByIdxDesc(UserEntity userEntity, Integer lastIdx, Pageable pageable);

    @Query(value = "SELECT r FROM ReviewEntity r " +
            "WHERE r.user = :userEntity AND ((r.idx < :lastIdx AND r.totalScore = :lastScore) OR r.totalScore < :lastScore)" +
            "ORDER BY r.totalScore DESC, r.idx DESC")
    Slice<ReviewEntity> findByUserOrderByTotalScoreDescIdxDesc(UserEntity userEntity, Integer lastScore, Integer lastIdx, Pageable pageable);

    ReviewEntity findByIdx(Integer reviewIdx);

    Slice<ReviewEntity> findByPerfume(PerfumeEntity perfume);

    ReviewEntity findTop1ByPerfumeOrderByIdxDesc(PerfumeEntity perfume);
    
    @Query(value = "select r from ReviewEntity r " +
                    "where r.perfume = :perfume and r.idx <:lastIdx "+
                    "ORDER BY r.idx DESC")
    Slice<ReviewEntity> findByPerfumeOrderByIdxDesc(PerfumeEntity perfume, Integer lastIdx, Pageable pageable);

    @Query(value = "select r from ReviewEntity r " +
                    "where r.perfume = :perfume and ((r.idx < :lastIdx AND r.likeCount = :lastLikeCount) OR r.likeCount < :lastLikeCount)"+
                    "Order by r.likeCount desc, r.idx desc")
    Slice<ReviewEntity> findByPerfumeOrderByLikeCountDescIdxDesc(PerfumeEntity perfume, Integer lastIdx, Integer lastLikeCount, Pageable pageable);


    @Query(value = "select r from ReviewEntity r " +
            "where r.perfume = :perfume and ((r.idx < :lastIdx AND r.totalScore = :lastTotalScore) OR r.totalScore < :lastTotalScore) "+
            "Order by r.totalScore desc, r.idx desc")
    Slice<ReviewEntity> findByPerfumeOrderTotalScoreDescIdxDesc(PerfumeEntity perfume, Integer lastIdx,Integer lastTotalScore, Pageable pageable);
    List<ReviewEntity> findByPerfumeIdx(Integer idx);

    Slice<ReviewEntity> findByUserAndIsDelete(UserEntity userEntity, boolean b);

    Integer countByUserAndIsDelete(UserEntity userEntity, boolean b);

    @Query("select sum(r.totalScore) from ReviewEntity as r where r.user = :userEntity and r.isDelete = :b")
    double sumByUserAndIsDelete(UserEntity userEntity, boolean b);

}
