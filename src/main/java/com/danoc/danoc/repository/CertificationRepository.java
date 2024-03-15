package com.danoc.danoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.danoc.danoc.entity.CertificationEntity;

import jakarta.transaction.Transactional;


@Repository
public interface CertificationRepository extends JpaRepository<CertificationEntity, String> {
    
    CertificationEntity findByUsername(String username);

    @Transactional
    void deleteByUsername(String username);
}
