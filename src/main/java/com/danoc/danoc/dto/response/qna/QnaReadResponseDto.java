package com.danoc.danoc.dto.response.qna;

import com.danoc.danoc.dto.ResponseDto;

import java.sql.Timestamp;
import java.util.List;

public class QnaReadResponseDto extends ResponseDto{

    private Long qaId;
    private String title;
    private String ctnt;
    private List<String> qaImageLIst;
    private TimeStamp date;
    private Long UserId;
    private String name;
    
}
