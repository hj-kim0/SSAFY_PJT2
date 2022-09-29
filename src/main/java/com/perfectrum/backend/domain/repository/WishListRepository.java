package com.perfectrum.backend.domain.repository;

import com.perfectrum.backend.domain.entity.PerfumeEntity;
import com.perfectrum.backend.domain.entity.UserEntity;
import com.perfectrum.backend.domain.entity.WishListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface WishListRepository extends JpaRepository<WishListEntity,String> {
    List<WishListEntity> findByUser(UserEntity userEntity);
    WishListEntity findByIdx(Integer idx);
    Optional<WishListEntity> findByUserAndIdx(Optional<UserEntity> userEntityOptional, Integer idx);
    Long countByPerfumeIdx(Integer perfumeIdx);
    Optional<WishListEntity> findByUserAndPerfumeAndIsDelete(UserEntity user, PerfumeEntity perfume, boolean b);
    Optional<WishListEntity> findByUserAndPerfume(UserEntity user, PerfumeEntity perfume);
    List<WishListEntity> findByUserAndIsDelete(UserEntity user, boolean b);
}
