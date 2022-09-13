package com.scrabble.backend.resolving.algorithm;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;

import static com.scrabble.backend.resolving.algorithm.Word.Direction.HORIZONTAL;
import static com.scrabble.backend.resolving.algorithm.Word.Direction.VERTICAL;



public class Word {
    public enum Direction {VERTICAL, HORIZONTAL}

    @Getter private final String value;
    @Getter private final Direction direction;
    private final Point begin;
    @Getter @Setter private Point entry;
    @Getter @Setter private int points;
    @Getter @Setter private char[] usedLetters;


    public Word(String value, int xBegin, int yBegin, Direction direction) {
        this.value = value;
        this.begin = new Point(xBegin, yBegin);
        this.direction = direction;
    }

    public int getXBegin() {
        return begin.x;
    }

    public int getYBegin() {
        return begin.y;
    }


    public int getXEnd() {
        if (getDirection() == VERTICAL) return begin.x;
        else return begin.x + value.length();
    }

    public int getYEnd() {
        if (getDirection() == HORIZONTAL) return begin.y;
        else return begin.y + value.length();
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
