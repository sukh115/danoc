package com.danoc.danoc.dto.request.board;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardWriteRequestDto {

    @NotBlank
    private String title;

    @NotBlank
    private String ctnt;

    @NotNull(message = "Category must not be null")
    private Long cate;

    private List<String> boardImageList;

    
}
