package com.danoc.danoc.service;

import org.springframework.http.ResponseEntity;

import com.danoc.danoc.dto.request.qna.QnaWriteRequestDto;
import com.danoc.danoc.dto.response.qna.QnaWriteResponseDto;

public interface QnaService {

    ResponseEntity<? super QnaWriteResponseDto> qnaWrite(QnaWriteRequestDto dto, Long userId);
    
}
