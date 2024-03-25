package com.danoc.danoc.dto.response.qna;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.danoc.danoc.common.ResponseCode;
import com.danoc.danoc.common.ResponseMessage;
import com.danoc.danoc.dto.ResponseDto;

import lombok.Getter;

@Getter
public class QnaWriteResponseDto extends ResponseDto{
    
    private QnaWriteResponseDto () {
        super();
    }

    public static ResponseEntity<QnaWriteResponseDto> success () {
        QnaWriteResponseDto responseBody = new QnaWriteResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> userNotFound() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.USER_NOT_FOUND, ResponseMessage.USER_NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> userDetailsNotFound() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.USER_NOT_FOUND, ResponseMessage.USER_NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
    }
}
