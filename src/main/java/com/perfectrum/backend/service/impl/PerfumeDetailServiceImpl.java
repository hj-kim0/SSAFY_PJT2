package com.perfectrum.backend.service.impl;

import com.perfectrum.backend.dto.perfume.AccordInfoDto;
import com.perfectrum.backend.dto.perfume.AccordMoreInfoDto;
import com.perfectrum.backend.dto.perfume.PerfumeAccordsDto;
import com.perfectrum.backend.dto.perfume.PerfumeViewDto;
import com.perfectrum.backend.dto.review.ReviewListDto;
import com.perfectrum.backend.dto.review.ReviewRegistDto;
import com.perfectrum.backend.dto.review.ReviewViewDto;
import com.perfectrum.backend.domain.entity.*;
import com.perfectrum.backend.domain.repository.*;
import com.perfectrum.backend.service.PerfumeDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;


@Service
public class PerfumeDetailServiceImpl implements PerfumeDetailService {

    private static final String success = "SUCCESS";
    private static final String fail = "FAIL";
    private static final String timeOut = "access-token timeout";

    private UserRepository userRepository;
    private PerfumeRepository perfumeRepository;
    private AccordClassRepository accordClassRepository;

    private ReviewRepository reviewRepository;

    private UserDetailLogRepository userDetailLogRepository;
    private HaveListRepository haveListRepository;
    private WishListRepository wishListRepository;
    private UserAccordClassRepository userAccordClassRepository;

    private AccordRepository accordRepository;

    @Autowired
    PerfumeDetailServiceImpl(UserRepository userRepository, PerfumeRepository perfumeRepository,
                             AccordClassRepository accordClassRepository, ReviewRepository reviewRepository,
                             UserDetailLogRepository userDetailLogRepository, UserAccordClassRepository userAccordClassRepository,
                             HaveListRepository haveListRepository, WishListRepository wishListRepository,
                             AccordRepository accordRepository) {
        this.userRepository = userRepository;
        this.perfumeRepository = perfumeRepository;
        this.accordClassRepository = accordClassRepository;
        this.reviewRepository = reviewRepository;
        this.userDetailLogRepository = userDetailLogRepository;
        this.haveListRepository = haveListRepository;
        this.wishListRepository = wishListRepository;
        this.userAccordClassRepository = userAccordClassRepository;
        this.accordRepository = accordRepository;
    }

