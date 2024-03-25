package com.danoc.danoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import com.danoc.danoc.entity.QnaEntity;



public interface QnaRepository extends JpaRepository<QnaEntity, Long>{
    
    QnaEntity findByQaId(Long qaId);

    Optional<QnaEntity> findById(Long qaId);
}
