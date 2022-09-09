package com.scrabble.backend.resolving.algorithm;

import lombok.Getter;
import lombok.Setter;

import static com.scrabble.backend.resolving.algorithm.Word.Direction.HORIZONTAL;
import static com.scrabble.backend.resolving.algorithm.Word.Direction.VERTICAL;


@Getter
public class Word {
    public enum Direction {VERTICAL, HORIZONTAL}

    private final String value;
    private final int xBegin;
    private final int yBegin;
    private final Direction direction;
    @Setter
    private int points;


    public Word(String value, int xBegin, int yBegin, Direction direction) {
        this.value = value;
        this.xBegin = xBegin;
        this.yBegin = yBegin;
        this.direction = direction;
    }


    public int getXEnd() {
        if (getDirection() == VERTICAL) return xBegin;
        else return xBegin + Direction.values().length;
    }

    public int getYEnd() {
        if (getDirection() == HORIZONTAL) return yBegin;
        else return yBegin + Direction.values().length;
    }

    public int getLength() {
        return getValue().length();
    }

    public char charAt(int index) {
        return getValue().charAt(index);
    }


    @Override
    public String toString() {
        return String.format("[\"%s\" (%d, %d) %s %d]", getValue(), getXBegin(), getYBegin(), getDirection().toString(), points);
    }
}
