package com.scrabble.backend.resolving.dto;

import com.scrabble.backend.resolving.algorithm.solver.finder.Word;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;

@Data
@NoArgsConstructor
public class WordDto {
    private String value;
    private String direction;
    private Integer x;
    private Integer y;
    private Integer score;

    public WordDto(Word word) {
        this.value = word.value;
        this.direction = word.direction.toString();
        this.x = word.begin.x;
        this.y = word.begin.y;
        this.score = word.score;
    }
}
