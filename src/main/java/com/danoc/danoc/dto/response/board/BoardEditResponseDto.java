package com.danoc.danoc.dto.response.board;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.danoc.danoc.common.ResponseCode;
import com.danoc.danoc.common.ResponseMessage;
import com.danoc.danoc.dto.ResponseDto;

public class BoardEditResponseDto extends ResponseDto{
        private BoardEditResponseDto () {
        super();
    }

    public static ResponseEntity<BoardEditResponseDto> success () {
        BoardEditResponseDto responseBody = new BoardEditResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
    public static ResponseEntity<ResponseDto> editFail() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.EDIT_FAIL, ResponseMessage.EDIT_FAIL);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
    }
}
