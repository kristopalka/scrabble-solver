package com.scrabble.backend.api.dto;

import com.scrabble.backend.solving.solver.finder.Word;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WordDto {
    private String value;
    private String direction;
    private Integer x;
    private Integer y;
    private Integer score;
    private String usedLetters;

    public WordDto(Word word) {
        this.value = word.value;
        this.direction = word.direction.toString();
        this.x = word.begin.x;
        this.y = word.begin.y;
        this.score = word.score;
        this.usedLetters = word.usedLetters;
    }
}
