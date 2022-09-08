package com.scrabble.backend.algorithm.scrabble.board;

import com.scrabble.backend.algorithm.scrabble.Word;
import com.scrabble.backend.algorithm.scrabble.Alphabet;
import lombok.Getter;

import java.security.InvalidParameterException;

public abstract class Board {
    @Getter
    protected int length;
    protected Field[/*x*/][/*y*/] fields;


    public Board addWord(Word word) {
        if (word.getDirection() == Word.Direction.VERTICAL) addWordVertically(word.getValue(), word.getXBegin(), word.getYBegin());
        else addWordHorizontally(word.getValue(), word.getXBegin(), word.getYBegin());
        return this;
    }

    private void addWordHorizontally(String word, int xStart, int yStart) {
        for (int x = 0; x < word.length(); x++) {
            addLetter(word.charAt(x), x + xStart, yStart);
        }
    }

    private void addWordVertically(String word, int xStart, int yStart) {
        for (int y = 0; y < word.length(); y++) {
            addLetter(word.charAt(y), xStart, yStart + y);
        }
    }

    public Board addLetter(char letter, int x, int y) {
        if (x < 0 || x >= length || y < 0 || y >= length)
            throw new InvalidParameterException(String.format("Given coordinates (%d,%d) goes beyond field", x, y));
        if (!fields[x][y].isEmpty() && fields[x][y].getValue() != letter)
            throw new InvalidParameterException(String.format("Cannot override existing field: '%c' with '%c' (%d,%d)", fields[x][y].getValue(), letter, x, y));
        fields[x][y].setValue(letter);

        return this;
    }


    public Field getField(int x, int y) {
        if (x < 0 || x >= length || y < 0 || y >= length)
            throw new InvalidParameterException(String.format("Given coordinates (%d,%d) goes beyond field", x, y));
        return fields[x][y];
    }

    public char[][] toCharArray() {
        char[][] array = new char[length][length];
        for (int x = 0; x < length; x++) {
            for (int y = 0; y < length; y++) {
                array[x][y] = fields[x][y].getValue();
            }
        }
        return array;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("+").append("--".repeat(Math.max(0, length))).append("+\n");

        for (int y = 0; y < length; y++) {
            builder.append("|");
            for (int x = 0; x < length; x++) {
                builder.append(fields[x][y].getValue()).append(' ');
            }
            builder.append("|\n");
        }
        builder.append("+").append("--".repeat(Math.max(0, length))).append("+\n");
        return builder.toString();
    }


    public int howManyPointsForWord(Word word) {
        int totalPoints = 0;
        int multiplier = 1;

        for (int i = 0; i < word.getLength(); i++) {
            Field.Bonus bonus;
            if (word.getDirection() == Word.Direction.VERTICAL) bonus = getField(word.getXBegin(), word.getYBegin() + i).getBonus();
            else bonus = getField(word.getXBegin() + i, word.getYBegin()).getBonus();


            int letterPoints = Alphabet.valueOfLetter(word.charAt(i));

            switch (bonus) {
                case EMPTY -> totalPoints += letterPoints;
                case DOUBLE_LETTER -> totalPoints += 2 * letterPoints;
                case TRIPLE_LETTER -> totalPoints += 3 * letterPoints;
                case DOUBLE_WORD -> {
                    totalPoints += letterPoints;
                    multiplier *= 2;
                }
                case TRIPLE_WORD -> {
                    totalPoints += letterPoints;
                    multiplier *= 3;
                }
            }
        }
        totalPoints *= multiplier;
        return totalPoints;
    }

}
