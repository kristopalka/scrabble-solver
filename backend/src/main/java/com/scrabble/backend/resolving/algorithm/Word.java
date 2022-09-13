package com.scrabble.backend.resolving.algorithm;

import lombok.ToString;

import java.awt.*;

import static com.scrabble.backend.resolving.algorithm.Word.Direction.HORIZONTAL;
import static com.scrabble.backend.resolving.algorithm.Word.Direction.VERTICAL;


@ToString
public class Word {
    public enum Direction {VERTICAL, HORIZONTAL}

    public String value;
    public Direction direction;
    public Point begin;
    public Point entryBegin;
    public int entryLength;
    public int score;

    public Word(String value, int xBegin, int yBegin, Direction direction) {
        this.value = value;
        this.begin = new Point(xBegin, yBegin);
        this.direction = direction;
    }

    public Word(String value, Point begin, Direction direction, Point entryBegin, int entryLength) {
        this.value = value;
        this.begin = begin;
        this.direction = direction;
        this.entryBegin = entryBegin;
        this.entryLength = entryLength;
    }

    public int xBegin() {
        return begin.x;
    }

    public int yBegin() {
        return begin.y;
    }


    public int xEnd() {
        if (direction == VERTICAL) return begin.x;
        else return begin.x + value.length() - 1;
    }

    public int yEnd() {
        if (direction == HORIZONTAL) return begin.y;
        else return begin.y + value.length() - 1;
    }

    public int length() {
        return value.length();
    }

    public char charAt(int index) {
        return value.charAt(index);
    }


    public static Point transposePoint(Point point){
        return new Point(point.y, point.x);
    }
}
