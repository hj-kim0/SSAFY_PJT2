package com.perfectrum.backend.service;

import com.perfectrum.backend.dto.user.UserInfoDto;
import com.perfectrum.backend.dto.user.UserMoreInfoDto;
import com.perfectrum.backend.dto.user.UserUpdateInfoDto;

public interface UserService {
    UserInfoDto getUserInfo(String decodeId);

    void addMoreUserInfo(String decodeId, UserMoreInfoDto userMoreInfoDto);

    UserInfoDto updateUserInfo(String decodeId, UserUpdateInfoDto userUpdateInfoDto);

    boolean checkNickName(String nickname);

    void deleteUser(String decodeId);
}
