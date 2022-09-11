package com.scrabble.backend.resolving.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResolveRequestDto {
    private char[][] board;
    private char[] holder;
}
