package com.scrabblewinner.scrabble.holder;

import com.scrabblewinner.scrabble.alphabet.Alphabet;
import com.scrabblewinner.scrabble.Word;
import com.scrabblewinner.utility.exceptions.IncorrectLetterException;
import com.scrabblewinner.utility.exceptions.NoGivenLetterInHolderException;
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
            throw new IncorrectLetterException("This is not a letter to add: " + letter);

        letters.add(letter);
        return this;
    }

    public Holder get(char letter) {
        if (!Alphabet.isLetter(letter))
            throw new IncorrectLetterException("This is not a letter to get: " + letter);

        try {
            letters.remove(letters.indexOf(letter));
        } catch (Exception e) {
            throw new NoGivenLetterInHolderException("Cannot find letter: " + letter + " in holder: " + this);
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

    public Holder selectLettersForWord (Word word) {
        for(char letter : word.getValue().toCharArray()) {
            get(letter);
        }
        return this;
    }
}
