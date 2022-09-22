package com.perfectrum.backend.domain.repository;

import com.perfectrum.backend.domain.entity.HaveListEntity;
import com.perfectrum.backend.domain.entity.WishListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HaveListRepository extends JpaRepository<HaveListEntity,Integer> {


}
