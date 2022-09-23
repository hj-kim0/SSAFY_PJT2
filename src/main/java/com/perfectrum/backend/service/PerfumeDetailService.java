package com.perfectrum.backend.service;

import com.perfectrum.backend.domain.entity.PerfumeEntity;
import com.perfectrum.backend.dto.review.ReviewViewDto;

import java.util.Map;

public interface PerfumeDetailService {

    Map<String,Object> getPerfumeDetail(String decodeId, Integer perfumeIdx);

    void addWishList(String decodeId, Integer perfumeIdx);

    void addHaveList(String decodeId,Integer perfumeIdx);

    void registReview(String decodeId,Integer perfumeIdx, ReviewViewDto review);

    void updateReview(String decodeId,Integer perfumeIdx, Integer reviewIdx, ReviewViewDto review);
}
