package com.danoc.danoc.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.danoc.danoc.dto.request.qna.QnaCommentWriteRequestDto;
import com.danoc.danoc.dto.request.qna.QnaDeleteRequestDto;
import com.danoc.danoc.dto.request.qna.QnaEditRequestDto;
import com.danoc.danoc.dto.request.qna.QnaWriteRequestDto;
import com.danoc.danoc.dto.response.comment.CommentListResponseDto;
import com.danoc.danoc.dto.response.qna.QnaCommentWriteResponseDto;
import com.danoc.danoc.dto.response.qna.QnaDeleteResponseDto;
import com.danoc.danoc.dto.response.qna.QnaEditResponseDto;
import com.danoc.danoc.dto.response.qna.QnaReadResponseDto;
import com.danoc.danoc.dto.response.qna.QnaWriteResponseDto;
import com.danoc.danoc.filter.JwtAuthenticationFilter;
import com.danoc.danoc.provider.JwtProvider;
import com.danoc.danoc.repository.resultSet.QnaListResultSet;
import com.danoc.danoc.service.QnaService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;





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

    @PostMapping("/edit")
    public ResponseEntity<? super QnaEditResponseDto> qnaEdit(
        @RequestBody @Valid QnaEditRequestDto requestDto
    ) {
        ResponseEntity<?super QnaEditResponseDto> response = qnaService.qnaEdit(requestDto);
        return response;
    }
    
    @GetMapping("/list")
    @ResponseBody
    public List<QnaListResultSet> qnaList() {
        return qnaService.qnaList();
    }
    
    @GetMapping("/{qaId}")
    public ResponseEntity<? super QnaReadResponseDto> qnaRead (
        @PathVariable("qaId") Long qaId
    ) {
        ResponseEntity<? super QnaReadResponseDto> response = qnaService.qnaRead(qaId);
        return response;
    }

    @PostMapping("/{qaId}/comment")
    public ResponseEntity<? super QnaCommentWriteResponseDto> commentWrite(
        @RequestBody @Valid QnaCommentWriteRequestDto requestDto,
        @PathVariable("qaId") Long qaId,
        @AuthenticationPrincipal Long userId
    ) {
        ResponseEntity<? super QnaCommentWriteResponseDto> response = qnaService.commentWrite(requestDto, userId, qaId);
        return response;
    }
    
    @GetMapping("/{qaId}/comment-list")
    public ResponseEntity<? super CommentListResponseDto> commentList(
        @PathVariable("qaId") Long qaId
    ) {
        ResponseEntity<? super CommentListResponseDto> response = qnaService.commentList(qaId);
        return response;
    }
    
    
    
    
    
    
}
