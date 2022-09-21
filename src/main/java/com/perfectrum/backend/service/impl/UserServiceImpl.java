package com.perfectrum.backend.service.impl;

import com.perfectrum.backend.domain.entity.UserEntity;
import com.perfectrum.backend.domain.repository.UserRepository;
import com.perfectrum.backend.dto.user.UserInfoDto;
import com.perfectrum.backend.dto.user.UserMoreInfoDto;
import com.perfectrum.backend.dto.user.UserUpdateInfoDto;
import com.perfectrum.backend.mapper.UserInfoMapper;
import com.perfectrum.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserInfoMapper userInfoMapper;

    @Autowired
    UserServiceImpl(UserRepository userRepository, UserInfoMapper userInfoMapper){
        this.userRepository = userRepository;
        this.userInfoMapper = userInfoMapper;
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
}
