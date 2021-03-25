package com.ScrabbleMachine.Components.Holder;

import java.util.ArrayList;

public abstract class Holder
{
    protected int maxLettersNumber;
    protected ArrayList<Character> letters;

    public ArrayList<Character> getLetters()
    {
        return letters;
    }
}
