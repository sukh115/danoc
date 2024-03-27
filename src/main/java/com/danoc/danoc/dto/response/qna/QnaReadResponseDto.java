package com.danoc.danoc.dto.response.qna;

import com.danoc.danoc.common.ResponseCode;
import com.danoc.danoc.common.ResponseMessage;
import com.danoc.danoc.dto.ResponseDto;
import com.danoc.danoc.entity.ImageEntity;
import com.danoc.danoc.repository.resultSet.QnaReadResultSet;

import lombok.Getter;

import java.sql.Timestamp;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

@Getter
    public class QnaReadResponseDto extends ResponseDto{

        private Long qaId;
        private String title;
        private String ctnt;
        private List<String> qnaImageList;
        private Timestamp date;
        private Long userId;
        private String name;

        private QnaReadResponseDto (QnaReadResultSet resultSet, List<ImageEntity> imageEntities) {
            super();

            List<String> qnaImageList = new ArrayList<>();
            for (ImageEntity imageEntity: imageEntities) {
                String qnaImage = imageEntity.getImage();
                qnaImageList.add(qnaImage);
            }

            this.qaId = resultSet.getQaId();
            this.title = resultSet.getTitle();
            this.ctnt = resultSet.getCtnt();
            this.qnaImageList = qnaImageList;
            this.date = resultSet.getDate();
            this.userId = resultSet.getUserId();
            this.name = resultSet.getName();
        }

        public static ResponseEntity<QnaReadResponseDto> success(QnaReadResultSet readResultSet, List<ImageEntity> imageEntities) {
            QnaReadResponseDto responseBody = new QnaReadResponseDto(readResultSet, imageEntities);
            return ResponseEntity.status(HttpStatus.OK).body(responseBody);
        }

        public static ResponseEntity<ResponseDto> boardNotFound() {
            ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_FOUND, ResponseMessage.NOT_FOUND);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
        }


    
}
