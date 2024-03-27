package com.danoc.danoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

import java.sql.Timestamp;

import com.danoc.danoc.entity.QnaEntity;



public interface QnaRepository extends JpaRepository<QnaEntity, Long>{
    
    QnaEntity findByQaId(Long qaId);

    List<QnaEntity> findByQaIdAndUserIdAndTitleAndDate(Long QaId,Long userId, String title, Timestamp date);

    Optional<QnaEntity> findById(Long qaId);
}
