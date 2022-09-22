package com.perfectrum.backend.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class WishListDto {
    Integer idx;
    Integer perfumeIdx;
    String perfumeName;
    String braneName;
    String perfumeImg;

    Boolean isDelete;
}
