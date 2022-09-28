package com.perfectrum.backend.domain.repository;

import com.perfectrum.backend.domain.entity.AccordClassEntity;
import com.perfectrum.backend.domain.entity.AccordEntity;
import com.perfectrum.backend.domain.entity.PerfumeEntity;
import com.perfectrum.backend.dto.perfume.PerfumeAccordsDto;
import com.perfectrum.backend.dto.survey.SurveyDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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





//    @Query(value = "select a.idx from AccordEntity as a " +
//                    "where a.accordClass = :accordClass")
//    List<Integer> findByAccordClass(Integer accordClass);

//    @Query(value = "select p from PerfumeEntity as p " +
//                    "where :accord in(select )")
//    PerfumeEntity findTop1ByGenderAndLongevityAndAccord(String gender,Integer longevity,String accord);


//    @Query(value = "select a.")
//    List<Integer> findByAccordIdx(Integer accordIdx);
//        @Query(value = " select p from PerfumeEntity p " +
//                " where p.gender = :gender " +
//                " and p.seasons like %:season% " +
//                " and p.longevity in :sList " +
//                "and p.idx in (select pa.perfume from PerfumeAccordsEntity pa where pa.accord in( select a.idx from AccordEntity a" +
//                "where a.idx = :accordClass))")
//        List<PerfumeEntity> findByGenderAndSeasonsAndSListLongevityAndAccordClass(@Param("gender")String gender, @Param("season")String season, @Param("longevity")List<Integer> sList,@Param("accordClass")Integer accordClass);

    @Query(value = " select * from perfumes " +
            " where gender = ':gender' " +
            " and seasons like concat('%',':season','%') " +
            " and longevity in (4,5) " +
            "and idx in (select perfume_idx from perfume_accords where accord_idx in( select idx from accords where accord_class = ':accordClass'" +
            "))",nativeQuery = true)
    List<PerfumeEntity> findByGenderAndSeasonsAndLongevityInSListAndAccordClass(@Param("gender")String gender, @Param("season")String season, @Param("Slist")List<Integer> longevity,@Param("accordClass")Integer accordClass);


    @Query(value = "select p from PerfumeEntity as p " +
            "where p.gender = :gender " +
            "and p.seasons like concat('%',:season,'%')" +
            "and p.longevity = :longevity")
    List<PerfumeEntity> findByGenderAndSeasonsAndLongevity(String gender,String season,Integer longevity,Integer accordClass);

        @Query(value = " select p from PerfumeEntity p " +
                " where p.gender = :gender " +
                " and p.seasons like %:season% " +
                " and p.longevity in :wList " +
                "and p.idx in (select pa.perfume from PerfumeAccordsEntity pa where pa.accord in( select a.idx from AccordEntity a " +
                "where a.idx = :accordClass))")
        List<PerfumeEntity> findByGenderAndSeasonsAndwListLongevityAndAccordClass(@Param("gender")String gender, @Param("season")String season, @Param("longevity")List<Integer> wList,@Param("accordClass")Integer accordClass);
@Query(value = " select p from PerfumeEntity p " +
        " where p.gender = ':gender' " +
        " and p.seasons like '%:season%' " +
        " and p.longevity in :Wlist " +
        "and p.idx in (select pa.perfume from perfume_accords pa where pa.accord in( select a.idx from accords_classes a" +
        "where a.accord = :accordClass))",nativeQuery = true)
List<PerfumeEntity> findByGenderAndSeasonsAndLongevityInWListAndAccordClass(@Param("gender")String gender, @Param("season")String season, @Param("Wlist")List<Integer> longevity,@Param("accordClass")Integer accordClass);

}
