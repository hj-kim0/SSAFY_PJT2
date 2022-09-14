package com.perfectrum.backend.domain.repository;

import com.perfectrum.backend.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {
}
