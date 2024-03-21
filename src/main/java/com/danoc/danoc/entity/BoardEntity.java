package com.danoc.danoc.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.UpdateTimestamp;


import com.danoc.danoc.dto.request.board.BoardDeleteRequestDto;
import com.danoc.danoc.dto.request.board.BoardEditRequestDto;
import com.danoc.danoc.dto.request.board.BoardWriteRequestDto;


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
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "board")
@Table(name = "board")
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;


    private Long userId;

    private String title;
    private String ctnt;
    private String photo;
    private Long cate;
    private Timestamp date;
    @UpdateTimestamp
    private Timestamp boardChg;
    
    public BoardEntity(BoardWriteRequestDto dto, Long userId) {
        // this.boardId = dto.getBoardId();
        this.title = dto.getTitle();
        this.ctnt = dto.getCtnt();
        this.cate = dto.getCate();
        this.date = new Timestamp(System.currentTimeMillis());
        this.userId = userId;
            }
    // public void uploadPhoto(MultipartFile file, FileService fileStorageService) {
    //     if 
    //         (file != null && !file.isEmpty()) {
    //             String fileName = fileStorageService.storeFile(file);
    //             this.photo = fileName;
    //         }
        
    // }

    public BoardEntity(BoardDeleteRequestDto dto){

        this.boardId = dto.getBoardId();
    }

    public BoardEntity(BoardEditRequestDto dto){

        this.boardId = dto.getBoardId();
        this.title = dto.getTitle();
        this.ctnt = dto.getCtnt();
        this.cate = dto.getCate();
        this.boardChg = dto.getBoardChg();
    }


}