package com.scrabble.backend.api.dto;

import lombok.Data;

@Data
public class GameStateDto {
    private String[][] board;
    private String[] rack;
}
