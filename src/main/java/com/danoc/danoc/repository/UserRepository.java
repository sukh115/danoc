package com.danoc.danoc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.danoc.danoc.entity.UserEntity;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByUsername(String username);
    boolean existsByUserId(Long userId);
    UserEntity findByUsername(String username);

    Optional<UserEntity> findByUserId(Long userId);
    
}

