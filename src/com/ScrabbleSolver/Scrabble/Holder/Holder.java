package com.ScrabbleSolver.Scrabble.Holder;

import java.util.ArrayList;

public abstract class Holder {
    protected int maxLettersNumber;
    protected ArrayList<Character> letters;

    public ArrayList<Character> getLetters() {
        return letters;
    }

    public char[] toCharArray() {
        char[] array = new char[letters.size()];
        for (int i=0; i<letters.size(); i++) {
            array[i] = letters.get(i);
        }
        return array;
    }
}
