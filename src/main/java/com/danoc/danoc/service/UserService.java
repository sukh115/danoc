package com.danoc.danoc.service;

import org.springframework.http.ResponseEntity;

import com.danoc.danoc.dto.response.user.GetSignInUserResponseDto;

public interface UserService {
    ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(Long userId);
}
