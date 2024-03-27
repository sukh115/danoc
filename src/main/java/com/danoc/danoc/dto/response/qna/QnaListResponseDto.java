package com.danoc.danoc.dto.response.qna;

import java.sql.Timestamp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.danoc.danoc.common.ResponseCode;
import com.danoc.danoc.common.ResponseMessage;
import com.danoc.danoc.dto.ResponseDto;
import com.danoc.danoc.entity.QnaEntity;

import lombok.Getter;

@Getter
public class QnaListResponseDto extends ResponseDto{
    
    private Long qaId;
    private String title;
    private Timestamp date;
    private Long userId;

    public QnaListResponseDto (QnaEntity qnaEntity) {
        super();
        this.qaId = qnaEntity.getQaId();
        this.title = qnaEntity.getTitle();
        this.date = qnaEntity.getDate();
        this.userId = qnaEntity.getUserId();
    }

    public static ResponseEntity<QnaListResponseDto> success (QnaEntity qnaEntity) {
        QnaListResponseDto responseBody = new QnaListResponseDto(qnaEntity);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
    public static ResponseEntity<ResponseDto> notFound () {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_FOUND, ResponseMessage.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
    }
}
