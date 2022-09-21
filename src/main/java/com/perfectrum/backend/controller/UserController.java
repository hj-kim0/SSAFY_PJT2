package com.perfectrum.backend.controller;

import com.perfectrum.backend.dto.user.UserMoreInfoDto;
import com.perfectrum.backend.dto.user.UserInfoDto;
import com.perfectrum.backend.dto.user.UserUpdateInfoDto;
import com.perfectrum.backend.service.JwtService;
import com.perfectrum.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    private static final String success = "SUCCESS";
    private static final String fail = "FAIL";
    private static final String timeOut = "access-token timeout";
    private static HttpStatus status = HttpStatus.NOT_FOUND;

    private final UserService userService;
    private final JwtService jwtService;
    @Autowired
    UserController(UserService userService, JwtService jwtService){
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @GetMapping("/profile") // 내 정보 조회
    public ResponseEntity<?> getUserInfo(HttpServletRequest request){
        Map<String, Object> resultMap = new HashMap<>();
        String decodeId = checkToken(request, resultMap);

        if(decodeId != null){
            try{
                UserInfoDto userInfo = userService.getUserInfo(decodeId);
                if(userInfo != null){
                    resultMap.put("data", userInfo);
                    resultMap.put("message", success);
                    status = HttpStatus.OK;
                }else{
                    resultMap.put("message", fail);
                    status = HttpStatus.INTERNAL_SERVER_ERROR;
                }
            }catch (Exception e){
                resultMap.put("message", fail);
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
        }
        return new ResponseEntity<>(resultMap, status);
    }

    @PostMapping("/profile") // 추가 정보 입력
    public ResponseEntity<?> addMoreUserInfo(HttpServletRequest request, @RequestBody UserMoreInfoDto userMoreInfoDto){
        Map<String, Object> resultMap = new HashMap<>();
        String decodeId = checkToken(request, resultMap);

        if(decodeId != null){
            try{
                userService.addMoreUserInfo(decodeId, userMoreInfoDto);
                resultMap.put("message", success);
                status = HttpStatus.OK;
            }catch (Exception e){
                resultMap.put("message", fail);
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
        }

        return new ResponseEntity<>(resultMap, status);
    }

    @GetMapping("/profile/check/{nickname}") // 닉네임 체크
    public ResponseEntity<?> checkNickName(@PathVariable String nickname){
        Map<String, Object> resultMap = new HashMap<>();
        try {
            if(userService.checkNickName(nickname)){ // 닉네임 서비스 호출
                resultMap.put("message", fail); // true이면 중복
            } else{
                resultMap.put("message", success);
            }
            status = HttpStatus.OK;
        }catch (Exception e){
            resultMap.put("message", fail);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(resultMap, status);
    }

    @PutMapping("/profile") // 회원 정보 수정
    public ResponseEntity<?> updateUserInfo(HttpServletRequest request, @RequestBody UserUpdateInfoDto userUpdateInfoDto){
        Map<String, Object> resultMap = new HashMap<>();
        String decodeId = checkToken(request, resultMap);

        if(decodeId != null){
            try{
                UserInfoDto userInfoDto = userService.updateUserInfo(decodeId, userUpdateInfoDto);
                resultMap.put("data", userInfoDto);
                resultMap.put("message", success);
                status = HttpStatus.OK;
            }catch (Exception e){
                resultMap.put("message", fail);
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
        }

        return new ResponseEntity<>(resultMap, status);
    }

    @DeleteMapping("/profile") // 회원 탈퇴
    public ResponseEntity<?> deleteUser(HttpServletRequest request){
        Map<String, Object> resultMap = new HashMap<>();
        String decodeId = checkToken(request, resultMap);

        if(decodeId != null){
            try {
                userService.deleteUser(decodeId);
                resultMap.put("message", success);
                status = HttpStatus.OK;
            } catch (Exception e){
                resultMap.put("message", fail);
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
        }

        return new ResponseEntity<>(resultMap, status);
    }

    public String checkToken(HttpServletRequest request, Map<String, Object> resultMap){
        String accessToken = request.getHeader("Authorization");
        String decodeId = jwtService.decodeToken(accessToken);
        if(!decodeId.equals("timeout")){
            return decodeId;
        }else{
            resultMap.put("message", timeOut);
            status = HttpStatus.UNAUTHORIZED;
            return null;
        }
    }
}
