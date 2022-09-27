package com.perfectrum.backend.service.impl;

import com.perfectrum.backend.domain.entity.AccordEntity;
import com.perfectrum.backend.domain.entity.PerfumeEntity;
import com.perfectrum.backend.domain.entity.UserEntity;
import com.perfectrum.backend.domain.repository.PerfumeRepository;
import com.perfectrum.backend.domain.repository.UserRepository;
import com.perfectrum.backend.domain.repository.WishListRepository;
import com.perfectrum.backend.dto.survey.SurveyDto;
import com.perfectrum.backend.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SurveyServiceImpl implements SurveyService {

    private PerfumeRepository perfumeRepository;
    private UserRepository userRepository;

    private WishListRepository wishListRepository;
    @Autowired
    SurveyServiceImpl(PerfumeRepository perfumeRepository,UserRepository userRepository,WishListRepository wishListRepository){
        this.perfumeRepository = perfumeRepository;
        this.userRepository = userRepository;
        this.wishListRepository = wishListRepository;
    }
    @Override
    public PerfumeEntity surveyResult(String decodeId, SurveyDto surveyDto) {
        Optional<UserEntity> user = userRepository.findByUserId(decodeId);
        Random random = new Random();
        String gender = surveyDto.getGender();
        String season = surveyDto.getSeason();
        Integer longevity = surveyDto.getLongevity();
        String accordClass_s = surveyDto.getAccordClass();
        Integer accordClass = 0;

        switch(accordClass_s){
            case "꽃 향기":
                accordClass = 2;
                break;
            case "풀 향기":
                accordClass = 3;
                break;
            case "과일 향":
                accordClass = 4;
                break;
            case "달콤한 향":
                accordClass = 8;
                break;
            case "매운 향":
                accordClass = 5;
                break;
            case "톡쏘는 향":
                accordClass = 1;
                break;
            case "야성적인 향":
                accordClass = 6;
                break;
            case "인공적인 향":
                accordClass = 7;
                break;
        }
        System.out.println("향수 리스트 찾기");
        List<PerfumeEntity> perfumeList = perfumeRepository.findByGenderAndSeasonsAndLongevity(gender,season,longevity);
        System.out.println("향수 리스트 찾아옴" + perfumeList.size());
        List<AccordEntity> accordList;
        List<Integer> list = new ArrayList<>();
        List<PerfumeEntity> resultList = new ArrayList<>();
        Integer max = 0,cnt = 0;
        for(int i=0;i<perfumeList.size();i++){

            cnt = 0;
            accordList = perfumeRepository.findByPerfume(perfumeList.get(i));
            for(int j=0;j<accordList.size();j++){
                if(accordList.get(j).getAccordClass().getIdx() == accordClass){
                    cnt++;
                }
            }
            System.out.println(i +": i " + "max = "+max +"cnt = " +cnt);

            if(max < cnt){
                max = cnt;
                resultList.clear();
                System.out.println("max = "+max +"cnt = " +cnt + "list clear " + i);
                resultList.add(perfumeList.get(i));
            }else if(max == cnt){
                resultList.add(perfumeList.get(i));
            }
            list.add(cnt);
            System.out.println("max "+max);
        }
        System.out.println(list.size());
        System.out.println("최대 포함 개수" + max);
        System.out.println("최종개수" + resultList.size());
        for(PerfumeEntity pe : resultList){
            System.out.println("최종 리스트 : " + pe.getIdx() + ": "+ pe.getPerfumeName());
        }

        if(resultList.size() != 1){
            return resultList.get(random.nextInt(resultList.size()));
        }else {
            return resultList.get(0);
        }
    }
}
