package com.scrabblewinner.scrabble.board.components;

import com.scrabblewinner.scrabble.alphabet.Alphabet;
import lombok.Getter;

import java.security.InvalidParameterException;


public class Field {
    @Getter
    Bonuses bonus;
    @Getter
    private char value;

    public Field(Bonuses bonus) {
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
