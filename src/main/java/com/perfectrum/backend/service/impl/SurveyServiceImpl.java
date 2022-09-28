package com.perfectrum.backend.service.impl;

import com.perfectrum.backend.domain.entity.AccordEntity;
import com.perfectrum.backend.domain.entity.PerfumeEntity;
import com.perfectrum.backend.domain.entity.UserEntity;
import com.perfectrum.backend.domain.repository.HaveListRepository;
import com.perfectrum.backend.domain.repository.PerfumeRepository;
import com.perfectrum.backend.domain.repository.UserRepository;
import com.perfectrum.backend.domain.repository.WishListRepository;
import com.perfectrum.backend.dto.perfume.PerfumeViewDto;
import com.perfectrum.backend.dto.survey.SurveyDto;
import com.perfectrum.backend.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SurveyServiceImpl implements SurveyService {

    private PerfumeRepository perfumeRepository;
    private UserRepository userRepository;

    private HaveListRepository haveListRepository;
    private WishListRepository wishListRepository;
    @Autowired
    SurveyServiceImpl(PerfumeRepository perfumeRepository,UserRepository userRepository,WishListRepository wishListRepository,
                      HaveListRepository haveListRepository){
        this.perfumeRepository = perfumeRepository;
        this.userRepository = userRepository;
        this.wishListRepository = wishListRepository;
        this.haveListRepository = haveListRepository;
    }
    @Override
    public Map<String, Object> surveyResult(String decodeId, SurveyDto surveyDto) {
        Optional<UserEntity> user = userRepository.findByUserId(decodeId);
        Map<String,Object> data = new HashMap<>();
        PerfumeEntity perfume;
        Random random = new Random();
        String gender = "Men";
        if(surveyDto.getGender().equals("남자")){
            gender = "Men";
        }else if(surveyDto.getGender().equals("여자")){
            gender = "Women";
        }else{
            gender = "Unisex";
        }
        String season = "spring";
        season = surveyDto.getSeason();
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

        List<PerfumeEntity> perfumeList = null;
        List<Integer> sList = new ArrayList<>();
        List<Integer> wList = new ArrayList<>();
        for(int i=0;i<3;i++){
            sList.add(i+1);
        }
        wList.add(4);
        wList.add(5);
//        if(longevity == 1){
//            perfumeList = perfumeRepository.findByGenderAndSeasonsAndSListLongevityAndAccordClass(gender,season,sList,accordClass);
//        }else if(longevity == 0){
//            perfumeList = perfumeRepository.findByGenderAndSeasonsAndwListLongevityAndAccordClass(gender,season,wList,accordClass);
//        }
        System.out.println(perfumeList.size());
        System.out.println("웨얼두");
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

            if(max < cnt){
                max = cnt;
                resultList.clear();
                resultList.add(perfumeList.get(i));
            }else if(max == cnt){
                resultList.add(perfumeList.get(i));
            }
            list.add(cnt);
        }


        if(resultList.size() != 1){
            perfume = resultList.get(random.nextInt(resultList.size()));
            ;
        }else {
            perfume = resultList.get(0);
        }
        Integer haveCount = Long.valueOf(Optional.ofNullable(haveListRepository.countByPerfumeIdx(perfume.getIdx())).orElse(0L)).intValue();

        Integer wishCount = Long.valueOf(Optional.ofNullable(wishListRepository.countByPerfumeIdx(perfume.getIdx())).orElse(0L)).intValue();
        PerfumeViewDto perfumeViewDto = PerfumeViewDto.builder()
                .idx(perfume.getIdx())
                .brandName(perfume.getBrandName())
                .perfumeName(perfume.getPerfumeName())
                .concentration(perfume.getConcentration())
                .gender(perfume.getGender())
                .scent(perfume.getScent())
                .topNotes(perfume.getTopNotes())
                .middleNotes(perfume.getMiddleNotes())
                .baseNotes(perfume.getBaseNotes())
                .itemRating(perfume.getItemRating())
                .perfumeImg(perfume.getPerfumeImg())
                .description(perfume.getDescription())
                .seasons(perfume.getSeasons())
                .timezone(perfume.getTimezone())
                .longevity(perfume.getLongevity())
                .sillage(perfume.getSillage())
                .wishCount(wishCount)
                .haveCount(haveCount)
                .build();

        System.out.println("idx " + perfumeViewDto.getIdx());
        data.put("perfume",perfumeViewDto);
        return data;
    }
}