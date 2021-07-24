package com.scrabblewinner.scrabble.holder;

import com.scrabblewinner.scrabble.alphabet.Alphabet;
import lombok.Getter;

import java.util.ArrayList;


public abstract class Holder {
    protected int maxLettersNumber;
    @Getter
    protected ArrayList<Character> letters;

    public Holder add(char letter) {
        if (letters.size() >= maxLettersNumber)
            throw new RuntimeException("Holder is full, cannot add new letter: " + letter);
        if (!Alphabet.isLetter(letter))
            throw new RuntimeException("This is not correct letter to add: " + letter);

        letters.add(letter);
        return this;
    }

    public Holder get(char letter) {
        if (letters.size() == 0)
            throw new RuntimeException("Holder is empty");
        if (!Alphabet.isLetter(letter))
            throw new RuntimeException("This is not correct letter to get: " + letter);

        try{
            letters.remove(letters.indexOf(letter));
        } catch (Exception e) {
            throw new RuntimeException("Cannot find letter: " + letter + " in holder");
        }
        return this;
    }

    public char[] toCharArray() {
        char[] array = new char[letters.size()];
        for (int i = 0; i < letters.size(); i++) {
            array[i] = letters.get(i);
        }
        return array;
    }
}
