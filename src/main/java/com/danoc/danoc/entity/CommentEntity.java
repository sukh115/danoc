package com.danoc.danoc.entity;

import java.sql.Timestamp;

import com.danoc.danoc.dto.request.qna.QnaCommentWriteRequestDto;

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
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "comment")
@Table(name = "comment")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    private Long userId;

    private Long qaId;

    private String ctnt;

    private Timestamp date;

    private Timestamp chg;

    public CommentEntity(QnaCommentWriteRequestDto dto, Long userId,Long qaId) {
        this.ctnt = dto.getCtnt();
        this.date = new Timestamp(System.currentTimeMillis());
        this.userId = userId;
        this.qaId = qaId;
    }

}
