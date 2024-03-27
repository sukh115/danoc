package com.danoc.danoc.dto.response.comment;

import lombok.Getter;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.danoc.danoc.common.ResponseCode;
import com.danoc.danoc.common.ResponseMessage;
import com.danoc.danoc.dto.ResponseDto;
import com.danoc.danoc.dto.object.CommentListItem;
import com.danoc.danoc.repository.resultSet.CommentListResultSet;

@Getter
public class CommentListResponseDto extends ResponseDto {
    
    private List<CommentListItem> commentList;

    private CommentListResponseDto(List<CommentListResultSet> resultSets) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.commentList = CommentListItem.copyList(resultSets);
    }

    public static ResponseEntity<CommentListResponseDto> success(List<CommentListResultSet> resultSets) {
        CommentListResponseDto responseBody = new CommentListResponseDto(resultSets);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> qnaNotFound() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.QNA_NOT_FOUND, ResponseMessage.QNA_NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
    }
}
