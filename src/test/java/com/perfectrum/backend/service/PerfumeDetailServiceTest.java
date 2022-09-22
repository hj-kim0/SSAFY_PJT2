package com.perfectrum.backend.service;


import com.perfectrum.backend.domain.entity.*;
import com.perfectrum.backend.domain.repository.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@SpringBootTest
public class PerfumeDetailServiceTest {
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
    PerfumeDetailServiceTest(UserRepository userRepository, PerfumeRepository perfumeRepository,
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

    @Test
    public void 향수상세정보_테스트(){
        Integer userIdx = 10;
        String userId = "Test";
        Integer perfumeIdx = 100;

        Optional<UserEntity> tmpUser = userRepository.findByUserId(userId);
        Map<String,Object> resultMap = new HashMap<>();

        PerfumeEntity perfume = perfumeRepository.findByIdx(perfumeIdx);

        resultMap.put("message",success);
        resultMap.put("data",perfume);

        if(tmpUser.isPresent()){


            UserDetailLogEntity userDetailLogEntity = UserDetailLogEntity.builder()
                    .user(tmpUser.get())
                    .perfume(perfume)
                    .build();

            userDetailLogRepository.save(userDetailLogEntity);
        }
    }

    @Test
    public void 향수담기_테스트(){
        Integer userIdx = 10;
        String userId = "Test";
        Integer perfumeIdx = 100;

        Optional<UserEntity> tmpUser = userRepository.findByUserId(userId);
        Map<String,Object> resultMap = new HashMap<>();

        PerfumeEntity perfume = perfumeRepository.findByIdx(perfumeIdx);

        HaveListEntity have = HaveListEntity.builder()
                .user(tmpUser.get())
                .perfume(perfume)
                .isDelete(false)
                .build();
        haveListRepository.save(have);

        WishListEntity wish = WishListEntity.builder()
                .user(tmpUser.get())
                .perfume(perfume)
                .isDelete(false)
                .build();
        wishListRepository.save(wish);

    }

}
