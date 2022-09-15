package com.scrabble.backend.resolving.algorithm;

import lombok.Getter;

import java.security.InvalidParameterException;
import java.util.Arrays;

import static com.scrabble.backend.resolving.algorithm.settings.Alphabet.emptySymbol;
import static com.scrabble.backend.resolving.algorithm.settings.Settings.boardSize;

public class BoardBuilder {
    @Getter
    protected int size;
    protected char[/*x*/][/*y*/] fields;

    public BoardBuilder(char[][] board) {
        this.size = boardSize;
        this.fields = new char[size][size];

        for (int i = 0; i < size; i++) {
            fields[i] = Arrays.copyOf(board[i], size);
        }
    }

    public BoardBuilder() {
        this.size = boardSize;
        this.fields = new char[size][size];

        for (char[] row : fields) {
            Arrays.fill(row, emptySymbol);
        }
    }

    public BoardBuilder addWord(Word word) {
        if (word.direction == Word.Direction.VERTICAL)
            addWordVertically(word.value, word.xBegin(), word.yBegin());
        else addWordHorizontally(word.value, word.xBegin(), word.yBegin());
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

    public BoardBuilder addLetter(char letter, int x, int y) {
        if (x < 0 || x >= size || y < 0 || y >= size)
            throw new InvalidParameterException(String.format("Given coordinates (%d,%d) goes beyond field", x, y));
        if (!(fields[x][y] == emptySymbol) && fields[x][y] != letter)
            throw new InvalidParameterException(String.format("Cannot override existing field: '%c' with '%c' (%d,%d)", fields[x][y], letter, x, y));

        fields[x][y] = letter;
        return this;
    }

    public char getField(int x, int y) {
        if (x < 0 || x >= size || y < 0 || y >= size)
            throw new InvalidParameterException(String.format("Given coordinates (%d,%d) goes beyond field", x, y));
        return fields[x][y];
    }

    public char[][] toCharArray() {
        return fields;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("+").append("--".repeat(Math.max(0, size))).append("+\n");

        for (int y = 0; y < size; y++) {
            builder.append("|");
            for (int x = 0; x < size; x++) {
                builder.append(fields[x][y]).append(' ');
            }
            builder.append("|\n");
        }
        builder.append("+").append("--".repeat(Math.max(0, size))).append("+\n");
        return builder.toString();
    }
}
