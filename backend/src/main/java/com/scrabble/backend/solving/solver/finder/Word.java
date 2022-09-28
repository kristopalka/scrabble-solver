package com.scrabble.backend.solving.solver.finder;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static com.scrabble.backend.solving.solver.finder.Word.Direction.HORIZONTAL;
import static com.scrabble.backend.solving.solver.finder.Word.Direction.VERTICAL;


@ToString
@EqualsAndHashCode
@Getter
public class Word {
    public enum Direction {
        VERTICAL, HORIZONTAL;

        public Direction getOpposite() {
            if(this == VERTICAL) return HORIZONTAL;
            return VERTICAL;
        }
    }

    public String value;
    public Direction direction;
    public Point begin;

    public Point entryBegin;
    public int entryLength;

    public String usedLetters;

    public Integer score;
    public List<Word> additionalWords;

    public Word(String value, int xBegin, int yBegin, Direction direction) {
        this.value = value;
        this.begin = new Point(xBegin, yBegin);
        this.direction = direction;
        additionalWords = new ArrayList<>();
    }

    public Word(String value, Point begin, Direction direction, Point entryBegin, int entryLength) {
        this.value = value;
        this.begin = begin;
        this.direction = direction;
        this.entryBegin = entryBegin;
        this.entryLength = entryLength;
        additionalWords = new ArrayList<>();
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

    public Point pointAt(int index) {
        if (direction == Word.Direction.VERTICAL) return new Point(begin.x, begin.y + index);
        return new Point(begin.x + index, begin.y);
    }


}
