package com.danoc.danoc.dto.response.board;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.danoc.danoc.common.ResponseCode;
import com.danoc.danoc.common.ResponseMessage;
import com.danoc.danoc.dto.ResponseDto;

import lombok.Getter;

@Getter
public class BoardListResponseDto extends ResponseDto {
    
    private BoardListResponseDto () {
        super();
    }

    public static ResponseEntity<BoardListResponseDto> success () {
        BoardListResponseDto responseBody = new BoardListResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
    public static ResponseEntity<ResponseDto> notFound () {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_FOUND, ResponseMessage.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
    }
}
