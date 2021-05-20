package com.ScrabbleSolver.Scrabble.Board.Components;

public class Word
{
    public String value;
    public int xStart;
    public int yStart;
    public Direction direction;

    public Word(String value, int xStart, int yStart, Direction direction)
    {
        this.value = value;
        this.xStart = xStart;
        this.yStart = yStart;
        this.direction = direction;
    }

}
