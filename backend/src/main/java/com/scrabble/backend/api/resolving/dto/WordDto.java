package com.scrabble.backend.api.resolving.dto;

import com.scrabble.backend.api.resolving.algorithm.scrabble.Word;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WordDto {
    private String value;
    private int xBegin;
    private int yBegin;
    private String direction;

    private WordDto(Word word) {
        this.value = word.getValue();
        this.xBegin = word.getXBegin();
        this.yBegin = word.getYBegin();
        this.direction = word.getDirection().toString();
    }
}
