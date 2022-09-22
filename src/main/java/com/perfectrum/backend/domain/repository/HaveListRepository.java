package com.perfectrum.backend.domain.repository;

import com.perfectrum.backend.domain.entity.HaveListEntity;
import com.perfectrum.backend.domain.entity.UserEntity;
import com.perfectrum.backend.domain.entity.WishListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HaveListRepository extends JpaRepository<HaveListEntity,Integer> {


    List<HaveListEntity> findByUser(UserEntity userEntity);

    Optional<HaveListEntity> findByUserAndIdx(Optional<UserEntity> userEntityOptional, Integer idx);
}
