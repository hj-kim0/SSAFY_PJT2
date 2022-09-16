package com.perfectrum.backend.service;

import com.perfectrum.backend.domain.entity.UserEntity;
import com.perfectrum.backend.domain.repository.UserRepository;
import com.perfectrum.backend.dto.user.UserInfoDto;
import com.perfectrum.backend.mapper.UserInfoMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Optional;

@SpringBootTest
//@Transactional
public class UserServiceTest {

    private final UserRepository userRepository;
    private final UserInfoMapper userInfoMapper;

    @Autowired
    UserServiceTest(UserRepository userRepository, UserInfoMapper userInfoMapper){
        this.userRepository = userRepository;
        this.userInfoMapper = userInfoMapper;
    }

    @Test
    public void 내_정보_조회_테스트(){
        // given
        String testId = "kakao123145";

        // when
        UserEntity userEntity = userRepository.findByUserId(testId).get();
        if(userEntity != null){
            UserInfoDto userInfo = userInfoMapper.toDto(userEntity);
            // then
            System.out.println(userInfo.toString());
        }else{
            System.out.println("fail");
        }
    }

    @Test
    public void 회원_가입_추가_정보(){
        // given
        String testId = "kakao123456";
        String gender = "Men";
        String seasons = "winter";
        Integer accordClass = 3;

        // when
        Optional<UserEntity> optionalUser = userRepository.findByUserId(testId);
        if(optionalUser.isPresent()){
            UserEntity user = optionalUser.get();
            user.setGender(gender);
            user.setSeasons(seasons);
            user.setAccordClass(accordClass);

            userRepository.save(user); // 저장
        }else{
            System.out.println("fail");
        }
    }

    @Test
    public void 닉네임_중복_체크(){
        // given
        String nickname = "진진자라";

        // when
        Optional<UserEntity> optionalUser = userRepository.findByNickname(nickname);
        if(optionalUser.isPresent()){
            System.out.println("fail");
        }else{
            System.out.println("okay");
        }
    }
    @Test
    public void 내_정보_수정_테스트(){
        // given
        Integer idx = 4;
        String testId = "kakao123456";
        String nickname = "닉네임이지롱";
        String profileImg = null;
        String gender = "Men";
        String seasons = "summer";
        Integer accordClass = 1;

        UserInfoDto userInfoDto = UserInfoDto.builder()
                .idx(idx)
                .userId(testId)
                .nickname(nickname)
                .profileImg(profileImg)
                .gender(gender)
                .seasons(seasons)
                .accordClass(accordClass)
                .build();

        // when
        Optional<UserEntity> optionalUser = userRepository.findByUserId(testId);
        if(optionalUser.isPresent()){
            UserEntity userEntity = optionalUser.get();
            userInfoMapper.updateFromDto(userInfoDto, userEntity);
            userRepository.save(userEntity);

            // then
            userInfoDto = userInfoMapper.toDto(userEntity);
            System.out.println(userInfoDto.toString());
            System.out.println("okay");
        }else{
            System.out.println("fail");
        }
    }

    @Disabled
    @Test
    public void 회원_탈퇴(){
        String Id = "kakao123456";

        Optional<UserEntity> optionalUser = userRepository.findByUserId(Id);
        if(optionalUser.isPresent()){
            UserEntity userEntity = optionalUser.get();
            userRepository.delete(userEntity);
            System.out.println("okay");
        }else{
            System.out.println("fail");
        }
    }
}
