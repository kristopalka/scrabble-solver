package com.scrabble.backend.resolving.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class GameStateDto {
    private char[][] board;
    private char[] holder;
    private String lang;
}
