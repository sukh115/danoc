package com.danoc.danoc.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.danoc.danoc.provider.JwtProvider;


@Component
public class JwtUtil {
    
    @Autowired
    private JwtProvider jwtProvider;

    public Long extractUserId(String token) {
        return jwtProvider.extractUserId(token);
    }
}
