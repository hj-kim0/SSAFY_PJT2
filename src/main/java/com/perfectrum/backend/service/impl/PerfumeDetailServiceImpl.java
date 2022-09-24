package com.perfectrum.backend.service.impl;

import com.perfectrum.backend.dto.review.ReviewListDto;
import com.perfectrum.backend.dto.review.ReviewViewDto;
import com.perfectrum.backend.domain.entity.*;
import com.perfectrum.backend.domain.repository.*;
import com.perfectrum.backend.service.PerfumeDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
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

    @Autowired
    PerfumeDetailServiceImpl(UserRepository userRepository, PerfumeRepository perfumeRepository,
                             AccordClassRepository accordClassRepository, ReviewRepository reviewRepository,
                             UserDetailLogRepository userDetailLogRepository,
                             HaveListRepository haveListRepository, WishListRepository wishListRepository) {
        this.userRepository = userRepository;
        this.perfumeRepository = perfumeRepository;
        this.accordClassRepository = accordClassRepository;
        this.reviewRepository = reviewRepository;
        this.userDetailLogRepository = userDetailLogRepository;
        this.haveListRepository = haveListRepository;
        this.wishListRepository = wishListRepository;
    }

    @Override
    public Map<String, Object> getPerfumeDetail(String decodeId, Integer perfumeIdx, ReviewListDto reviewListDto) {
        System.out.println("Service 진입");
        Map<String, Object> data = new HashMap<>();
        List<ReviewViewDto> reviewList = new ArrayList<>();
        String type = reviewListDto.getType();
        Integer lastIdx = reviewListDto.getLastIdx();
        Integer pageSize = reviewListDto.getPageSize();

        Pageable pageable = Pageable.ofSize(pageSize);

        PerfumeEntity perfume = perfumeRepository.findByIdx(perfumeIdx);
        data.put("perfume", perfume);
        Slice<ReviewEntity> reviews = reviewRepository.findByPerfume(perfume);
        System.out.println("일단 리뷰 다 가져옴");
        if (!reviews.isEmpty()) {
            if (lastIdx == null) {
                // 현재까지 화면에 표시된 리뷰중 마지막 리뷰의 idx를 가져와서
                // 그 다음 리뷰를 추가로 가져오는데 처음 화면을 표시할 경우 idx를
                // 알 수 없으므로 현재 등록된 리뷰 중 가장 큰 idx값을 기본으로 설정함
                System.out.println("lastIdx 대입 전");
                lastIdx = reviewRepository.findTop1ByPerfumeOrderByIdxDesc(perfume).getIdx() + 1;
                System.out.println("lastIdx 대입 후"+lastIdx);
            }

            if (type.equals("평점순")) {
                System.out.println("평점 순이라면?");
                reviews = reviewRepository.findByPerfumeOrderByIdxDescLikeCountDesc(perfume, lastIdx, pageable);
            } else {
                System.out.println("평점순이 아니라면");
                reviews = reviewRepository.findByPerfumeOrderByIdxDesc(perfume, lastIdx, pageable);
                System.out.println("리뷰 다 가져옴");
            }
        }
        System.out.println("어디냐 대체");
        boolean hasNext = reviews.hasNext();
        data.put("hasNext", hasNext);

        for(ReviewEntity re : reviews){
            ReviewViewDto reviewViewDto = ReviewViewDto.builder()
                    .idx(re.getIdx())
                    .userNickname(null)
                    .userProfileimg(null)
                    .perfumeName(re.getPerfume().getPerfumeName())
                    .reviewImg(re.getReviewImg())
                    .totalScore(re.getTotalScore())
                    .longevity(re.getLongevity())
                    .sillageScore(re.getSillageScore())
                    .content(re.getContent())
                    .time(re.getTime())
                    .updateTime(re.getUpdateTime())
                    .build();
            reviewList.add(reviewViewDto);
        }
        data.put("reviewList",reviewList);
        System.out.println("리뷰 갯수");
        return data;
    }


    @Override
    public void addWishList(String decodeId, Integer perfumeIdx) {
        Optional<UserEntity> user = userRepository.findByUserId(decodeId);
        boolean isWish = false;
        if (user.isPresent()) {
            Long cnt = wishListRepository.countByuserIdx(user.get().getIdx());
            if (cnt == 0) {
                isWish = true;
            }
            PerfumeEntity perfume = perfumeRepository.findByIdx(perfumeIdx);
            WishListEntity wish = WishListEntity.builder()
                    .user(user.get())
                    .perfume(perfume)
                    .isDelete(isWish)
                    .build();

            wishListRepository.save(wish);
        }
    }

    @Override
    public void addHaveList(String decodeId, Integer perfumeIdx) {
        Optional<UserEntity> user = userRepository.findByUserId(decodeId);
        boolean isHave = false;
        if (user.isPresent()) {
            Long cnt = haveListRepository.countByuserIdx(user.get().getIdx());
            if (cnt == 0) {
                isHave = true;
            }
            PerfumeEntity perfume = perfumeRepository.findByIdx(perfumeIdx);
            HaveListEntity have = HaveListEntity.builder()
                    .user(user.get())
                    .perfume(perfume)
                    .isDelete(isHave)
                    .build();

            haveListRepository.save(have);
        }
    }

    @Override
    public void registReview(String decodeId, Integer perfumeIdx, ReviewViewDto reviewDto) {
        Optional<UserEntity> user = userRepository.findByUserId(decodeId);
        PerfumeEntity perfume = perfumeRepository.findByIdx(perfumeIdx);
        String reviewImg = reviewDto.getReviewImg();
        Integer totalScore = reviewDto.getTotalScore();
        String content = reviewDto.getContent();
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));

        if (user.isPresent()) {
            ReviewEntity reviewEntity = ReviewEntity.builder()
                    .user(user.get())
                    .perfume(perfume)
                    .reviewImg(reviewImg)
                    .totalScore(totalScore)
                    .longevity(perfume.getLongevity())
                    .sillageScore(perfume.getSillage())
                    .content(content)
                    .time(now)
                    .updateTime(null)
                    .build();

            reviewRepository.save(reviewEntity);
        }
    }

    @Override
    public void updateReview(String decodeId, Integer perfumeIdx, Integer reviewIdx, ReviewViewDto reviewDto) {
        Optional<UserEntity> user = userRepository.findByUserId(decodeId);
        PerfumeEntity perfume = perfumeRepository.findByIdx(perfumeIdx);
        String reviewImg = reviewDto.getReviewImg();
        Integer totalScore = reviewDto.getTotalScore();
        String content = reviewDto.getContent();
        LocalDateTime registTime = LocalDateTime.from(reviewDto.getTime());
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        if (user.isPresent()) {
            ReviewEntity reviewEntity = ReviewEntity.builder()
                    .user(user.get())
                    .perfume(perfume)
                    .reviewImg(reviewImg)
                    .totalScore(totalScore)
                    .longevity(perfume.getLongevity())
                    .sillageScore(perfume.getSillage())
                    .content(content)
                    .time(registTime)
                    .updateTime(now)
                    .build();

            reviewRepository.save(reviewEntity);
        }
    }

}
