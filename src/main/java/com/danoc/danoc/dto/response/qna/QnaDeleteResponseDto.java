package com.danoc.danoc.dto.response.qna;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.danoc.danoc.common.ResponseCode;
import com.danoc.danoc.common.ResponseMessage;
import com.danoc.danoc.dto.ResponseDto;

import lombok.Getter;

@Getter
public class QnaDeleteResponseDto extends ResponseDto{
    private QnaDeleteResponseDto () {
        super();
    }
    public static ResponseEntity<QnaDeleteResponseDto> success () {
        QnaDeleteResponseDto responseBody = new QnaDeleteResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> deleteFail () {
        ResponseDto responseBody = new ResponseDto(ResponseCode.DELETE_FAIL, ResponseMessage.DELETE_FAIL);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
    }
    
}
