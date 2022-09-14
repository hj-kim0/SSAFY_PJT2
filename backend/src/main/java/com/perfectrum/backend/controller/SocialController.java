package com.perfectrum.backend.controller;

import com.perfectrum.backend.service.KakaoUserService;
import com.perfectrum.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SocialController {

    private final UserService userService;
    private final KakaoUserService kakaoUserService;

    @Autowired
    SocialController(UserService userService, KakaoUserService kakaoUserService){
        this.userService = userService;
        this.kakaoUserService = kakaoUserService;
    }
}