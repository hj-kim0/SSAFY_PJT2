package com.perfectrum.backend.service;

import java.io.IOException;

public interface KakaoUserService {
    String[] kakaoLogin(String authToken);

    String getKakaoAccessToken(String code) throws IOException;

    String[] createKakaoUser(String accessToken);
}
