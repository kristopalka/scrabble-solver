package com.scrabblewinner.scrabble.board.components;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class Word {
    public String value;
    public int xStart;
    public int yStart;
    public Direction direction;

    public int getLength() {
        return value.length();
    }

    public char charAt(int index) {
        return value.charAt(index);
    }
}
