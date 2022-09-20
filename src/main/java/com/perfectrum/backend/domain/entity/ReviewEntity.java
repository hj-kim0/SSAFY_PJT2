package com.perfectrum.backend.domain.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@Table(name = "reviews")
public class ReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    @ManyToOne
    @JoinColumn(name = "user_idx")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "perfume_idx")
    private PerfumeEntity perfume;

    @Column(name="review_img")
    private String reviewImg;
    @Column(name="total_score")
    private Integer totalScore;
    private Integer longevity;
    @Column(name="sillage_score")
    private Integer sillageScore;
    private String content;
    @Column(name="like_count")
    private Integer likeCount;
    private LocalDateTime time;
    @Column(name="update_time")
    private LocalDateTime updateTime;
}
