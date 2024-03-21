package com.danoc.danoc.dto.response.board;

import java.util.List;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.danoc.danoc.common.ResponseCode;
import com.danoc.danoc.common.ResponseMessage;
import com.danoc.danoc.dto.ResponseDto;
import com.danoc.danoc.entity.ImageEntity;
import com.danoc.danoc.repository.resultSet.BoardReadResultSet;

import lombok.Getter;

@Getter
public class BoardReadResponseDto extends ResponseDto {

    private Long boardId;
    private String title;
    private String ctnt;
    private List<String> boardImageList;
    private Long cate;
    private Timestamp date;
    private Long userId;
    private String name;

    private BoardReadResponseDto (BoardReadResultSet resultSet, List<ImageEntity> imageEntities) {
        super();
        
        List<String> boardImageList = new ArrayList<>();
        for (ImageEntity imageEntity: imageEntities) {
            String boardImage = imageEntity.getImage();
            boardImageList.add(boardImage);
        }

        this.boardId = resultSet.getBoardId();
        this.title = resultSet.getTitle();
        this.ctnt = resultSet.getCtnt();
        this.boardImageList = boardImageList;
        this.cate = resultSet.getCate();
        this.date = resultSet.getDate();
        this.userId = resultSet.getUserId();
        this.name = resultSet.getName();
    }

    public static ResponseEntity<BoardReadResponseDto> success(BoardReadResultSet resultSet, List<ImageEntity> imageEntities) {
        BoardReadResponseDto responseBody = new BoardReadResponseDto(resultSet, imageEntities);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
    public static ResponseEntity<ResponseDto> boardNotFound() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_FOUND, ResponseMessage.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
    }
}