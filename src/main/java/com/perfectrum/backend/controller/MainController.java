package com.perfectrum.backend.controller;

import com.perfectrum.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class MainController {
    private static final String success = "SUCCESS";
    private static final String fail = "FAIL";
    private static final String timeOut = "access-token tieout";
    private static HttpStatus status = HttpStatus.NOT_FOUND;

    private UserService userService;

    @Autowired
    MainController(UserService userService){
        this.userService = userService;
    }

//    private String checkToken(HttpServletRequest request, Map<String, Object> resultMap) {
//        String accessToken = request.getHeader("Authorization");
//        String decodeId = jwtService.decodeToken(accessToken);
//        if(!decodeId.equals("timeout")){
//            return decodeId;
//        }else{
//            resultMap.put("message", timeOut);
//            status = HttpStatus.UNAUTHORIZED;
//            return null;
//        }
//    }

    @GetMapping("/best") // 베스트 향수 조회
    public ResponseEntity<?> viewBestPerfume(HttpServletRequest request){
        Map<String, Object> resultMap = new HashMap<>();

        return null;
    }

    @GetMapping("/")
    public ResponseEntity<?> testPostman(){
        Map<String, Object> resultMap = new HashMap<>();

        try{
            resultMap.put("message", success);
            resultMap.put("data", "성공");

            status = HttpStatus.OK;
        }catch (Exception e){
            resultMap.put("message", fail);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(resultMap, status);
    }
}
