package com.danoc.danoc.service;

import java.util.List;
import org.springframework.http.ResponseEntity;

import com.danoc.danoc.dto.request.qna.QnaDeleteRequestDto;
import com.danoc.danoc.dto.request.qna.QnaEditRequestDto;
import com.danoc.danoc.dto.request.qna.QnaWriteRequestDto;
import com.danoc.danoc.dto.response.qna.QnaDeleteResponseDto;
import com.danoc.danoc.dto.response.qna.QnaEditResponseDto;
import com.danoc.danoc.dto.response.qna.QnaWriteResponseDto;
import com.danoc.danoc.entity.QnaEntity;

public interface QnaService {

    ResponseEntity<? super QnaWriteResponseDto> qnaWrite(QnaWriteRequestDto dto, Long userId);
    ResponseEntity<? super QnaDeleteResponseDto> qnaDelete(QnaDeleteRequestDto dto);
    ResponseEntity<? super QnaEditResponseDto> qnaEdit(QnaEditRequestDto dto);
    List<QnaEntity> qnaList();
    
}
