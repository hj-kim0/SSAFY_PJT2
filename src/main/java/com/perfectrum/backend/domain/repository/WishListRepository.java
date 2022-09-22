package com.perfectrum.backend.domain.repository;

import com.perfectrum.backend.domain.entity.UserEntity;
import com.perfectrum.backend.domain.entity.WishListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface WishListRepository extends JpaRepository<WishListEntity,Integer> {
    List<WishListEntity> findByUser(UserEntity userEntity);

    @Transactional
    void deleteByUserAndIdx(UserEntity userEntity, Integer idx);

    WishListEntity findByIdx(Integer idx);

    Optional<WishListEntity> findByUserAndIdx(Optional<UserEntity> userEntityOptional, Integer idx);
}
