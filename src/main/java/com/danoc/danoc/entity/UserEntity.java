package com.danoc.danoc.entity;



import com.danoc.danoc.dto.request.auth.SignInRequestDto;
import com.danoc.danoc.dto.request.auth.SignUpRequestDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="user")
@Table(name="user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    private String role;

    @Column(name = "username")
    private String username;

    private String email;
    private String password;

    private String name;
    
    private String tel;
    
    private java.sql.Timestamp date;
    
    private java.sql.Timestamp chg;

    private String type;

    public UserEntity(Long userId) {
        this.userId = userId;
    }

    public UserEntity (SignInRequestDto dto) {
        this.userId =dto.getUserId();
        this.username = dto.getUsername();
        this.password = dto.getPassword();
        this.role = dto.getRole();
    }
    

    public UserEntity (SignUpRequestDto dto) {
        this.username = dto.getUsername();
        this.password = dto.getPassword();
        this.email = dto.getEmail();
        this.name = dto.getName();
        this.tel = dto.getTel();
        this.date = dto.getDate();
        this.type = "app";
        this.role = "ROLE_USER";
    }

    public UserEntity (String username, String email,String name, String type, java.sql.Timestamp date) {
        this.username = username;
        this.password = "Passw0rd";
        this.email = email;
        this.name = name;
        this.tel = "unknown";
        this.date = date;
        this.type = type;
        this.role = "ROLE_USER";
    }

    // 게시글 수정
    public void edit(String newPassword, String newUsername) {
        this.password = newPassword;
        this.username = newUsername;
    }

}
