package com.perfectrum.backend.service;

import com.perfectrum.backend.domain.entity.PerfumeEntity;
import com.perfectrum.backend.domain.entity.ReviewEntity;
import com.perfectrum.backend.domain.entity.UserEntity;
import com.perfectrum.backend.domain.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@SpringBootTest
public class ReviewServiceTest {
    private static final String success = "SUCCESS";
    private static final String fail = "FAIL";
    private static final String timeOut = "access-token timeout";
    private UserRepository userRepository;
    private PerfumeRepository perfumeRepository;
    private ReviewRepository reviewRepository;
    private UserSearchLogRepository userSearchLogRepository;

    @Autowired
    ReviewServiceTest(UserRepository userRepository, PerfumeRepository perfumeRepository, ReviewRepository reviewRepository){
        this.userRepository = userRepository;
        this.perfumeRepository = perfumeRepository;
        this.reviewRepository = reviewRepository;
    }


    @Test
    public void 리뷰등록_테스트(){
        Integer userIdx = 3;
        String userId = "reviewT";
        Integer perfumeIdx = 100;
        String reviewImg = "ImgURL";
        Integer totalScore = 4;
        Integer longevity;
        Integer sillageScore;
        String content = "이 향수 너무 좋아요";
        Integer likeCount = 3;
        LocalDateTime now = LocalDateTime.now();

        Optional<UserEntity> tmpUser = userRepository.findByUserId(userId);
        PerfumeEntity perfume = perfumeRepository.findByIdx(perfumeIdx);
        Map<String,Object> resultMap = new HashMap<>();

        if(tmpUser.isPresent()){
            ReviewEntity reviewEntity = ReviewEntity.builder()
                    .user(tmpUser.get())
                    .perfume(perfume)
                    .reviewImg(reviewImg)
                    .totalScore(totalScore)
                    .longevity(perfume.getLongevity())
                    .sillageScore(perfume.getSillage())
                    .content(content)
                    .time(now)
                    .build();
            reviewRepository.save(reviewEntity);
            resultMap.put("message",success);
        }else{

        }
    }

    @Test
    public void 리뷰수정_테스트(){
        Integer userIdx = 3;
        String userId = "reviewT";
        Integer perfumeIdx = 100;
        String reviewImg = "imgURL_re";
        Integer totalScore = 1;
        Integer longevity;
        Integer sillageScore;
        String content = "이 향수 별로에요";
        Integer likeCount = 3;

        Optional<UserEntity> tmpUser = userRepository.findByUserId(userId);
        PerfumeEntity perfume = perfumeRepository.findByIdx(perfumeIdx);
        Map<String,Object> resultMap = new HashMap<>();

        if(tmpUser.isPresent()){
            LocalDateTime now = LocalDateTime.now();
            ReviewEntity reviewEntity = ReviewEntity.builder()
                    .user(tmpUser.get())
                    .perfume(perfume)
                    .reviewImg(reviewImg)
                    .totalScore(totalScore)
                    .longevity(perfume.getLongevity())
                    .sillageScore(perfume.getSillage())
                    .content(content)
                    .updateTime(now)
                    .build();
            reviewRepository.save(reviewEntity);
            resultMap.put("message",success);
        }
    }
}
