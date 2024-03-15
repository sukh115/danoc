package com.danoc.danoc.dto.response.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.danoc.danoc.common.ResponseCode;
import com.danoc.danoc.common.ResponseMessage;
import com.danoc.danoc.dto.ResponseDto;

import lombok.Getter;

@Getter
public class SignInResponseDto extends ResponseDto {
    
    private String token;
    private int expirationTime;

    private SignInResponseDto (String token) {
        super();
        this.token = token;
        this.expirationTime = 7200;

    }

    public static ResponseEntity<SignInResponseDto> success (String token) {
        SignInResponseDto responseBody = new SignInResponseDto(token);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> signInFail () {
        ResponseDto responseBody = new ResponseDto(ResponseCode.SIGN_IN_FAIL, ResponseMessage.SIGN_IN_FAIL);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
    }
}
