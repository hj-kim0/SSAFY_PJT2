package com.perfectrum.backend.service;

import com.perfectrum.backend.dto.HaveListDto;

import java.util.List;

public interface HaveListService {
    List<HaveListDto> viewHaveList(String decodeId);

    void deleteHaveList(String decodeId, Integer idx);
}
