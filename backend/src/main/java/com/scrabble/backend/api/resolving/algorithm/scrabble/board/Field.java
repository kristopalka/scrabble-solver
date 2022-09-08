package com.scrabble.backend.api.resolving.algorithm.scrabble.board;

import com.scrabble.backend.api.resolving.algorithm.scrabble.Alphabet;
import lombok.Getter;


public class Field {
    public enum Bonus {EMPTY, DOUBLE_LETTER, TRIPLE_LETTER, DOUBLE_WORD, TRIPLE_WORD}

    @Getter
    Bonus bonus;
    @Getter
    private char value;


    public Field(Bonus bonus) {
        this.value = Alphabet.getEmptySymbol();
        this.bonus = bonus;
    }

    public void setValue(char value) {
        if (!Alphabet.isAllowedCharacter(value)) throw new RuntimeException("This is not a letter: " + value);
        this.value = value;
    }

    public boolean isEmpty() {
        return Alphabet.isEmptySymbol(this.value);
    }
}
