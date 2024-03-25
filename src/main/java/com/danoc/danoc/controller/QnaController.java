package com.danoc.danoc.controller;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.danoc.danoc.dto.request.qna.QnaDeleteRequestDto;
import com.danoc.danoc.dto.request.qna.QnaWriteRequestDto;
import com.danoc.danoc.dto.response.qna.QnaDeleteResponseDto;
import com.danoc.danoc.dto.response.qna.QnaWriteResponseDto;
import com.danoc.danoc.filter.JwtAuthenticationFilter;
import com.danoc.danoc.provider.JwtProvider;
import com.danoc.danoc.service.QnaService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Slf4j
@Controller
@RequestMapping("/api/qna")
@RequiredArgsConstructor
public class QnaController {
    
    private final QnaService qnaService;
    private final JwtProvider jwtProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @PostMapping("/write")
    public ResponseEntity<? super QnaWriteResponseDto> qnaWrite(
        @RequestBody @Valid QnaWriteRequestDto requestDto, HttpServletRequest request) {
        
            String token = jwtAuthenticationFilter.parseBearerToken(request);

            Long userId = jwtProvider.extractUserId(token);
            String username = jwtProvider.extractUsername(token);

            log.debug("현재 인증된 사용자의 userId: {}", userId);
            log.debug("현재 인증된 사용자의 username: {}", username);

        ResponseEntity<? super QnaWriteResponseDto> reponse = qnaService.qnaWrite(requestDto, userId);
        
        return reponse;
    }

    @PostMapping("/delete")
    public ResponseEntity<? super QnaDeleteResponseDto> qnaDelete(
        @RequestBody @Valid QnaDeleteRequestDto requestDto
    ) {
        ResponseEntity<? super QnaDeleteResponseDto> response = qnaService.qnaDelete(requestDto);
        return response;
    }
    
    
}
