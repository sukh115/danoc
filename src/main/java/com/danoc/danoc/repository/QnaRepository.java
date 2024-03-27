package com.danoc.danoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

import java.sql.Timestamp;

import com.danoc.danoc.entity.QnaEntity;
import com.danoc.danoc.repository.resultSet.QnaListResultSet;
import com.danoc.danoc.repository.resultSet.QnaReadResultSet;



public interface QnaRepository extends JpaRepository<QnaEntity, Long>{


    @Query(
        value = 
        "SELECT " +
        "Q.qa_Id AS qaId, " +
        "Q.title AS title, " +
        "Q.ctnt AS ctnt, " +
        "Q.date AS date, " +
        "Q.user_id AS userId, " +
        "U.name AS name " +
        "FROM qna AS Q " +
        "INNER JOIN user AS U " +
        "ON Q.user_id = U.user_id " +
        "WHERE qa_id = ?1 ",
        nativeQuery = true
    )
    QnaReadResultSet qnaRead(Long qaId);

    
    QnaEntity findByQaId(Long qaId);

    @Query(
        value = 
        "SELECT " +
        "Q.qa_Id AS qaId, " +
        "Q.title AS title, " +
        "Q.date AS date, " +
        "Q.user_id AS userId, " +
        "U.name AS name " +
        "FROM qna AS Q " +
        "INNER JOIN user AS U " +
        "ON Q.user_id = U.user_id ",
        nativeQuery = true
    )
    List<QnaListResultSet> qnaList();

    boolean existsByQaId(Long qaId);

    Optional<QnaEntity> findById(Long qaId);
}
