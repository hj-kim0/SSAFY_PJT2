package com.perfectrum.backend.domain.repository;

import com.perfectrum.backend.domain.entity.AccordClassEntity;
import com.perfectrum.backend.domain.entity.AccordEntity;
import com.perfectrum.backend.domain.entity.PerfumeEntity;
import com.perfectrum.backend.dto.perfume.PerfumeAccordsDto;
import com.perfectrum.backend.dto.survey.SurveyDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PerfumeRepository extends JpaRepository<PerfumeEntity, Integer> {

    List<PerfumeEntity> findTop6ByGenderAndSeasonsContainsOrderByItemRatingDesc(String gender, String season);
    List<PerfumeEntity> findTop6ByOrderByItemRatingDesc();
    List<PerfumeEntity> findTop6ByTimezoneAndSeasonsContainsOrderByItemRatingDesc(String timeZone, String season);

    List<PerfumeEntity> findTop20ByTimezoneAndSeasonsContainsOrderByItemRatingDesc(String timeZone, String season);

    PerfumeEntity findTop1BySeasonsContainsAndGenderAndLongevityAndTimezone(String like_seasons,String like_gender,Integer like_longevity,String like_timezone);

    List<PerfumeEntity> findAllByGenderAndLongevity(String gender,Integer longevity);

    @Query(value =
            "SELECT p FROM PerfumeEntity AS p WHERE p.gender = :gender AND p.seasons like %:season% and " +
            "p.idx in (SELECT pa.perfume FROM PerfumeAccordsEntity AS pa " +
            "WHERE pa.accord IN (SELECT a.idx from AccordEntity AS a WHERE a.accordClass = :accordClassEntity)) " +
            "ORDER BY p.itemRating DESC")
    List<PerfumeEntity> findBest6Perfumes(String gender, String season, AccordClassEntity accordClassEntity, Pageable top6);

    PerfumeEntity findByIdx(Integer idx);

    @Query(value = " select a from AccordEntity as a where a.idx in " +
                    "(select p.accord from PerfumeAccordsEntity as p where p.perfume = :perfume)")
    List<AccordEntity> findByPerfume(PerfumeEntity perfume);


    @Query(value = "select p from PerfumeEntity as p " +
                    "where p.gender = :gender " +
                    "and p.seasons like concat('%',:season,'%')" +
                    "and p.longevity = :longevity")
    List<PerfumeEntity> findByGenderAndSeasonsAndLongevity(String gender,String season,Integer longevity);


}
