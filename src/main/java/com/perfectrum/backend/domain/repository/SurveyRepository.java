package com.perfectrum.backend.domain.repository;

import com.perfectrum.backend.domain.entity.PerfumeEntity;
import com.perfectrum.backend.domain.entity.SurveyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

public interface SurveyRepository extends JpaRepository<PerfumeEntity, String> {
    //    PerfumeEntity findTopBylikeSeasonsContainsAndlikeGenderAndlikeLongevityAndlikeTimezoneAndlikeAccordClass(String like_seasons,String like_gender,Integer like_longevity,String like_timezone,Integer like_accord_class);
//    PerfumeEntity findTopBylikeSeasonsContainsAndlikeGenderAndlikeLongevityAndlikeTimezone(String like_seasons,String like_gender,Integer like_longevity,String like_timezone);
    PerfumeEntity findTopBySeasonsContainsAndGenderAndLongevityAndTimezone(String like_seasons,String like_gender,Integer like_longevity,String like_timezone);
    PerfumeEntity findTop1ByGender(String like_gender);

//    @Transactional
//    @Override
//    <S extends PerfumeEntity> S save(S entity) {
//        if (entityInformation.isNew(entity)) {
//            em.persist(entity);
//            return entity;
//        } else {
//            return em.merge(entity);
//        }
//    }

    void save(SurveyEntity surveyEntity);
}