package com.perfectrum.backend.service.impl;

import com.perfectrum.backend.dto.review.ReviewViewDto;
import com.perfectrum.backend.domain.entity.*;
import com.perfectrum.backend.domain.repository.*;
import com.perfectrum.backend.service.PerfumeDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class PerfumeDetailServiceImpl implements PerfumeDetailService {

    private static final String success = "SUCCESS";
    private static final String fail = "FAIL";
    private static final String timeOut = "access-token timeout";

    private UserRepository userRepository;
    private PerfumeRepository perfumeRepository;
    private AccordClassRepository accordClassRepository;

    private ReviewRepository reviewRepository;

    private UserDetailLogRepository userDetailLogRepository;
    private HaveListRepository haveListRepository;
    private WishListRepository wishListRepository;

    @Autowired
    PerfumeDetailServiceImpl(UserRepository userRepository, PerfumeRepository perfumeRepository,
                             AccordClassRepository accordClassRepository,ReviewRepository reviewRepository,
                             UserDetailLogRepository userDetailLogRepository,
                             HaveListRepository haveListRepository,WishListRepository wishListRepository) {
        this.userRepository = userRepository;
        this.perfumeRepository = perfumeRepository;
        this.accordClassRepository = accordClassRepository;
        this.reviewRepository = reviewRepository;
        this.userDetailLogRepository = userDetailLogRepository;
        this.haveListRepository = haveListRepository;
        this.wishListRepository = wishListRepository;
    }

    @Override
    public Map<String, Object> getPerfumeDetail(String decodeId,Integer perfumeIdx) {
        Optional<UserEntity> user = userRepository.findByUserId(decodeId);
        Map<String,Object> data = new HashMap<>();
        if(user.isPresent()){
            PerfumeEntity perfume = perfumeRepository.findByIdx(perfumeIdx);
            data.put("perfume",perfume);

            List<ReviewEntity> reviewList = reviewRepository.findByPerfumeIdx(perfumeIdx);
            data.put("review_list",reviewList);
        }

        return data;
    }



    @Override
    public void addWishList(String decodeId, Integer perfumeIdx) {
        Optional<UserEntity> user = userRepository.findByUserId(decodeId);
        boolean isWish = false;
        if(user.isPresent()){
            Long cnt = wishListRepository.countByuserIdx(user.get().getIdx());
            if(cnt == 0){
                isWish = true;
            }
            PerfumeEntity perfume = perfumeRepository.findByIdx(perfumeIdx);
            WishListEntity wish = WishListEntity.builder()
                    .user(user.get())
                    .perfume(perfume)
                    .isDelete(isWish)
                    .build();

            wishListRepository.save(wish);
        }
    }

    @Override
    public void addHaveList(String decodeId, Integer perfumeIdx) {
        Optional<UserEntity> user = userRepository.findByUserId(decodeId);
        boolean isHave = false;
        if(user.isPresent()){
            Long cnt = haveListRepository.countByuserIdx(user.get().getIdx());
            if(cnt == 0){
                isHave = true;
            }
            PerfumeEntity perfume = perfumeRepository.findByIdx(perfumeIdx);
            HaveListEntity have = HaveListEntity.builder()
                    .user(user.get())
                    .perfume(perfume)
                    .isDelete(isHave)
                    .build();

            haveListRepository.save(have);
        }
    }

    @Override
    public void registReview(String decodeId,Integer perfumeIdx, ReviewViewDto reviewDto){
        Optional<UserEntity> user = userRepository.findByUserId(decodeId);
        PerfumeEntity perfume = perfumeRepository.findByIdx(perfumeIdx);
        String reviewImg = reviewDto.getReviewImg();
        Integer totalScore = reviewDto.getTotalScore();
        String content = reviewDto.getContent();
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));

        if(user.isPresent()){
            ReviewEntity reviewEntity = ReviewEntity.builder()
                        .user(user.get())
                        .perfume(perfume)
                        .reviewImg(reviewImg)
                        .totalScore(totalScore)
                        .longevity(perfume.getLongevity())
                        .sillageScore(perfume.getSillage())
                        .content(content)
                        .time(now)
                        .updateTime(null)
                        .build();

            reviewRepository.save(reviewEntity);
        }
    }

    @Override
    public void updateReview(String decodeId, Integer perfumeIdx, Integer reviewIdx,ReviewViewDto reviewDto) {
        Optional<UserEntity> user = userRepository.findByUserId(decodeId);
        PerfumeEntity perfume = perfumeRepository.findByIdx(perfumeIdx);
        String reviewImg = reviewDto.getReviewImg();
        Integer totalScore = reviewDto.getTotalScore();
        String content = reviewDto.getContent();
        LocalDateTime registTime = LocalDateTime.from(reviewDto.getTime());
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        if(user.isPresent()){
            ReviewEntity reviewEntity = ReviewEntity.builder()
                    .user(user.get())
                    .perfume(perfume)
                    .reviewImg(reviewImg)
                    .totalScore(totalScore)
                    .longevity(perfume.getLongevity())
                    .sillageScore(perfume.getSillage())
                    .content(content)
                    .time(registTime)
                    .updateTime(now)
                    .build();

            reviewRepository.save(reviewEntity);
        }
    }

}
