package com.danoc.danoc.dto.request.qna;

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
public class QnaEditRequestDto {

    @NotNull
    private Long qaId;

    @NotBlank
    private String title;

    @NotBlank
    private String ctnt;

    @NotBlank
    private String pwd;

    private List<String> qaImageList;

    @NotNull
    private Timestamp chg = new Timestamp(System.currentTimeMillis());
    
}
