package com.danoc.danoc.dto.response.board;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.danoc.danoc.common.ResponseCode;
import com.danoc.danoc.common.ResponseMessage;
import com.danoc.danoc.dto.ResponseDto;

import lombok.Getter;

@Getter
public class BoardWriteResponseDto extends ResponseDto {
    
    private BoardWriteResponseDto () {
        super();
    }

    public static ResponseEntity<BoardWriteResponseDto> success () {
        BoardWriteResponseDto responseBody = new BoardWriteResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
    public static ResponseEntity<ResponseDto> userNotFound() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.USER_NOT_FOUND, ResponseMessage.USER_NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
    }

    
}
