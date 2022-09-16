package com.perfectrum.backend.domain.repository;

import com.perfectrum.backend.domain.entity.PerfumeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PerfumeRepository extends JpaRepository<PerfumeEntity, Integer> {

    List<PerfumeEntity> findTop6ByGenderAndSeasonsContainsOrderByItemRatingDesc(String gender, String season);
    List<PerfumeEntity> findTop6ByOrderByItemRatingDesc();
    List<PerfumeEntity> findTop6ByTimezoneAndSeasonsContainsOrderByItemRatingDesc(String timeZone, String season);

    List<PerfumeEntity> findTop20ByTimezoneAndSeasonsContainsOrderByItemRatingDesc(String timeZone, String season);
}
