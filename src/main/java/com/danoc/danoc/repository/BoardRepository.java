package com.danoc.danoc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.danoc.danoc.entity.BoardEntity;

public interface BoardRepository  extends JpaRepository<BoardEntity, Long> {
    

    List<BoardEntity> findAll();

    Page<BoardEntity> findAll(Pageable pageable);

    Page<BoardEntity> findAllByCate(Long cate, Pageable pageable);

    Optional<BoardEntity> findById(Long boardId);
}
