package com.scrabble.backend.resolving.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.security.InvalidParameterException;

import static com.scrabble.backend.resolving.algorithm.scrabble.Static.*;
import static com.scrabble.backend.resolving.algorithm.scrabble.resources.Alphabet.emptySymbol;

@Data
@ToString
@NoArgsConstructor
public class GameStateDto {
    private char[][] board;
    private char[] holder;
    private String lang;

    public char[][] preprocessBoard() {
        if (board.length != boardSize || board[0].length != boardSize)
            throw new InvalidParameterException("Invalid board size");

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] != emptySymbol) {
                    board[i][j] = Character.toLowerCase(board[i][j]);
                    getAlphabet(lang).throwIfIncorrectSymbol(board[i][j]);
                }
            }
        }
        return board;
    }

    public String preprocessHolder() {
        if (holder.length != holderSize)
            throw new InvalidParameterException("Invalid holder size: " + holder.length);

        StringBuilder builder = new StringBuilder();
        for (char letter : holder) {
            if (letter != emptySymbol) {
                char lowerCaseLetter = Character.toLowerCase(letter);

                getAlphabet(lang).throwIfIncorrectSymbol(lowerCaseLetter);
                builder.append(lowerCaseLetter);
            }
        }
        return builder.toString();
    }
}
