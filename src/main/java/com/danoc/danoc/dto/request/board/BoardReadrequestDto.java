package com.danoc.danoc.dto.request.board;

import java.sql.Timestamp;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardReadrequestDto {
    @NotBlank
    private Long boardId;

    @NotBlank 
    private String title;

    @NotBlank
    private String ctnt;

    @NotBlank 
    private Long cate;
    
    private List<String> boardImageList;

    @NotBlank
    private Timestamp date;

}
