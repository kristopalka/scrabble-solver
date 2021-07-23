package com.scrabblewinner.scrabble.board;

import com.scrabblewinner.scrabble.board.components.Direction;
import com.scrabblewinner.scrabble.board.components.Field;
import com.scrabblewinner.scrabble.board.components.Word;
import lombok.Getter;

import java.security.InvalidParameterException;
import java.util.Locale;

public abstract class Board {
    @Getter
    protected int length;
    protected Field[/*x*/][/*y*/] fields;


    public void addWord(Word word) {
        if (word.direction == Direction.VERTICAL) addWordVertically(word.value, word.xStart, word.yStart);
        else addWordHorizontally(word.value, word.xStart, word.yStart);
    }

    private void addWordHorizontally(String word, int xStart, int yStart) {
        if (xStart < 0 || xStart >= length || yStart < 0 || yStart >= length)
            throw new InvalidParameterException("Start parameter out of range");
        if (xStart + word.length() >= length + 1) throw new InvalidParameterException("Word goes off board");

        for (int x = 0; x < word.length(); x++) {
            insertValueToField(word.charAt(x), x + xStart, yStart);
        }
    }

    private void addWordVertically(String word, int xStart, int yStart) {
        if (xStart < 0 || xStart >= length || yStart < 0 || yStart >= length)
            throw new InvalidParameterException("Start parameter out of range");
        if (yStart + word.length() >= length + 1) throw new InvalidParameterException("Word goes off board");

        for (int y = 0; y < word.length(); y++) {
            insertValueToField(word.charAt(y), xStart, yStart + y);
        }
    }

    private void insertValueToField(char letter, int x, int y) {
        if (!fields[x][y].isEmpty() && fields[x][y].getValue() != letter)
            throw new InvalidParameterException(String.format("Cannot override existing field: '%c' with '%c' (%d,%d)", fields[x][y].getValue(), letter, x, y));
        fields[x][y].setValue(letter);
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
        for (int y = 0; y < length; y++) {
            for (int x = 0; x < length; x++) {
                builder.append(fields[x][y].getValue());
                builder.append(' ');
            }
            builder.append('\n');
        }
        return builder.toString();
    }


}
