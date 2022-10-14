package com.scrabble.backend.solving.scrabble;

import com.scrabble.backend.solving.solver.finder.Word;
import lombok.Getter;

import java.util.Arrays;

import static com.scrabble.backend.solving.scrabble.resources.Alphabet.emptySymbol;

public class BoardBuilder {
    @Getter
    protected int size;
    protected char[/*x*/][/*y*/] fields;

    public BoardBuilder(char[][] board) {
        this.size = ScrabbleResources.boardSize;
        this.fields = new char[size][size];

        for (int i = 0; i < size; i++) {
            fields[i] = Arrays.copyOf(board[i], size);
        }
    }

    public BoardBuilder() {
        this.size = ScrabbleResources.boardSize;
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

    private void addLetter(char letter, int x, int y) {
        if (x < 0 || x >= size || y < 0 || y >= size)
            throw new IllegalArgumentException(String.format("Given coordinates (%d,%d) goes beyond field", x, y));
        if (!(fields[x][y] == emptySymbol) && fields[x][y] != letter)
            throw new IllegalArgumentException(String.format("Cannot override existing field: '%c' with '%c' (%d,%d)", fields[x][y], letter, x, y));

        fields[x][y] = letter;
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
