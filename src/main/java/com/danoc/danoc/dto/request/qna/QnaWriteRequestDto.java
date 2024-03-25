package com.danoc.danoc.dto.request.qna;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class QnaWriteRequestDto {
    
    @NotBlank
    private String title;

    @NotBlank
    private String ctnt;

    @NotBlank
    private String pwd;

    private List<String> qaImageList;
}
