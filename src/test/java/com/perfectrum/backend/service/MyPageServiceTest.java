package com.perfectrum.backend.service;

import com.perfectrum.backend.domain.entity.HaveListEntity;
import com.perfectrum.backend.domain.entity.PerfumeEntity;
import com.perfectrum.backend.domain.entity.UserEntity;
import com.perfectrum.backend.domain.entity.WishListEntity;
import com.perfectrum.backend.domain.repository.HaveListRepository;
import com.perfectrum.backend.domain.repository.PerfumeRepository;
import com.perfectrum.backend.domain.repository.UserRepository;
import com.perfectrum.backend.domain.repository.WishListRepository;
import com.perfectrum.backend.dto.HaveListDto;
import com.perfectrum.backend.dto.WishListDto;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional
public class MyPageServiceTest {

    private UserRepository userRepository;
    private WishListRepository wishListRepository;
    private HaveListRepository haveListRepository;
    private PerfumeRepository perfumeRepository;

    @Autowired
    MyPageServiceTest(UserRepository userRepository, WishListRepository wishListRepository, HaveListRepository haveListRepository,
                      PerfumeRepository perfumeRepository){
        this.userRepository = userRepository;
        this.wishListRepository = wishListRepository;
        this.haveListRepository = haveListRepository;
        this.perfumeRepository = perfumeRepository;
    }

    @Test
    public void wishList_조회() {
        // given
        String testId = "kakao2435577184";


        // when
        Optional<UserEntity> optionalUserEntity = userRepository.findByUserId(testId);
        if(optionalUserEntity.isPresent()){
            UserEntity userEntity = optionalUserEntity.get();
            List<WishListEntity> list = wishListRepository.findByUser(userEntity);
            List<WishListDto> dtoList = new ArrayList<>();
            if(!list.isEmpty()){
                for(WishListEntity w : list){
                    WishListDto dto = WishListDto.builder()
                            .perfumeIdx(w.getPerfume().getIdx())
                            .perfumeName(w.getPerfume().getPerfumeName())
                            .braneName(w.getPerfume().getBrandName())
                            .perfumeImg(w.getPerfume().getPerfumeImg())
                            .isDelete(w.getIsDelete())
                            .build();

                    dtoList.add(dto);
                }

            }else{
                dtoList = null;
            }

            // then
            if(dtoList != null){
                for(WishListDto w : dtoList) {
                    System.out.println(w.toString());
                }
            }else{
                System.out.println("위시 향수 없음");
            }
        }
    }

    @Disabled
    @Test
    public void wish에서_have로_이동(){
        // given
        String testId = "kakao2435577184";
        Integer idx = 1061; // 위시리스트 idx

        // when
        Optional<UserEntity> optionalUserEntity = userRepository.findByUserId(testId);
        if(optionalUserEntity.isPresent()){
            UserEntity userEntity = optionalUserEntity.get();
            // 1. 보유리스트에 추가로 넣기
            WishListEntity wishListEntity = wishListRepository.findByIdx(idx);
            PerfumeEntity perfumeEntity = perfumeRepository.findByIdx(wishListEntity.getPerfume().getIdx());
            HaveListEntity haveListEntity = HaveListEntity.builder()
                    .user(userEntity)
                    .perfume(perfumeEntity)
                    .build();
            haveListRepository.save(haveListEntity);

            // 2. 위시리스트에 있는 향수 지우기
            WishListEntity updateWishList = WishListEntity.builder().idx(idx).perfume(wishListEntity.getPerfume()).user(wishListEntity.getUser()).isDelete(true).build();
            wishListRepository.save(updateWishList);
      }
    }

    @Test
    public void wishList_삭제(){
        String testId = "kakao2435577184";
        Integer idx = 1060;

        Optional<UserEntity> userEntityOptional = userRepository.findByUserId(testId);
        Optional<WishListEntity> wishListEntity = wishListRepository.findByUserAndIdx(userEntityOptional,idx);

        if(wishListEntity.isPresent()){
            WishListEntity wishList = wishListEntity.get();
            WishListEntity updateWishList = WishListEntity.builder().idx(idx).perfume(wishList.getPerfume()).user(wishList.getUser()).isDelete(true).build();
            wishListRepository.save(updateWishList);
        }
    }

    @Test
    public void haveList_삭제(){
        String testId = "kakao2435577184";
        Integer idx = 228;

        Optional<UserEntity> userEntityOptional = userRepository.findByUserId(testId);
        Optional<HaveListEntity> haveListEntity = haveListRepository.findByUserAndIdx(userEntityOptional,idx);

        if(haveListEntity.isPresent()){
            HaveListEntity haveList = haveListEntity.get();
            HaveListEntity updateHaveList = HaveListEntity.builder().idx(idx).perfume(haveList.getPerfume()).user(haveList.getUser()).isDelete(true).build();
            haveListRepository.save(updateHaveList);
        }
    }

    @Test
    public void haveList_조회(){
        String testId = "kakao2435577184";

        Optional<UserEntity> optionalUserEntity = userRepository.findByUserId(testId);
        if(optionalUserEntity.isPresent()){
            UserEntity userEntity = optionalUserEntity.get();
            List<HaveListEntity> list = haveListRepository.findByUser(userEntity);
            List<HaveListDto> dtoList = new ArrayList<>();

            if(!list.isEmpty()){
                for(HaveListEntity h : list){
                    HaveListDto dto = HaveListDto.builder()
                            .perfumeIdx(h.getPerfume().getIdx())
                            .perfumeName(h.getPerfume().getPerfumeName())
                            .braneName(h.getPerfume().getBrandName())
                            .perfumeImg(h.getPerfume().getPerfumeImg())
                            .isDelete(h.getIsDelete())
                            .build();

                    dtoList.add(dto);
                }
            }else{
                dtoList = null;
            }

            if(dtoList != null){
                for(HaveListDto h : dtoList){
                    System.out.println(h.toString());
                }
            }else{
                System.out.println("보유한 향수 없음");
            }
        }
    }
}
