package com.ScrabbleMachine.Components;

public class Word
{
    public String value;
    public int xStart;
    public int yStart;
    public boolean isVertical; //if false, is horizontal

    public Word(String value, int xStart, int yStart, boolean isVertical)
    {
        this.value = value;
        this.xStart = xStart;
        this.yStart = yStart;
        this.isVertical = isVertical;
    }

    public boolean isVertical()
    {
        return isVertical;
    }

    public boolean isHorizontal()
    {
        return !isVertical;
    }
}
