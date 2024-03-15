package com.danoc.danoc.dto.request.auth;


import java.sql.Timestamp;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignUpRequestDto {
    
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String certificationNumber;

    @NotBlank
    private String name;

    @NotBlank 
    private String tel;

    private String role;

    @NotNull(message = "Date cannot be null")
    private Timestamp date = new Timestamp(System.currentTimeMillis());


}
