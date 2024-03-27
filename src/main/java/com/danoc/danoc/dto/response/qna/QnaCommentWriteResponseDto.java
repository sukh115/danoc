package com.danoc.danoc.dto.response.qna;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.danoc.danoc.common.ResponseCode;
import com.danoc.danoc.common.ResponseMessage;
import com.danoc.danoc.dto.ResponseDto;

import lombok.Getter;

@Getter
public class QnaCommentWriteResponseDto extends ResponseDto{
    
    private QnaCommentWriteResponseDto() {
        super();
    }

    public static ResponseEntity<QnaCommentWriteResponseDto> success() {
        QnaCommentWriteResponseDto responseBody = new QnaCommentWriteResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> userNotFound() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.USER_NOT_FOUND, ResponseMessage.USER_NOT_FOUND);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
    }
    public static ResponseEntity<ResponseDto> qnaNotFound() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.QNA_NOT_FOUND, ResponseMessage.QNA_NOT_FOUND);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
}