    @Override
    public Map<String, Object> getPerfumeDetail(String decodeId, Integer perfumeIdx, ReviewListDto reviewListDto) {
        Map<String, Object> data = new HashMap<>();
        Optional<UserEntity> user = userRepository.findByUserId(decodeId);
        List<ReviewViewDto> reviewList = new ArrayList<>();
        List<AccordEntity> accordList;
        List<AccordInfoDto> aList = new ArrayList<>();
        String type = reviewListDto.getType();
        Integer lastIdx = reviewListDto.getLastIdx();
        Integer lastTotalScore = reviewListDto.getLastTotalScore();
        Integer lastLikeCount = reviewListDto.getLastLikeCount();
        Integer pageSize = reviewListDto.getPageSize();

        Pageable pageable = Pageable.ofSize(pageSize);

        PerfumeEntity perfume = perfumeRepository.findByIdx(perfumeIdx);
        accordList = perfumeRepository.findByPerfume(perfume);
//
        Integer haveCount = Long.valueOf(Optional.ofNullable(haveListRepository.countByPerfumeIdx(perfumeIdx)).orElse(0L)).intValue();

        Integer wishCount = Long.valueOf(Optional.ofNullable(haveListRepository.countByPerfumeIdx(perfumeIdx)).orElse(0L)).intValue();
//
        PerfumeViewDto perfumeViewDto = PerfumeViewDto.builder()
                .idx(perfumeIdx)
                .brandName(perfume.getBrandName())
                .perfumeName(perfume.getPerfumeName())
                .concentration(perfume.getConcentration())
                .gender(perfume.getGender())
                .scent(perfume.getScent())
                .topNotes(perfume.getTopNotes())
                .middleNotes(perfume.getMiddleNotes())
                .baseNotes(perfume.getBaseNotes())
                .itemRating(perfume.getItemRating())
                .perfumeImg(perfume.getPerfumeImg())
                .description(perfume.getDescription())
                .seasons(perfume.getSeasons())
                .timezone(perfume.getTimezone())
                .longevity(perfume.getLongevity())
                .sillage(perfume.getSillage())
                .wishCount(wishCount)
                .haveCount(haveCount)
                .build();

        data.put("perfume", perfumeViewDto);

//        for (AccordEntity ae : accordList) {
//            PerfumeAccordsDto pad = PerfumeAccordsDto.builder()
////                    .accordIdx(ae.getIdx())
//                    .accordName(ae.getAccordName())
//                    .build();
//
//            aList.add(pad);
//        }

        for(AccordEntity ae : accordList) {
            AccordInfoDto aid = AccordInfoDto.builder()
                    .accordIdx(ae.getIdx())
                    .accordName(ae.getAccordName())
                    .build();
            aList.add(aid);
        }
        data.put("perfumeAccordList", aList);
        Slice<ReviewEntity> reviews = reviewRepository.findByPerfume(perfume);
        if (!reviews.isEmpty()) {
            if (lastIdx == null) {
                // 현재까지 화면에 표시된 리뷰중 마지막 리뷰의 idx를 가져와서
                // 그 다음 리뷰를 추가로 가져오는데 처음 화면을 표시할 경우 idx를
                // 알 수 없으므로 현재 등록된 리뷰 중 가장 큰 idx값을 기본으로 설정함
                lastIdx = reviewRepository.findTop1ByPerfumeOrderByIdxDesc(perfume).getIdx() + 1;
                lastTotalScore = reviewRepository.findTop1ByPerfumeOrderByIdxDesc(perfume).getIdx();
                lastLikeCount = reviewRepository.findTop1ByPerfumeOrderByIdxDesc(perfume).getIdx();
            }

            if (type.equals("평점순")) {
                reviews = reviewRepository.findByPerfumeOrderTotalScoreDescIdxDesc(perfume, lastIdx, lastTotalScore, pageable);
            } else if (type.equals("최신순")) {
                reviews = reviewRepository.findByPerfumeOrderByIdxDesc(perfume, lastIdx, pageable);
            } else { // 공감순 or null
                reviews = reviewRepository.findByPerfumeOrderByLikeCountDescIdxDesc(perfume, lastIdx, lastLikeCount, pageable);

            }
        }
        boolean hasNext = reviews.hasNext();
        data.put("hasNext", hasNext);

        for (ReviewEntity re : reviews) {
            ReviewViewDto reviewViewDto = ReviewViewDto.builder()
                    .idx(re.getIdx())
                    .userNickname(user.get().getNickname())
                    .userProfileimg(user.get().getProfileImg())
                    .perfumeName(re.getPerfume().getPerfumeName())
                    .reviewImg(re.getReviewImg())
                    .totalScore(re.getTotalScore())
                    .longevity(re.getLongevity())
                    .sillageScore(re.getSillageScore())
                    .content(re.getContent())
                    .likeCount(re.getLikeCount())
                    .time(re.getTime())
                    .updateTime(re.getUpdateTime())
                    .build();
            reviewList.add(reviewViewDto);
        }
        data.put("reviewList", reviewList);

        if(user.isPresent()){
            UserDetailLogEntity userDetailLogEntity = new UserDetailLogEntity().builder()
                    .user(user.get())
                    .perfume(perfume)
                    .build();
            userDetailLogRepository.save(userDetailLogEntity);
        }
        return data;
    }

    @Override
    public Map<String,Object> getAcoordMoreInfo(Integer accordIdx){
        Map<String,Object> data = new HashMap<>();
        AccordEntity ae = accordRepository.findByIdx(accordIdx);

        AccordMoreInfoDto accordMoreInfoDto = AccordMoreInfoDto.builder()
                .accordImg(ae.getAccordImg())
                .accordDescription(ae.getAccordDescription())
                .build();

        data.put("accordMoreInfo",accordMoreInfoDto);

        return data;

    }

