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

    ReviewEntity findByIdx(Integer reviewIdx);

//    List<ReviewEntity> findByPerfumeIdx(Integer perfumeIdx);

    Slice<ReviewEntity> findByPerfume(PerfumeEntity perfume);

    ReviewEntity findTop1ByPerfumeOrderByIdxDesc(PerfumeEntity perfume);




    // 주어진 갯수만큼 끊어서 데이터를 가져오지만 스크롤 도중 새로운 리뷰가 생성될 시
    // 데이터가 하나씩 밀려서 중복되는 데이터를 가져올 수 있는 상황이 발생하므로
    // 현재까지 로드된 데이터 중 가장 작은 idx값을 가져와서 비교후 pagination
    // https://wbluke.tistory.com/18
    @Query(value = "select r from ReviewEntity r " +
                    "where r.perfume = :perfume and r.idx <:lastIdx "+
                    "ORDER BY r.idx DESC")
    Slice<ReviewEntity> findByPerfumeOrderByIdxDesc(PerfumeEntity perfume, Integer lastIdx, Pageable pageable);

//    ReviewEntity findTop1ByPerfumeIdxOrderByLikeCountIdxDesc(Integer perfumeIdx);

    @Query(value = "select r from ReviewEntity r " +
                    "where r.perfume = :perfume and r.idx <:lastIdx "+
                    "Order by r.idx desc, r.likeCount desc")
    Slice<ReviewEntity> findByPerfumeOrderByIdxDescLikeCountDesc(PerfumeEntity perfume, Integer lastIdx, Pageable pageable);

    List<ReviewEntity> findByPerfumeIdx(Integer idx);
//    @Modifying
//    @Query("UPDATE reviews r SET r.review_img =:reviewImg, r.total_score = :totalScore,r.content = :content, r.update_time = :updateTime" +
//            "WHERE r.idx = :idx")
//    Integer updateReview(@Param(value="reviewImg")String reviewImg, @Param(value="totalScore")Integer total_score, @Param(value="content")String content, @Param(value="updateTime")LocalDateTime update_time);

}
