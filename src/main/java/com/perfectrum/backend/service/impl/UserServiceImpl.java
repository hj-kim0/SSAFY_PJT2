package com.perfectrum.backend.service.impl;

import com.perfectrum.backend.domain.entity.ReviewEntity;
import com.perfectrum.backend.domain.entity.UserEntity;
import com.perfectrum.backend.domain.repository.ReviewRepository;
import com.perfectrum.backend.domain.repository.UserRepository;
import com.perfectrum.backend.dto.review.MyReviewDto;
import com.perfectrum.backend.dto.user.UserInfoDto;
import com.perfectrum.backend.dto.user.UserMoreInfoDto;
import com.perfectrum.backend.dto.user.UserUpdateInfoDto;
import com.perfectrum.backend.mapper.UserInfoMapper;
import com.perfectrum.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserInfoMapper userInfoMapper;
    private final ReviewRepository reviewRepository;

    @Autowired
    UserServiceImpl(UserRepository userRepository, UserInfoMapper userInfoMapper, ReviewRepository reviewRepository){
        this.userRepository = userRepository;
        this.userInfoMapper = userInfoMapper;
        this.reviewRepository = reviewRepository;
    }
    @Override
    public UserInfoDto getUserInfo(String decodeId) {
        Optional<UserEntity> userEntityOptional = userRepository.findByUserId(decodeId);
        if(userEntityOptional.isPresent()){
            UserEntity userEntity = userEntityOptional.get();

            return userInfoMapper.toDto(userEntity);
        }
        return null;
    }

    @Override
    public void addMoreUserInfo(String decodeId, UserMoreInfoDto userMoreInfoDto) {
        String gender = userMoreInfoDto.getGender();
        String seasons = userMoreInfoDto.getSeasons();
        Integer accorClass = userMoreInfoDto.getAccordClass();

        Optional<UserEntity> userEntityOptional = userRepository.findByUserId(decodeId);
        if(userEntityOptional.isPresent()){
            UserEntity user = userEntityOptional.get();
            user.setGender(gender);
            user.setSeasons(seasons);
            user.setAccordClass(accorClass);

            userRepository.save(user);
        }
    }

    @Override
    public UserInfoDto updateUserInfo(String decodeId, UserUpdateInfoDto userUpdateInfoDto) {
        String nickname = userUpdateInfoDto.getNickname();
        String profileImg = userUpdateInfoDto.getProfileImg();
        String gender = userUpdateInfoDto.getGender();
        String seasons = userUpdateInfoDto.getSeasons();
        Integer accordClass = userUpdateInfoDto.getAccordClass();

        Optional<UserEntity> userEntity = userRepository.findByUserId(decodeId);
        if(userEntity.isPresent()){
            UserEntity user = userEntity.get();
            UserInfoDto userInfoDto = UserInfoDto.builder()
                    .idx(user.getIdx())
                    .userId(user.getUserId())
                    .profileImg(profileImg)
                    .nickname(nickname)
                    .gender(gender)
                    .seasons(seasons)
                    .accordClass(accordClass)
                    .build();

            userInfoMapper.updateFromDto(userInfoDto, user);
            userRepository.save(user);

            return userInfoDto;
        }
        return null;
    }

    @Override
    public boolean checkNickName(String nickname) {
        return userRepository.findByNickname(nickname).isPresent();
    }

    @Override
    public void deleteUser(String decodeId) {
        UserEntity user = userRepository.findByUserId(decodeId).get();
        userRepository.delete(user);
    }

    @Override
    public List<MyReviewDto> viewMyReviews(String decodeId) {
        Optional<UserEntity> userEntityOptional = userRepository.findByUserId(decodeId);
        if(userEntityOptional.isPresent()){
            UserEntity user = userEntityOptional.get();
            List<MyReviewDto> myReviewList = new ArrayList<>();

            List<ReviewEntity> reviewEntityList = reviewRepository.findByUser(user);
            if(!reviewEntityList.isEmpty()){
                for(ReviewEntity r : reviewEntityList){

                    MyReviewDto myReviewDto = MyReviewDto.builder()
                            .idx(r.getIdx())
                            .perfumeIdx(r.getPerfume().getIdx())
                            .perfumeName(r.getPerfume().getPerfumeName())
                            .reviewImg(r.getReviewImg())
                            .totalScore(r.getTotalScore())
                            .longevity(r.getLongevity())
                            .sillageScore(r.getSillageScore())
                            .content(r.getContent())
                            .time(r.getTime())
                            .updateTime(r.getUpdateTime())
                            .build();

                    myReviewList.add(myReviewDto);
                }
                return myReviewList;
            }
        }
        return null;
    }
}