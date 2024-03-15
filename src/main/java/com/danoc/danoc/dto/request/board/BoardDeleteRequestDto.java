package com.danoc.danoc.dto.request.board;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardDeleteRequestDto {
    @NotNull
    private Long boardId;
}
