package com.scrabble.backend.resolving.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.security.InvalidParameterException;

import static com.scrabble.backend.resolving.algorithm.scrabble.Static.*;

@Data
@ToString
@NoArgsConstructor
public class GameStateDto {
    private String[][] board;
    private String[] holder;
    private String lang;

    public char[][] preprocessBoard() {
        if (board.length != boardSize || board[0].length != boardSize)
            throw new InvalidParameterException("Invalid board size");

        char[][] charBoard = new char[boardSize][boardSize];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                String lowerCase = board[i][j].toLowerCase();
                throwIfIncorrectValue(lowerCase);

                charBoard[i][j] = lowerCase.charAt(0);
            }
        }
        return charBoard;
    }

    public String preprocessHolder() {
        if (holder.length != holderSize)
            throw new InvalidParameterException("Invalid holder size: " + holder.length);

        StringBuilder builder = new StringBuilder();
        for (String letter : holder) {
            String lowerCase = letter.toLowerCase();
            throwIfIncorrectValue(lowerCase);
            builder.append(lowerCase.charAt(0));
        }
        return builder.toString();
    }

    public void throwIfIncorrectValue(String symbol) {
        if (symbol.length() != 1) throw new InvalidParameterException("Invalid symbol \"" + symbol + "\"");
        if (!getAlphabet(lang).isLetterOrEmptySymbol(symbol.charAt(0)))
            throw new InvalidParameterException("Invalid letter: \"" + symbol + "\"");
    }
}
