package com.scrabble.backend.resolving.dto;

import lombok.Setter;
import lombok.ToString;

import static com.scrabble.backend.resolving.algorithm.scrabble.Static.*;
import static com.scrabble.backend.resolving.algorithm.scrabble.resources.Alphabet.emptySymbol;

@Setter
@ToString
public class RequestDto {
    private String[][] board;
    private String[] holder;
    private String lang;
    private String mode;
    private Integer number;

    public String getMode() {
        return mode == null ? "score" : mode;
    }

    public Integer getNumber() {
        return number == null ? 5 : number;
    }

    public String getLang() {
        if (lang == null) throw new IllegalArgumentException("Language is mandatory");
        if (!supportedLanguages.contains(lang))
            throw new IllegalArgumentException("Language \"" + lang + "\" is not supported");

        return lang;
    }

    public char[][] getBoard() {
        if (board == null) throw new IllegalArgumentException("Board is mandatory");
        if (board.length != boardSize || board[0].length != boardSize)
            throw new IllegalArgumentException("Invalid board size");

        char[][] charBoard = new char[boardSize][boardSize];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                String lowerCase = board[i][j].length() == 0 ? String.valueOf(emptySymbol) : board[i][j].toLowerCase();

                throwIfIncorrectValue(lowerCase);

                charBoard[i][j] = lowerCase.charAt(0);
            }
        }
        return charBoard;
    }

    public String getHolder() {
        if (holder == null) throw new IllegalArgumentException("Holder is mandatory");
        if (holder.length != holderSize)
            throw new IllegalArgumentException("Invalid holder size: " + holder.length);

        StringBuilder builder = new StringBuilder();
        for (String letter : holder) {
            String lowerCase = letter.length() == 0 ? String.valueOf(emptySymbol) : letter.toLowerCase();
            throwIfIncorrectValue(lowerCase);
            builder.append(lowerCase.charAt(0));
        }
        return builder.toString();
    }

    public void throwIfIncorrectValue(String symbol) {
        if (symbol.length() != 1) throw new IllegalArgumentException("Invalid symbol \"" + symbol + "\"");
        if (!getAlphabet(lang).isLetterOrEmptySymbol(symbol.charAt(0)))
            throw new IllegalArgumentException("Invalid letter: \"" + symbol + "\"");
    }
}
