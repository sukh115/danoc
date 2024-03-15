package com.danoc.danoc.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="certification")
@Table(name ="certification")
public class CertificationEntity {

    @Id
    @Column(name = "id")
    private String username;
    
    private String email;
    
    private String certificationNumber;
}
