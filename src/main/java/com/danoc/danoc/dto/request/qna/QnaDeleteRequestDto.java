package com.danoc.danoc.dto.request.qna;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class QnaDeleteRequestDto {

    @NotNull
    private Long qaId;
    
}
