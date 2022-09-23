package com.perfectrum.backend.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@Table(name = "user_accord_class")
public class UserAccordClassEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    @ManyToOne
    @JoinColumn(name = "user_idx")
    UserEntity user;

    @ManyToOne
    @JoinColumn(name = "accord_class_idx")
    AccordClassEntity accordClass;

    @Column(name = "accord_class_count")
    Integer accordClassCount;
}
