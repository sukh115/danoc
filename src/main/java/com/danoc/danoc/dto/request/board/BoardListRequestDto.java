package com.danoc.danoc.dto.request.board;

import java.sql.Timestamp;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardListRequestDto {
    
    @NotNull
    private Long boardId;

    @NotBlank 
    private String title;

    @NotNull 
    private Long cate;

    @NotNull
    private Timestamp date;

    @NotNull
    private Long userId;
}
