package com.scrabble.backend.resolving.dto;

import com.scrabble.backend.resolving.algorithm.Word;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WordDto {
    private String value;
    private int xBegin;
    private int yBegin;
    private String direction;

    public WordDto(Word word) {
        this.value = word.value;
        this.xBegin = word.begin.x;
        this.yBegin = word.begin.y;
        this.direction = word.direction.toString();
    }
}
