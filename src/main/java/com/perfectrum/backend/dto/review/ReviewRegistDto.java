package com.perfectrum.backend.dto.review;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewRegistDto {
    private Integer idx;
    private String reviewImg;
    private Integer totalScore;
    private String content;

}