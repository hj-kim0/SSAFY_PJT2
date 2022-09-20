package com.perfectrum.backend.service.impl;

import com.perfectrum.backend.domain.entity.PerfumeEntity;
import com.perfectrum.backend.domain.entity.UserEntity;
import com.perfectrum.backend.domain.repository.PerfumeRepository;
import com.perfectrum.backend.domain.repository.UserRepository;
import com.perfectrum.backend.dto.perfume.PerfumeViewDto;
import com.perfectrum.backend.dto.user.UserInfoDto;
import com.perfectrum.backend.mapper.PerfumeViewMapper;
import com.perfectrum.backend.service.PerfumeService;
import com.perfectrum.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PerfumeServiceImpl implements PerfumeService {
    private PerfumeRepository perfumeRepository;
    private PerfumeViewMapper perfumeViewMapper;
    private UserRepository userRepository;


    @Autowired
    PerfumeServiceImpl(PerfumeRepository perfumeRepository, PerfumeViewMapper perfumeViewMapper, UserRepository userRepository){
        this.perfumeRepository = perfumeRepository;
        this.perfumeViewMapper = perfumeViewMapper;
        this.userRepository = userRepository;
    }
    @Override
    public List<PerfumeViewDto> viewBestPerfume(String decodeId) {
        List<PerfumeViewDto> result = new ArrayList<>();
        Optional<UserEntity> userEntityOptional = userRepository.findByUserId(decodeId);

        if(userEntityOptional.isPresent()){
            UserEntity userEntity = userEntityOptional.get();
            String gender = userEntity.getGender();
            String season = userEntity.getSeasons();
            Integer accordClass = userEntity.getAccordClass();
            // 추가 정보 있음 -> 정보 기반 추천
            if(gender != null){
                List<PerfumeEntity> perfumes = perfumeRepository.findTop6ByGenderAndSeasonsContainsOrderByItemRatingDesc(gender, season);

                for(PerfumeEntity p : perfumes){
                    PerfumeViewDto perfumeViewDto = perfumeViewMapper.toDto(p);
                    result.add(perfumeViewDto);
                }
            }else{ // 추가정보 없음 -> 기본 베스트 향수 추천
                List<PerfumeEntity> perfumes = perfumeRepository.findTop6ByOrderByItemRatingDesc();

                for(PerfumeEntity p : perfumes){
                    PerfumeViewDto perfumeViewDto = perfumeViewMapper.toDto(p);
                    result.add(perfumeViewDto);
                }
            }
        }else{ // 비로그인 상태
            List<PerfumeEntity> perfumes = perfumeRepository.findTop6ByOrderByItemRatingDesc();
            for(PerfumeEntity p : perfumes){
                PerfumeViewDto perfumeViewDto = perfumeViewMapper.toDto(p);
                result.add(perfumeViewDto);
            }
        }
        return result;
    }
}
