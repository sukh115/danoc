package com.danoc.danoc.entity;

import java.sql.Timestamp;

import com.danoc.danoc.dto.request.qna.QnaWriteRequestDto;

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
@Entity
@Table(name = "qna")
public class QnaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qa_id")
    private Long qaId;

    private Long userId;

    private String title;

    private String ctnt;

    private String pwd;

    private Timestamp date;

    private Timestamp chg;

    public QnaEntity(QnaWriteRequestDto dto, Long userId) {
        this.title = dto.getTitle();
        this.ctnt = dto.getCtnt();
        this.pwd = dto.getPwd();
        this.date = new Timestamp(System.currentTimeMillis());
        this.userId = userId;
    }

}
