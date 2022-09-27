package com.perfectrum.backend.controller;

import com.perfectrum.backend.domain.entity.PerfumeEntity;
import com.perfectrum.backend.dto.survey.SurveyDto;
import com.perfectrum.backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class SurveyController {
    private static final String success = "SUCCESS";
    private static final String fail = "FAIL";
    private static final String timeOut = "access-token timeout";
    private static HttpStatus status = HttpStatus.NOT_FOUND;

    private JwtService jwtService;
    private WishListService wishListService;
    private HaveListService haveListService;

    private PerfumeDetailService perfumeDetailService;

    private SurveyService surveyService;

    @Autowired
    SurveyController(JwtService jwtService, WishListService wishListService,
                            HaveListService haveListService,PerfumeDetailService perfumeDetailService,
                            SurveyService surveyService){
        this.jwtService = jwtService;
        this.wishListService = wishListService;
        this.haveListService = haveListService;
        this.perfumeDetailService = perfumeDetailService;
        this.surveyService = surveyService;
    }

    @PostMapping("/survey")
    public ResponseEntity<?> surveyResult(HttpServletRequest request, @RequestBody SurveyDto surveyDto){
        Map<String, Object> resultMap = new HashMap<>();
        PerfumeEntity perfume;
        String decodeId = checkToken(request,resultMap);
        try{
            System.out.println("향수 IDX 찾기");
            perfume = surveyService.surveyResult(decodeId,surveyDto);
            System.out.println("향수 IDX");
            System.out.println(perfume.getIdx());
            resultMap.put("perfumeName",perfume.getPerfumeName());
            resultMap.put("perfumeImg",perfume.getPerfumeImg());
            resultMap.put("message",success);
            status = HttpStatus.OK;
        }catch(Exception e){
            resultMap.put("message",fail);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(resultMap,status);
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
