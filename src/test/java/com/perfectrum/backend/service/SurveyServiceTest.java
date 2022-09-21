package com.perfectrum.backend.service;

import com.perfectrum.backend.domain.entity.PerfumeEntity;
import com.perfectrum.backend.domain.entity.SurveyEntity;
import com.perfectrum.backend.domain.entity.UserEntity;
import com.perfectrum.backend.domain.repository.PerfumeRepository;
import com.perfectrum.backend.domain.repository.SurveyRepository;
import com.perfectrum.backend.domain.repository.UserRepository;
import com.perfectrum.backend.mapper.PerfumeViewMapper;
//import org.junit.Test;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@SpringBootTest
@Transactional
public class SurveyServiceTest {
    private static final String success = "SUCCESS";
    private static final String fail = "FAIL";
    private static final String timeOut = "access-token timeout";
    private UserRepository userRepository;
    private SurveyRepository surveyRepository;
    private PerfumeRepository perfumeRepository;

    @Autowired
    SurveyServiceTest(UserRepository userRepository, PerfumeRepository perfumeRepository, SurveyRepository surveyRepository){
        this.userRepository = userRepository;
        this.perfumeRepository = perfumeRepository;
        this.surveyRepository = surveyRepository;
    }

    @Test
    public void 설문조사_테스트(){
        Integer user_idx = 10;
        String user_id = "surveyT";
        String like_seasons = "summer";
        String like_gender = "Men";
        Integer like_longevity = 3;
        String like_timezone = "day";
        Integer like_accord_class = 1;

        Optional<UserEntity> tmpUser = userRepository.findByUserId(user_id);
        Map<String,Object> resultMap = new HashMap<>();

        // 설문결과에 따른 퍼퓸 반환
        PerfumeEntity perfume = perfumeRepository.findTop1BySeasonsContainsAndGenderAndLongevityAndTimezone
                (like_seasons,like_gender,like_longevity,like_timezone);
//        PerfumeEntity perfume = surveyRepository.findTop1ByGender(like_gender);
        resultMap.put("data",perfume);
        // 로그인 했을 경우
        if(tmpUser.isPresent()){

            // 반환된 퍼퓸의 idx 값
            System.out.println("향수 반환 시도");
            System.out.println(perfume.getGender());
            System.out.println("향수 반환 결과");
            Integer p_idx = perfume.getIdx();

            SurveyEntity surveyEntity = SurveyEntity.builder()
                    .user(tmpUser.get())
                    .perfume(perfume)
                    .likeSeasons(like_seasons)
                    .likeGender(like_gender)
                    .likeLongevity(like_longevity)
                    .likeTimezone(like_timezone)
                    .likeAccordClass(3)
                    .build();
//            SurveyEntity surveyEntity = SurveyEntity.builder().build();
//            SurveyEntity survey = new SurveyEntity();
//            survey.setUser(tmpUser.get());
//            survey.setPerfume(perfume);
//            survey.setLikeSeasons(like_seasons);
//            survey.setLikeGender(like_gender);
//            survey.setLikeLongevity(like_longevity);
//            survey.setLikeTimezone(like_timezone);
//            survey.setLikeAccordClass(like_accord_class);

            // 설문결과 저장
            System.out.println("설문결과");
            surveyRepository.save(surveyEntity);
            resultMap.put("message",success);
        }else{
            resultMap.put("message",success);

        }

        System.out.println(resultMap.toString());
    }
}