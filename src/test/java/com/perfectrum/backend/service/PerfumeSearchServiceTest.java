package com.perfectrum.backend.service;

import com.perfectrum.backend.domain.entity.AccordClassEntity;
import com.perfectrum.backend.domain.entity.PerfumeEntity;
import com.perfectrum.backend.domain.repository.*;
import com.perfectrum.backend.dto.perfume.PerfumeViewDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;


import java.util.*;

@SpringBootTest
public class PerfumeSearchServiceTest {
    private PerfumeRepository perfumeRepository;
    private UserRepository userRepository;
    private AccordClassRepository accordClassRepository;
    private HaveListRepository haveListRepository;
    private WishListRepository wishListRepository;

    @Autowired
    PerfumeSearchServiceTest(PerfumeRepository perfumeRepository, UserRepository userRepository, AccordClassRepository accordClassRepository,
                             HaveListRepository haveListRepository, WishListRepository wishListRepository){
        this.perfumeRepository = perfumeRepository;
        this.userRepository = userRepository;
        this.accordClassRepository = accordClassRepository;
        this.haveListRepository = haveListRepository;
        this.wishListRepository = wishListRepository;
    }

    @Test
    public void 향수_상세_검색(){
        Map<String, Object> data = new HashMap<>();

        // given
        Integer lastIdx = 712;
        Float lastItemRating = null;
        Integer pageSize = 6;

        String genderList = "Men,Women,Unisex";
        String durationList = "1,3,5";
        String accordClassList = "3,4,5,6,7,8";


        List<Integer> longevity = new ArrayList<>();
        if(durationList != null){
            String str = durationList.replaceAll("[^0-9]","");
            char[] ch = str.toCharArray();
            for(int i=0; i<ch.length; i++){
                longevity.add(ch[i]-'0');
            }
        }

        List<AccordClassEntity> accordClass = new ArrayList<>();
        if(accordClassList != null){
            String str = accordClassList.replaceAll("[^0-9]","");
            char[] ch = str.toCharArray();
            for(int i=0; i<ch.length; i++){
                accordClass.add(accordClassRepository.findByIdx(ch[i]-'0'));
            }
        }

        if(lastIdx == null){
            lastIdx = perfumeRepository.findTop1ByOrderByIdxDesc().getIdx() + 1;
        }

        if(lastItemRating == null){
            lastItemRating = perfumeRepository.findTop1ByOrderByItemRatingDescIdxDesc().getItemRating() + 1;
        }


        Pageable pageable = Pageable.ofSize(pageSize);
        Slice<PerfumeEntity> searchList = perfumeRepository.findAllByGenderAndLongevityAndAccordClass(genderList, longevity, accordClass, lastIdx, pageable);

        List<PerfumeViewDto> result = new ArrayList<>();
        if(!searchList.isEmpty()){
            boolean hasNext = searchList.hasNext();
            data.put("hasNext", hasNext);

            for(PerfumeEntity pe : searchList){
                PerfumeViewDto perfumeViewDto = PerfumeViewDto.builder()
                        .idx(pe.getIdx())
                        .brandName(pe.getBrandName())
                        .perfumeName(pe.getPerfumeName())
                        .concentration(pe.getConcentration())
                        .gender(pe.getGender())
                        .scent(pe.getScent())
                        .topNotes(pe.getTopNotes())
                        .middleNotes(pe.getMiddleNotes())
                        .baseNotes(pe.getBaseNotes())
                        .itemRating(pe.getItemRating())
                        .perfumeImg(pe.getPerfumeImg())
                        .description(pe.getDescription())
                        .seasons(pe.getSeasons())
                        .timezone(pe.getTimezone())
                        .longevity(pe.getLongevity())
                        .sillage(pe.getSillage())
                        .wishCount(Long.valueOf(Optional.ofNullable(haveListRepository.countByPerfumeIdx(pe.getIdx())).orElse(0L)).intValue())
                        .haveCount(Long.valueOf(Optional.ofNullable(wishListRepository.countByPerfumeIdx(pe.getIdx())).orElse(0L)).intValue())
                        .build();

                result.add(perfumeViewDto);
            }

            data.put("searchList", result);
        }

        for(PerfumeViewDto d : result){
            System.out.println(d.toString());
        }

    }
}
