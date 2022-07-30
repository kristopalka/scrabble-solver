package com.scrabblewinner.scrabble;

import com.scrabblewinner.scrabble.alphabet.Alphabet;
import com.scrabblewinner.scrabble.board.Board;
import com.scrabblewinner.scrabble.board.Field;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.scrabblewinner.scrabble.Word.Direction.*;

@AllArgsConstructor
@Getter
public class Word {
    public enum Direction {VERTICAL, HORIZONTAL}

    private final String value;
    private final int xBegin;
    private final int yBegin;
    private final Direction direction;


    public int getXEnd() {
        if (getDirection() == VERTICAL) return xBegin;
        else return xBegin + values().length;
    }

    public int getYEnd() {
        if (getDirection() == HORIZONTAL) return yBegin;
        else return yBegin + values().length;
    }

    public int getLength() {
        return getValue().length();
    }

    public char charAt(int index) {
        return getValue().charAt(index);
    }


    @Override
    public String toString() {
        return String.format("[\"%s\" (%d, %d) %s]", getValue(), getXBegin(), getYBegin(), getDirection().toString());
    }
}
