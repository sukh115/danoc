package com.danoc.danoc.controller;



import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.danoc.danoc.dto.request.board.BoardDeleteRequestDto;
import com.danoc.danoc.dto.request.board.BoardEditRequestDto;
import com.danoc.danoc.dto.request.board.BoardListRequestDto;
import com.danoc.danoc.dto.request.board.BoardWriteRequestDto;

import com.danoc.danoc.dto.response.board.BoardListResponseDto;
import com.danoc.danoc.dto.response.board.BoardReadResponseDto;
import com.danoc.danoc.dto.response.board.BoardWriteResponseDto;
import com.danoc.danoc.dto.response.board.BoardDeleteResponseDto;
import com.danoc.danoc.dto.response.board.BoardEditResponseDto;

import com.danoc.danoc.filter.JwtAuthenticationFilter;
import com.danoc.danoc.provider.JwtProvider;
import com.danoc.danoc.service.BoardService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final JwtProvider jwtProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @GetMapping("/list")
    public ResponseEntity<? super BoardListResponseDto> boardList (
        @Valid BoardListRequestDto requestDto,
        @RequestParam(defaultValue = "0") int page, 
        @RequestParam(defaultValue = "10") int pageSize 
    ) {
        ResponseEntity<? super BoardListResponseDto> response = boardService.boardList(requestDto, page, pageSize);
        return response;
    }

    @PostMapping("/write")
    public ResponseEntity<? super BoardWriteResponseDto> boardWrite (
        @RequestBody @Valid BoardWriteRequestDto requestDto, HttpServletRequest request
    ) {
        // 토큰 추출
        String token = jwtAuthenticationFilter.parseBearerToken(request);

        // 토큰에서 userId를 추출
        Long userId = jwtProvider.extractUserId(token);
        String username = jwtProvider.extractUsername(token);

        log.debug("현재 인증된 사용자의 userId: {}", userId);
        log.debug("현재 인증된 사용자의 username: {}", username);

        // boardService.boardWrite() 메서드에 토큰을 전달하여 호출
        ResponseEntity<? super BoardWriteResponseDto> response = boardService.boardWrite(requestDto, userId);
        return response;

    }
    
    @PostMapping("/delete")
    public ResponseEntity<? super BoardDeleteResponseDto> boardDelete (
        @RequestBody @Valid BoardDeleteRequestDto requestDto
    ) {
        ResponseEntity<? super BoardDeleteResponseDto> response = boardService.boardDelete(requestDto);
        return response;
    }
    
    @PostMapping("/edit")
    public ResponseEntity<? super BoardEditResponseDto> boardEdit (
        @RequestBody @Valid BoardEditRequestDto requestDto
    ) {
        ResponseEntity<? super BoardEditResponseDto> response = boardService.boardEdit(requestDto);
        return response;
    }

    @GetMapping("/read/{boardId}")
    public ResponseEntity<? super BoardReadResponseDto> boardRead (
         @PathVariable Long boardId
    ) {
        ResponseEntity<? super BoardReadResponseDto> response = boardService.boardRead(boardId);
        return response;
    }
    
}
