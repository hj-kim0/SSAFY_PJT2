package com.perfectrum.backend.controller;


import com.perfectrum.backend.domain.entity.PerfumeEntity;
import com.perfectrum.backend.dto.review.ReviewListDto;
import com.perfectrum.backend.dto.review.ReviewRegistDto;
import com.perfectrum.backend.dto.review.ReviewViewDto;
import com.perfectrum.backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class PerfumeDetailController {

    private static final String success = "SUCCESS";
    private static final String fail = "FAIL";
    private static final String timeOut = "access-token timeout";
    private static HttpStatus status = HttpStatus.NOT_FOUND;

    private JwtService jwtService;
    private WishListService wishListService;
    private HaveListService haveListService;

    private PerfumeDetailService perfumeDetailService;

    @Autowired
    PerfumeDetailController(JwtService jwtService, WishListService wishListService,
                            HaveListService haveListService,PerfumeDetailService perfumeDetailService){
        this.jwtService = jwtService;
        this.wishListService = wishListService;
        this.haveListService = haveListService;
        this.perfumeDetailService = perfumeDetailService;
    }

    @PostMapping("/detail/{idx}")
    public ResponseEntity<?> getPerfumeDetail(HttpServletRequest request, @PathVariable("idx") Integer perfumeIdx, @RequestBody ReviewListDto reviewListDto){
        Map<String,Object> resultMap = new HashMap<>();
        String decodeId = "isLogin";

        if(request != null && request.getHeader("Authorization") != null){
            decodeId = checkToken(request, resultMap);
        }

        if(decodeId != null){
            try{
                Map<String, Object> data = new HashMap<>();
                System.out.println("컨트롤러 진입");
                data = perfumeDetailService.getPerfumeDetail(decodeId,perfumeIdx,reviewListDto);
//                resultMap.putAll(perfumeDetailService.getPerfumeDetail(decodeId,perfumeIdx,reviewListDto));
                resultMap.put("perfume",data.get("perfume"));
                resultMap.put("hasNext",data.get("hasNext"));
                resultMap.put("reviewList",data.get("reviewList"));
                resultMap.put("accordList",data.get("perfumeAccordList"));
                resultMap.put("message",success);
                status = HttpStatus.OK;
            }catch(Exception e){
                resultMap.put("message",fail);
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }

        }

        return new ResponseEntity<>(resultMap,status);
    }

    @GetMapping("/detail/accord/{idx}")
    public ResponseEntity<?> getAccordMoreInfo(HttpServletRequest request, @PathVariable("idx") Integer accordIdx){
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> data = new HashMap<>();
        try{
            data = perfumeDetailService.getAcoordMoreInfo(accordIdx);
            resultMap.put("accordMoreInfo",data.get("accordMoreInfo"));
            resultMap.put("message",success);
            status = HttpStatus.OK;
        }catch(Exception e){
            resultMap.put("message",fail);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(resultMap,status);
    }
    @GetMapping("detail/wish/{idx}")
    public ResponseEntity<?> addWishList(HttpServletRequest request, @PathVariable("idx") Integer perfumeIdx){
        Map<String,Object> resultMap = new HashMap<>();
        String decodeId = checkToken(request, resultMap);
        if(decodeId != null){
            try{
                Map<String, Object> result = perfumeDetailService.addWishList(decodeId,perfumeIdx);
                resultMap.put("isClicked", result.get("isClicked"));
                resultMap.put("message",success);
                status = HttpStatus.OK;
            }catch(Exception e){
                resultMap.put("message",fail);
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
        }

        return new ResponseEntity<>(resultMap,status);
    }


    @GetMapping("detail/have/{idx}")
    public ResponseEntity<?> addHaveList(HttpServletRequest request,@PathVariable("idx") Integer perfumeIdx){
        Map<String,Object> resultMap = new HashMap<>();
        String decodeId = checkToken(request, resultMap);
        if(decodeId != null){
            try{
                Map<String, Object> result = perfumeDetailService.addHaveList(decodeId,perfumeIdx);
                resultMap.put("isClicked", result.get("isClicked"));
                if(result.get("isWishClicked") != null) resultMap.put("isWishClicked", result.get("isWishClicked"));
                resultMap.put("message",success);
                status = HttpStatus.OK;
            }catch(Exception e){
                resultMap.put("message",fail);
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
        }

        return new ResponseEntity<>(resultMap,status);
    }

    @PostMapping("detail/{idx}/review")
    public ResponseEntity<?> writeReview(HttpServletRequest request, @PathVariable("idx") Integer perfumeIdx, @RequestBody ReviewRegistDto reviewRegistDto){
        Map<String,Object> resultMap = new HashMap<>();
        String decodeId = checkToken(request,resultMap);
        if(decodeId != null){
            try{
                perfumeDetailService.registReview(decodeId,perfumeIdx,reviewRegistDto);
                resultMap.put("message",success);
                status = HttpStatus.OK;
            }catch(Exception e){
                resultMap.put("message", fail);
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
        }

        return new ResponseEntity<>(resultMap,status);
    }

    @PutMapping("detail/{idx}/review/{review_idx}")
    public ResponseEntity<?> updateReview(HttpServletRequest request, @PathVariable("idx") Integer perfumeIdx,@PathVariable("review_idx") Integer reviewIdx, @RequestBody ReviewRegistDto reviewRegistDto){
        Map<String,Object> resultMap = new HashMap<>();
        String decodeId = checkToken(request,resultMap);
        if(decodeId != null){
            try{
                perfumeDetailService.updateReview(decodeId,perfumeIdx,reviewIdx,reviewRegistDto);
                resultMap.put("message",success);
                status = HttpStatus.OK;
            }catch(Exception e){
                resultMap.put("message", fail);
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
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