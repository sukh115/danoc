package com.danoc.danoc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.danoc.danoc.entity.CommentEntity;
import com.danoc.danoc.repository.resultSet.CommentListResultSet;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    
    @Query(
        value = 
        "SELECT " +
        "U.name AS name, " +
        "C.date AS date, " +
        "C.ctnt AS ctnt " +
        "FROM comment AS C " +
        "INNER JOIN user AS U " +
        "ON C.user_id= U.user_id " +
        "WHERE C.qa_id = ?1 " +
        "ORDER BY date DESC",
        nativeQuery = true

    )
    List<CommentListResultSet> commentList(Long qaId);
}

