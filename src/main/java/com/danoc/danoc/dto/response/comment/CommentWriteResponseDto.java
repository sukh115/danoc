package com.danoc.danoc.dto.response.comment;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.danoc.danoc.common.ResponseCode;
import com.danoc.danoc.common.ResponseMessage;
import com.danoc.danoc.dto.ResponseDto;

public class CommentWriteResponseDto extends ResponseDto {
    
    private CommentWriteResponseDto() {
        super();
    }
    public static ResponseEntity<CommentWriteResponseDto> success () {
        CommentWriteResponseDto responseBody = new CommentWriteResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
    public static ResponseEntity<ResponseDto> userNotFound() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.USER_NOT_FOUND, ResponseMessage.USER_NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
    }
    public static ResponseEntity<ResponseDto> boardNotFound() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_FOUND, ResponseMessage.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
    }
}
