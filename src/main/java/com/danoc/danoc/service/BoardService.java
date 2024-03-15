package com.danoc.danoc.service;

import org.springframework.http.ResponseEntity;

import com.danoc.danoc.dto.request.board.BoardListRequestDto;
import com.danoc.danoc.dto.request.board.BoardWriteRequestDto;
import com.danoc.danoc.dto.request.board.BoardDeleteRequestDto;
import com.danoc.danoc.dto.request.board.BoardEditRequestDto;
import com.danoc.danoc.dto.response.board.BoardListResponseDto;
import com.danoc.danoc.dto.response.board.BoardReadResponseDto;
import com.danoc.danoc.dto.response.board.BoardWriteResponseDto;
import com.danoc.danoc.dto.response.board.BoardDeleteResponseDto;
import com.danoc.danoc.dto.response.board.BoardEditResponseDto;

public interface BoardService {

    ResponseEntity<? super BoardListResponseDto> boardList(BoardListRequestDto dto, int page, int size);
    ResponseEntity<? super BoardWriteResponseDto> boardWrite(BoardWriteRequestDto dto, Long userId);
    ResponseEntity<? super BoardDeleteResponseDto> boardDelete(BoardDeleteRequestDto dto);
    ResponseEntity<? super BoardEditResponseDto> boardEdit(BoardEditRequestDto dto);
    ResponseEntity<? super BoardReadResponseDto> boardRead(Long boardId);
} 