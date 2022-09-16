package com.perfectrum.backend.controller;

import com.perfectrum.backend.service.KakaoUserService;
import com.perfectrum.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
public class SocialController {
    private static final String success = "SUCCESS";
    private static final String fail = "FAIL";
    private static final String timeOut = "access-token tieout";
    private static HttpStatus status = HttpStatus.NOT_FOUND;

    private final UserService userService;
    private final KakaoUserService kakaoUserService;

    @Autowired
    SocialController(UserService userService, KakaoUserService kakaoUserService){
        this.userService = userService;
        this.kakaoUserService = kakaoUserService;
    }

    // 카카오 로그인
    @PostMapping("/kakao")
    public ResponseEntity<?> authKakao(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        String authToken = request.getHeader("authToken");
        try{
            String[] rest = kakaoUserService.kakaoLogin(authToken);

        }catch(Exception e){
            resultMap.put("message", fail);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(resultMap, status);
    }
}