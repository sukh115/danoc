package com.danoc.danoc.service;

import org.springframework.http.ResponseEntity;

import com.danoc.danoc.dto.request.auth.IdCheckRequestDto;
import com.danoc.danoc.dto.request.auth.SignInRequestDto;
import com.danoc.danoc.dto.request.auth.SignUpRequestDto;
import com.danoc.danoc.dto.request.auth.CheckCertificationRequestDto;
import com.danoc.danoc.dto.request.auth.EmailCertificationRequestDto;
import com.danoc.danoc.dto.response.auth.IdCheckResponseDto;
import com.danoc.danoc.dto.response.auth.SignInResponseDto;
import com.danoc.danoc.dto.response.auth.SignUpResponseDto;
import com.danoc.danoc.dto.response.auth.CheckCertificationResponseDto;
import com.danoc.danoc.dto.response.auth.EmailCertificationResponseDto;


public interface AuthService {

    ResponseEntity<? super IdCheckResponseDto> idCheck(IdCheckRequestDto dto);
    ResponseEntity<? super EmailCertificationResponseDto> emailCertification(EmailCertificationRequestDto dto);
    ResponseEntity<? super CheckCertificationResponseDto> checkCertification(CheckCertificationRequestDto dto);
    ResponseEntity<? super SignUpResponseDto> signUp (SignUpRequestDto dto);
    ResponseEntity<? super SignInResponseDto> signIn (SignInRequestDto dto);
}
