package com.danoc.danoc.dto.request.board;

import java.sql.Timestamp;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardEditRequestDto {

    @NotNull
    private Long boardId;

    @NotBlank
    private String title;

    @NotBlank
    private String ctnt;

    @NotNull
    private Long cate;

    private List<String> boardImageList;

    @NotNull
    private Timestamp boardChg = new Timestamp(System.currentTimeMillis());
    
}
