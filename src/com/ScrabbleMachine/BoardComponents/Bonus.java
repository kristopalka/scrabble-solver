package com.ScrabbleMachine.BoardComponents;

public class Bonus
{
    public static int EMPTY = 0;
    public static int DOUBLE_LETTER = 1;
    public static int TRIPLE_LETTER = 2;
    public static int DOUBLE_WORD = 3;
    public static int TRIPLE_WORD= 4;

    public static boolean isBonusValue(int value)
    {
        if (value == EMPTY ||
            value == DOUBLE_LETTER ||
            value == TRIPLE_LETTER ||
            value == DOUBLE_WORD ||
            value == TRIPLE_WORD) return true;
        return false;
    }
}
