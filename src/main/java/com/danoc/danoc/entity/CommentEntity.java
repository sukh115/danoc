package com.danoc.danoc.entity;

import java.sql.Timestamp;

import com.danoc.danoc.dto.request.comment.CommentWriteRequestDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userId;

    @ManyToOne(fetch = FetchType.LAZY)
    private QnaEntity qaId;

    private String content;

    private Timestamp date;

    private Timestamp chg;

    public CommentEntity(CommentWriteRequestDto dto, QnaEntity qaId,UserEntity userId) {
        this.content = dto.getContent();
        this.date = new Timestamp(System.currentTimeMillis());
        this.userId = userId;
        this.qaId = qaId;
    }

}
