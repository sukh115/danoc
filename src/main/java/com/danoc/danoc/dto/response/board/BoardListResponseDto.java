package com.danoc.danoc.dto.response.board;

import java.sql.Timestamp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.danoc.danoc.common.ResponseCode;
import com.danoc.danoc.common.ResponseMessage;
import com.danoc.danoc.dto.ResponseDto;
import com.danoc.danoc.entity.BoardEntity;

import lombok.Getter;

@Getter
public class BoardListResponseDto extends ResponseDto {
    
    private Long boardId;
    private Long cate;
    private String title;
    private Timestamp date;
    private Long userId;

    public BoardListResponseDto (BoardEntity boardEntity) {
        super();
        this.boardId = boardEntity.getBoardId();
        this.cate= boardEntity.getCate();
        this.title = boardEntity.getTitle();
        this.date = boardEntity.getDate();
        this.userId = boardEntity.getUserId();
    }

    public static ResponseEntity<BoardListResponseDto> success (BoardEntity boardEntity) {
        BoardListResponseDto responseBody = new BoardListResponseDto(boardEntity);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
    public static ResponseEntity<ResponseDto> notFound () {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_FOUND, ResponseMessage.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
    }
}
