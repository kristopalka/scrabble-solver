package com.scrabblewinner.scrabble.holder;

import lombok.Getter;

import java.util.ArrayList;


public abstract class Holder {
    protected int maxLettersNumber;
    @Getter
    protected ArrayList<Character> letters;

    public Holder addLetter(char letter) {
        if(letters.size() < maxLettersNumber){
            letters.add(letter);
            return this;
        }
        else throw new RuntimeException("Holder is full, cannot add new letter: " + letter);
    }

    public char[] toCharArray() {
        char[] array = new char[letters.size()];
        for (int i = 0; i < letters.size(); i++) {
            array[i] = letters.get(i);
        }
        return array;
    }
}
