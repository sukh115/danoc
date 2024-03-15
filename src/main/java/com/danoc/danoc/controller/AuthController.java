package com.danoc.danoc.controller;

import org.springframework.web.bind.annotation.RestController;

import com.danoc.danoc.dto.request.auth.CheckCertificationRequestDto;
import com.danoc.danoc.dto.request.auth.EmailCertificationRequestDto;
import com.danoc.danoc.dto.request.auth.IdCheckRequestDto;
import com.danoc.danoc.dto.request.auth.SignInRequestDto;
import com.danoc.danoc.dto.request.auth.SignUpRequestDto;
import com.danoc.danoc.dto.response.auth.CheckCertificationResponseDto;
import com.danoc.danoc.dto.response.auth.EmailCertificationResponseDto;
import com.danoc.danoc.dto.response.auth.IdCheckResponseDto;
import com.danoc.danoc.dto.response.auth.SignInResponseDto;
import com.danoc.danoc.dto.response.auth.SignUpResponseDto;
import com.danoc.danoc.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/id-check")
    public ResponseEntity<? super IdCheckResponseDto> idCheck (
        @RequestBody @Valid IdCheckRequestDto requestBody
    ) {
        ResponseEntity<? super IdCheckResponseDto> response = authService.idCheck(requestBody);
        return response;
    }

    @PostMapping("/email-certification")
    public ResponseEntity<? super EmailCertificationResponseDto> emailCertification (
        @RequestBody @Valid EmailCertificationRequestDto requestBody
    ) {
        ResponseEntity<? super EmailCertificationResponseDto> response = authService.emailCertification(requestBody);
        return response;
    }

    @PostMapping("/check-certification")
    public ResponseEntity<? super CheckCertificationResponseDto> checkCertification (
        @RequestBody @Valid CheckCertificationRequestDto requestBody
    ) {
        ResponseEntity<? super CheckCertificationResponseDto> reponse = authService.checkCertification(requestBody);
        return reponse;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<? super SignUpResponseDto> signUP (
        @RequestBody @Valid SignUpRequestDto requestBody
    ){
        ResponseEntity<? super SignUpResponseDto> response = authService.signUp(requestBody);
        return response;

    }

    @PostMapping("/sign-in")
    public ResponseEntity<? super SignInResponseDto> signIN (
        @RequestBody @Valid SignInRequestDto requestBody
    ){
        ResponseEntity<? super SignInResponseDto> response = authService.signIn(requestBody);
        return response;
    }
    
    // @PostMapping("/sign-out")
    // public ResponseEntity<Void> signOut (HttpServletRequest request) {

    //     authService.logout();
    // }
    

}
    
    
