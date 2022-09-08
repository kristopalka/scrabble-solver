package com.scrabble.backend.api.resolving.algorithm.scrabble.holder;

import com.scrabble.backend.api.resolving.algorithm.scrabble.Alphabet;
import com.scrabble.backend.api.resolving.algorithm.scrabble.Word;
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
            throw new RuntimeException("This is not a letter to add: " + letter);

        letters.add(letter);
        return this;
    }

    public Holder get(char letter) {
        if (!Alphabet.isLetter(letter))
            throw new RuntimeException("This is not a letter to get: " + letter);

        try {
            letters.remove(letters.indexOf(letter));
        } catch (Exception e) {
            throw new RuntimeException("Cannot find letter: " + letter + " in holder: " + this);
        }
        return this;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append('[');
        for (int i = 0; i < letters.size() - 1; i++) builder.append(letters.get(i)).append(", ");
        builder.append(letters.get(letters.size() - 1)).append("]");

        return builder.toString();
    }

    public char[] toCharArray() {
        char[] array = new char[letters.size()];
        for (int i = 0; i < letters.size(); i++) {
            array[i] = letters.get(i);
        }
        return array;
    }

    public Holder fillInWithRandomLetters() {
        while (letters.size() < maxLettersNumber) {
            letters.add(Alphabet.getRandomLetter());
        }
        return this;
    }

    public Holder replaceAllLetters() {
        letters = new ArrayList<>();
        fillInWithRandomLetters();

        return this;
    }

    public Holder getWord(Word word) {
        for (char letter : word.getValue().toCharArray()) {
            get(letter);
        }
        return this;
    }
}
