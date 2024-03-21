package com.danoc.danoc.service.implement;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.danoc.danoc.dto.ResponseDto;
import com.danoc.danoc.dto.response.user.GetSignInUserResponseDto;
import com.danoc.danoc.entity.UserEntity;
import com.danoc.danoc.repository.UserRepository;
import com.danoc.danoc.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImplement implements UserService {
    
    private final UserRepository userRepository;

    @Override    
public ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(Long userId) {
        
    Optional<UserEntity> userOptional = null;

    try {
            userOptional = userRepository.findByUserId(userId);
            if (!userOptional.isPresent()) return GetSignInUserResponseDto.userNotFound();
                
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return ResponseEntity.ok(GetSignInUserResponseDto.success(userOptional.get()));
    }

    
}