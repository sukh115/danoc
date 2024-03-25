package com.danoc.danoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.danoc.danoc.entity.QnaEntity;



public interface QnaRepository extends JpaRepository<QnaEntity, Long>{
    
    QnaEntity findByQaId(Long qaId);
}
