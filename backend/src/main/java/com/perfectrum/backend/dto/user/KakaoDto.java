package com.perfectrum.backend.dto.user;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KakaoDto {
    private Long id;
    private KakaoAccount kakao_account;
    private String test;

    @Getter
    @ToString
    public static class KakaoAccount{
        private String email;
    }

}
