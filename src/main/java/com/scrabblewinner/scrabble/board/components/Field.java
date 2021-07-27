package com.scrabblewinner.scrabble.board.components;

import com.scrabblewinner.scrabble.alphabet.Alphabet;
import lombok.Getter;

import java.security.InvalidParameterException;


public class Field {
    @Getter
    Bonus bonus;
    @Getter
    private char value;

    public Field(Bonus bonus) {
        this.value = Alphabet.getEmptySymbol();
        this.bonus = bonus;
    }

    public void setValue(char value) {
        if (!Alphabet.isAllowedCharacter(value)) throw new InvalidParameterException("This is not a letter: " + value);
        this.value = value;
    }

    public boolean isEmpty() {
        return Alphabet.isEmptySymbol(this.value);
    }

}
