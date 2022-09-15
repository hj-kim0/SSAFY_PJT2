package com.perfectrum.backend.service;

import com.perfectrum.backend.domain.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
public class UserServiceTest {

    private final UserRepository userRepository;

    @Autowired
    UserServiceTest(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Test
    public void 회원_가입_테스트(){

    }
}