    @Override
    public Map<String, Object> addWishList(String decodeId, Integer perfumeIdx) {
        Optional<UserEntity> userOptional = userRepository.findByUserId(decodeId);
        Map<String, Object> result = new HashMap<>();

        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();
            PerfumeEntity perfume = perfumeRepository.findByIdx(perfumeIdx);

            Optional<WishListEntity> wishListOptional = wishListRepository.findByUserAndPerfumeAndIsDelete(user, perfume, false);

            if (wishListOptional.isPresent()) {
                WishListEntity wishList = WishListEntity.builder()
                        .idx(wishListOptional.get().getIdx())
                        .user(user)
                        .perfume(perfume)
                        .isDelete(true)
                        .build();
                wishListRepository.save(wishList);
                List<AccordClassEntity> accordClassEntities = accordClassRepository.findByPerfumeAccordClass(perfume);
                for (AccordClassEntity a : accordClassEntities) {
                    Optional<UserAccordClassEntity> userAccordClass = userAccordClassRepository.findByUserAndAccordClass(user, a);
                    if (userAccordClass.isPresent()) {
                        UserAccordClassEntity updateUserAccordClass = UserAccordClassEntity.builder()
                                .idx(userAccordClass.get().getIdx())
                                .user(userAccordClass.get().getUser())
                                .accordClass(userAccordClass.get().getAccordClass())
                                .accordClassCount(userAccordClass.get().getAccordClassCount() - 1)
                                .build();

                        userAccordClassRepository.save(updateUserAccordClass);
                    }
                }
                result.put("isClicked", "false");
            } else { // 없음 -> db에 등록
                WishListEntity wishList = WishListEntity.builder()
                        .user(user)
                        .perfume(perfume)
                        .build();
                wishListRepository.save(wishList);

                List<AccordClassEntity> accordClassEntity = accordClassRepository.findByPerfumeAccordClass(perfume);
                for (AccordClassEntity a : accordClassEntity) {
                    Optional<UserAccordClassEntity> userAccordClass = userAccordClassRepository.findByUserAndAccordClass(user, a);
                    // DB 존재 -> cnt+1 수정
                    if (userAccordClass.isPresent()) {
                        UserAccordClassEntity updateUserAccordClass = UserAccordClassEntity.builder()
                                .idx(userAccordClass.get().getIdx())
                                .user(userAccordClass.get().getUser())
                                .accordClass(userAccordClass.get().getAccordClass())
                                .accordClassCount(userAccordClass.get().getAccordClassCount() + 1)
                                .build();
                        userAccordClassRepository.save(updateUserAccordClass);

                    } else { // DB에 삽입
                        UserAccordClassEntity userAccordClassEntity = UserAccordClassEntity.builder()
                                .user(user)
                                .accordClass(a)
                                .build();
                        userAccordClassRepository.save(userAccordClassEntity);
                    }
                }
                result.put("isClicked", "true");
            }
        }
        return result;
    }

    @Override
    public Map<String, Object> addHaveList(String decodeId, Integer perfumeIdx) {
        Map<String, Object> result = new HashMap<>();
        Optional<UserEntity> userOptional = userRepository.findByUserId(decodeId);
        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();
            PerfumeEntity perfume = perfumeRepository.findByIdx(perfumeIdx);

            Optional<WishListEntity> wishListOptional = wishListRepository.findByUserAndPerfumeAndIsDelete(user, perfume, false);

            // 이미 위시에 담겨져있음 -> 위시에서 지우고 보유에 등록
            if (wishListOptional.isPresent()) {
                WishListEntity wishList = WishListEntity.builder()
                        .idx(wishListOptional.get().getIdx())
                        .user(user)
                        .perfume(perfume)
                        .isDelete(true)
                        .build();
                wishListRepository.save(wishList);

                HaveListEntity haveList = HaveListEntity.builder()
                        .user(user)
                        .perfume(perfume)
                        .build();
                haveListRepository.save(haveList);
                result.put("isWishClicked", "false");
                result.put("isClicked", "true");
            } else { // 없음 -> 바로 DB 등록
                Optional<HaveListEntity> haveListOptional = haveListRepository.findByUserAndPerfumeAndIsDelete(user, perfume, false);

                if (haveListOptional.isPresent()) {
                    HaveListEntity haveList = HaveListEntity.builder()
                            .idx(haveListOptional.get().getIdx())
                            .user(user)
                            .perfume(perfume)
                            .isDelete(true)
                            .build();
                    haveListRepository.save(haveList);
                    List<AccordClassEntity> accordClassEntities = accordClassRepository.findByPerfumeAccordClass(perfume);
                    for (AccordClassEntity a : accordClassEntities) {
                        Optional<UserAccordClassEntity> userAccordClass = userAccordClassRepository.findByUserAndAccordClass(user, a);
                        if (userAccordClass.isPresent()) {
                            UserAccordClassEntity updateUserAccordClass = UserAccordClassEntity.builder()
                                    .idx(userAccordClass.get().getIdx())
                                    .user(userAccordClass.get().getUser())
                                    .accordClass(userAccordClass.get().getAccordClass())
                                    .accordClassCount(userAccordClass.get().getAccordClassCount() - 1)
                                    .build();

                            userAccordClassRepository.save(updateUserAccordClass);
                        }
                    }
                    result.put("isClicked", "false");
                } else {
                    HaveListEntity haveList = HaveListEntity.builder()
                            .user(user)
                            .perfume(perfume)
                            .build();
                    haveListRepository.save(haveList);

                    List<AccordClassEntity> accordClassEntity = accordClassRepository.findByPerfumeAccordClass(perfume);
                    for (AccordClassEntity a : accordClassEntity) {
                        Optional<UserAccordClassEntity> userAccordClass = userAccordClassRepository.findByUserAndAccordClass(user, a);
                        // DB 존재 -> cnt+1 수정
                        if (userAccordClass.isPresent()) {
                            UserAccordClassEntity updateUserAccordClass = UserAccordClassEntity.builder()
                                    .idx(userAccordClass.get().getIdx())
                                    .user(userAccordClass.get().getUser())
                                    .accordClass(userAccordClass.get().getAccordClass())
                                    .accordClassCount(userAccordClass.get().getAccordClassCount() + 1)
                                    .build();
                            userAccordClassRepository.save(updateUserAccordClass);

                        } else { // DB에 삽입
                            UserAccordClassEntity userAccordClassEntity = UserAccordClassEntity.builder()
                                    .user(user)
                                    .accordClass(a)
                                    .build();
                            userAccordClassRepository.save(userAccordClassEntity);
                        }
                    }
                    result.put("isClicked", "true");
                }
            }
        }
        return result;
    }

    @Override
    public void registReview(String decodeId, Integer perfumeIdx, ReviewRegistDto reviewRegistDto) {
        Optional<UserEntity> user = userRepository.findByUserId(decodeId);
        PerfumeEntity perfume = perfumeRepository.findByIdx(perfumeIdx);
        String reviewImg = reviewRegistDto.getReviewImg();
        Integer totalScore = reviewRegistDto.getTotalScore();
        String content = reviewRegistDto.getContent();
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));

        if (user.isPresent()) {
            ReviewEntity reviewEntity = ReviewEntity.builder()
                    .user(user.get())
                    .perfume(perfume)
                    .reviewImg(reviewImg)
                    .totalScore(totalScore)
                    .longevity(reviewRegistDto.getLongevity())
                    .sillageScore(reviewRegistDto.getSillageScore())
                    .content(content)
                    .likeCount(0)
                    .time(now)
                    .updateTime(null)
                    .build();

            reviewRepository.save(reviewEntity);
        }
    }

    @Override
    public void updateReview(String decodeId, Integer perfumeIdx, Integer reviewIdx, ReviewRegistDto reviewRegistDto) {
        Optional<UserEntity> user = userRepository.findByUserId(decodeId);
        PerfumeEntity perfume = perfumeRepository.findByIdx(perfumeIdx);
        ReviewEntity originReview = reviewRepository.findByIdx(reviewIdx);
        String reviewImg = reviewRegistDto.getReviewImg();
        Integer totalScore = reviewRegistDto.getTotalScore();
        String content = reviewRegistDto.getContent();
        LocalDateTime registTime = LocalDateTime.from(originReview.getTime());
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        if (user.isPresent()) {
            ReviewEntity reviewEntity = ReviewEntity.builder()
                    .idx(originReview.getIdx())
                    .user(user.get())
                    .perfume(perfume)
                    .reviewImg(reviewImg)
                    .totalScore(totalScore)
                    .longevity(reviewRegistDto.getLongevity())
                    .sillageScore(reviewRegistDto.getSillageScore())
                    .content(content)
                    .likeCount(originReview.getLikeCount())
                    .time(registTime)
                    .updateTime(now)
                    .build();

            reviewRepository.save(reviewEntity);
        }

    }

}