package com.danoc.danoc.entity;

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
@Entity(name = "image")
@Table(name = "image")
public class ImageEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;
    
    private Long boardId;
    private String image;
    private Long qaId;

    public ImageEntity(Long boardIdOrQaId, String image, boolean isQaId) {
        if (isQaId) {
            this.qaId = boardIdOrQaId;
            this.boardId = null; // qaId를 설정하면 boardId는 null이 됩니다.
        } else {
            this.boardId = boardIdOrQaId;
            this.qaId = null; // boardId를 설정하면 qaId는 null이 됩니다.
        }
        this.image = image;
    }
}

