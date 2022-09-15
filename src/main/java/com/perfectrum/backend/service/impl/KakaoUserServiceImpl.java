package com.perfectrum.backend.service.impl;

import com.perfectrum.backend.domain.repository.UserRepository;
import com.perfectrum.backend.service.KakaoUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class KakaoUserServiceImpl implements KakaoUserService {
    private UserRepository userRepository;

    @Autowired
    KakaoUserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    @Transactional
    public String[] kakaoLogin(String authToken) {
        return new String[0];
    }
}
