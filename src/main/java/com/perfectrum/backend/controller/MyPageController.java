package com.perfectrum.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class MyPageController {
    private static final String success = "SUCCESS";
    private static final String fail = "FAIL";
    private static final String timeOut = "access-token timeout";
    private static HttpStatus status = HttpStatus.NOT_FOUND;


//    @GetMapping("/my-page/wish")
//    public ResponseEntity<?> viewWishList(HttpServletRequest request){
//        Map<String, Object> resultMap = new HashMap<>();
//
//        return new ResponseEntity<>(resultMap, status);
//    }
//
//    @GetMapping("/my-page/wish")
//    public ResponseEntity<?> viewHaveList(HttpServletRequest request){
//        Map<String, Object> resultMap = new HashMap<>();
//
//        return new ResponseEntity<>(resultMap, status);
//    }
}
