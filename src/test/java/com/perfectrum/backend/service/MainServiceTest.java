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
import org.springframework.http.HttpStatus;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@SpringBootTest
@Transactional
public class MainServiceTest {
    private static final String success = "SUCCESS";
    private static final String fail = "FAIL";
    private static final String timeOut = "access-token tieout";
    private static HttpStatus status = HttpStatus.NOT_FOUND;
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
                resultMap.put("message", success);
                status = HttpStatus.OK;
            }else{
                // 추가 정보 없음 -> 기본 베스트 향수 추천
                List<PerfumeEntity> perfumes = perfumeRepository.findTop6ByOrderByItemRatingDesc();
                for(PerfumeEntity p : perfumes){
                    PerfumeViewDto perfumeViewDto = perfumeViewMapper.toDto(p);
                    result.add(perfumeViewDto);
                }

                resultMap.put("data", result);
                resultMap.put("message", success);
                status = HttpStatus.OK;
//                for(PerfumeViewDto d : result){
//                    System.out.println(d.toString());
//                }
            }
        }else { // 로그인 하지 않음 -> 기본 베스트 향수 추천
            List<PerfumeEntity> perfumes = perfumeRepository.findTop6ByOrderByItemRatingDesc();
            for(PerfumeEntity p : perfumes){
                PerfumeViewDto perfumeViewDto = perfumeViewMapper.toDto(p);
                result.add(perfumeViewDto);
            }

            resultMap.put("data", result);
            resultMap.put("message", success);
            status = HttpStatus.OK;
        }

        // then
        System.out.println(resultMap.toString());
    }

    @Test
    public void 오늘의_향수_Top6_조회() {
        // 로그인, 비로그인 동일 -> 현재 날짜(월) => season / 현재 시간 => timezone으로 구분해서 출력
        // given
        // 1. 날짜 얻어오기
        LocalDate now = LocalDate.now(ZoneId.of("Asia/Seoul"));

        // 현재 월 가져오기
        String monthString = now.getMonth().toString();
        int monthValue = now.getMonthValue();

        System.out.println("========= Month =========");
        System.out.println(monthString);
        System.out.println(monthValue+"월");
        System.out.println("==========================");

        // 계절
        String season;
        switch (monthValue){
            case 3:
            case 4:
            case 5:
                season = "spring";
                break;
            case 6:
            case 7:
            case 8:
                season = "summer";
                break;
            case 9:
            case 10:
            case 11:
                season = "fall";
                break;
            default:
                season = "winter";
                break;
        }

        System.out.println("========== season ==========");
        System.out.println(season);
        System.out.println("==========================");

        // 2. 시간 얻어오기
        LocalTime nowTIme = LocalTime.now(ZoneId.of("Asia/Seoul"));
        int hour = nowTIme.getHour();

        System.out.println("========== Hour ==========");
        System.out.println(hour);
        System.out.println("==========================");

        // 시간대 구하기
        String timeZone;
        if(6<=hour && hour<=18) timeZone = "day";
        else timeZone = "night";

        System.out.println("========== Timezone ==========");
        System.out.println(timeZone);
        System.out.println("==========================");

        // when
        Map<String, Object> resultMap = new HashMap<>();
        List<PerfumeViewDto> result = new ArrayList<>();

//        List<PerfumeEntity> perfumeEntities = perfumeRepository.findTop20ByTimezoneAndSeasonsContainsOrderByItemRatingDesc(timeZone, season);
        List<PerfumeEntity> perfumeEntities = perfumeRepository.findTop6ByTimezoneAndSeasonsContainsOrderByItemRatingDesc(timeZone, season);
        for(PerfumeEntity p : perfumeEntities){
            PerfumeViewDto dto = perfumeViewMapper.toDto(p);
            result.add(dto);
        }

        resultMap.put("data", result);
        resultMap.put("message", success);
        status = HttpStatus.OK;

        // then
        for(PerfumeViewDto d : result){
            System.out.println(d.toString());
        }
    }
}
