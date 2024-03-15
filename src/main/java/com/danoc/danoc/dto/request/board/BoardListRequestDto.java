package com.danoc.danoc.dto.request.board;

import java.sql.Timestamp;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardListRequestDto {
    
    @NotBlank
    private Long boardId;

    @NotBlank 
    private String title;

    @NotBlank
    private String ctnt;

    @NotBlank 
    private Long cate;

    @NotBlank
    private Timestamp date;

    @NotBlank
    private Long userId;
}
