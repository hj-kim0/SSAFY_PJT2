package com.perfectrum.backend.service;

import com.perfectrum.backend.domain.entity.ReviewEntity;
import com.perfectrum.backend.domain.entity.UserEntity;
import com.perfectrum.backend.domain.repository.ReviewRepository;
import com.perfectrum.backend.domain.repository.UserRepository;
import com.perfectrum.backend.dto.review.MyReviewDto;
import com.perfectrum.backend.dto.user.UserInfoDto;
import com.perfectrum.backend.mapper.UserInfoMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
//@Transactional
public class UserServiceTest {

    private final UserRepository userRepository;
    private final UserInfoMapper userInfoMapper;
    private final ReviewRepository reviewRepository;

    @Autowired
    UserServiceTest(UserRepository userRepository, UserInfoMapper userInfoMapper, ReviewRepository reviewRepository){
        this.userRepository = userRepository;
        this.userInfoMapper = userInfoMapper;
        this.reviewRepository = reviewRepository;
    }

    @Test
    public void 내_정보_조회_테스트(){
        // given
        String testId = "kakao123145";

        // when
        UserEntity userEntity = userRepository.findByUserId(testId).get();
        if(userEntity != null){
            UserInfoDto userInfo = userInfoMapper.toDto(userEntity);
            // then
            System.out.println(userInfo.toString());
        }else{
            System.out.println("fail");
        }
    }

    @Test
    public void 회원_가입_추가_정보(){
        // given
        String testId = "kakao123456";
        String gender = "Men";
        String seasons = "winter";
        Integer accordClass = 3;

        // when
        Optional<UserEntity> optionalUser = userRepository.findByUserId(testId);
        if(optionalUser.isPresent()){
            UserEntity user = optionalUser.get();
            user.setGender(gender);
            user.setSeasons(seasons);
            user.setAccordClass(accordClass);

            userRepository.save(user); // 저장
        }else{
            System.out.println("fail");
        }
    }

    @Test
    public void 닉네임_중복_체크(){
        // given
        String nickname = "진진자라";

        // when
        Optional<UserEntity> optionalUser = userRepository.findByNickname(nickname);
        if(optionalUser.isPresent()){
            System.out.println("fail");
        }else{
            System.out.println("okay");
        }
    }
    @Test
    public void 내_정보_수정_테스트(){
        // given
        Integer idx = 4;
        String testId = "kakao123456";
        String nickname = "닉네임이지롱";
        String profileImg = null;
        String gender = "Men";
        String seasons = "summer";
        Integer accordClass = 1;

        UserInfoDto userInfoDto = UserInfoDto.builder()
                .idx(idx)
                .userId(testId)
                .nickname(nickname)
                .profileImg(profileImg)
                .gender(gender)
                .seasons(seasons)
                .accordClass(accordClass)
                .build();

        // when
        Optional<UserEntity> optionalUser = userRepository.findByUserId(testId);
        if(optionalUser.isPresent()){
            UserEntity userEntity = optionalUser.get();
            userInfoMapper.updateFromDto(userInfoDto, userEntity);
            userRepository.save(userEntity);

            // then
            userInfoDto = userInfoMapper.toDto(userEntity);
            System.out.println(userInfoDto.toString());
            System.out.println("okay");
        }else{
            System.out.println("fail");
        }
    }

    @Disabled
    @Test
    public void 회원_탈퇴(){
        String testId = "kakao123456";

        Optional<UserEntity> optionalUser = userRepository.findByUserId(testId);
        if(optionalUser.isPresent()){
            UserEntity userEntity = optionalUser.get();
            userRepository.delete(userEntity);
            System.out.println("okay");
        }else{
            System.out.println("fail");
        }
    }

    @Disabled
    @Test
    public void 작성한_리뷰_조회_테스트(){
        String testId = "kakao2435577184";

        Optional<UserEntity> optionalUser = userRepository.findByUserId(testId);

        if(optionalUser.isPresent()){
            UserEntity userEntity = optionalUser.get();
            List<MyReviewDto> myReviewList = new ArrayList<>();

            List<ReviewEntity> reviewEntityList = reviewRepository.findByUser(userEntity);
            if(!reviewEntityList.isEmpty()){
                for(ReviewEntity r : reviewEntityList){
                    MyReviewDto myReviewDto = MyReviewDto.builder()
                            .idx(r.getIdx())
                            .perfumeIdx(r.getPerfume().getIdx())
                            .perfumeName(r.getPerfume().getPerfumeName())
                            .reviewImg(r.getReviewImg())
                            .totalScore(r.getTotalScore())
                            .longevity(r.getLongevity())
                            .sillageScore(r.getSillageScore())
                            .content(r.getContent())
                            .time(r.getTime())
                            .updateTime(r.getUpdateTime())
                            .build();

                    myReviewList.add(myReviewDto);
                }
                for(MyReviewDto d : myReviewList){
                    System.out.println(d.toString());
                }
            }else{
                System.out.println("리뷰 없음");
            }
        }
    }

    @Test
    public void 리뷰_전체_개수(){
        String testId = "kakao123145";

        Optional<UserEntity> optionalUserEntity = userRepository.findByUserId(testId);
        if(optionalUserEntity.isPresent()){
            UserEntity userEntity = optionalUserEntity.get();
            Integer count = reviewRepository.countByUser(userEntity);

            System.out.println("======== 리뷰 개수 ========");
            System.out.println(count);
        }
    }

//    @Test
//    public void 리뷰_전체_평점() {
//        String testId = "kakao123145";
//
//        Optional<UserEntity> optionalUserEntity = userRepository.findByUserId(testId);
//        if(optionalUserEntity.isPresent()){
//            UserEntity userEntity = optionalUserEntity.get();
//
//            int count = reviewRepository.countByUser(userEntity);
//            Double total_score = reviewRepository.sumByUser(userEntity);
//
//            Double avg_score = total_score/count;
//            System.out.println(avg_score);
//        }
//    }
}
