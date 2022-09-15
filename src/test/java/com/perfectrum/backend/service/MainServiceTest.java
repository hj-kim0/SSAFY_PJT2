package com.perfectrum.backend.service;

import com.perfectrum.backend.domain.entity.PerfumeEntity;
import com.perfectrum.backend.domain.entity.UserEntity;
import com.perfectrum.backend.domain.repository.PerfumeRepository;
import com.perfectrum.backend.domain.repository.UserRepository;
import com.perfectrum.backend.dto.perfume.PerfumeViewDto;
import com.perfectrum.backend.mapper.PerfumeViewMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.*;

@SpringBootTest
@Transactional
public class MainServiceTest {
    private UserRepository userRepository;
    private PerfumeRepository perfumeRepository;
    private PerfumeViewMapper perfumeViewMapper;


    @Autowired
    MainServiceTest(UserRepository userRepository, PerfumeRepository perfumeRepository, PerfumeViewMapper perfumeViewMapper){
        this.userRepository = userRepository;
        this.perfumeRepository = perfumeRepository;
        this.perfumeViewMapper = perfumeViewMapper;
    }

    @Test
    public void 베스트_향수_top6_조회() {
        // given
        // 회원 정보 있을 때
//        String testId = "kakao123145";
        String testId = "kakao123456";
        String gender = null;
        String season = null;
        String accordClass = null;

        // 회원 정보 없을 때

        // when
        Map<String, Object> resultMap = new HashMap<>();
        List<PerfumeViewDto> result = new ArrayList<>();

        Optional<UserEntity> testUserOptional = userRepository.findByUserId(testId);
        // 로그인 한 회원
        if(testUserOptional.isPresent()){
            UserEntity testUser = testUserOptional.get();
            gender = testUser.getGender();
            season = testUser.getSeasons();

            // 추가 정보 값이 있다면 -> 추가 정보 기반 향수 추천
            if(gender != null){
                List<PerfumeEntity> perfumes = perfumeRepository.findTop6ByGenderAndSeasonsContainsOrderByItemRatingDesc(gender, season);

                for(PerfumeEntity p : perfumes){
                    PerfumeViewDto perfumeViewDto = perfumeViewMapper.toDto(p);
                    result.add(perfumeViewDto);
                }

                resultMap.put("data", result);
            }else{
                // 추가 정보 없음 -> 기본 베스트 향수 추천
                List<PerfumeEntity> perfumes = perfumeRepository.findTop6ByOrderByItemRatingDesc();
                for(PerfumeEntity p : perfumes){
                    PerfumeViewDto perfumeViewDto = perfumeViewMapper.toDto(p);
                    result.add(perfumeViewDto);
                }

                resultMap.put("data", result);
                for(PerfumeViewDto d : result){
                    System.out.println(d.toString());
                }
            }
        }else { // 로그인 하지 않음 -> 기본 베스트 향수 추천
            List<PerfumeEntity> perfumes = perfumeRepository.findTop6ByOrderByItemRatingDesc();
            for(PerfumeEntity p : perfumes){
                PerfumeViewDto perfumeViewDto = perfumeViewMapper.toDto(p);
                result.add(perfumeViewDto);
            }

            resultMap.put("data", result);
        }

        // then
//        System.out.println(resultMap.toString());
    }
}
