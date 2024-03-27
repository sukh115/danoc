package com.danoc.danoc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.danoc.danoc.entity.BoardEntity;
import com.danoc.danoc.repository.resultSet.BoardReadResultSet;

public interface BoardRepository  extends JpaRepository<BoardEntity, Long> {
    
    @Query(
        value=
        "SELECT " +
        "B.board_id AS boardId, " +
        "B.title AS title, " +
        "B.ctnt AS ctnt, " +
        "B.cate AS cate, " +
        "B.date AS date, " +
        "B.user_id AS userId, " +
        "U.name AS name " +
        "FROM board AS B " +
        "INNER JOIN user AS U " +
        "ON B.user_id = U.user_id " +
        "WHERE board_id = ?1 " +
        "ORDER BY date DESC",
        nativeQuery = true
    )
    BoardReadResultSet boardRead(Long boardId);

    BoardEntity findByBoardId(Long boardId);

    List<BoardEntity> findAll();

    Page<BoardEntity> findAll(Pageable pageable);

    Page<BoardEntity> findAllByCate(Long cate, Pageable pageable);

    Optional<BoardEntity> findById(Long boardId);
}
