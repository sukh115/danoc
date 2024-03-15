package com.danoc.danoc.dto.request.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignInRequestDto {
    
    @NotBlank
    private String username;

    @NotBlank 
    private String password;

    private String role;

    private Long userId;

}
