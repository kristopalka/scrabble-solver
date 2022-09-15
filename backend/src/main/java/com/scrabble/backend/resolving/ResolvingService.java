package com.scrabble.backend.resolving;

import com.scrabble.backend.resolving.algorithm.solver.finder.Word;
import com.scrabble.backend.resolving.dto.GameStateDto;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.List;

import static com.scrabble.backend.resolving.algorithm.scrabble.Static.*;
import static com.scrabble.backend.resolving.algorithm.scrabble.resources.Alphabet.emptySymbol;
import static com.scrabble.backend.resolving.algorithm.solver.Solver.getWordsByBestScore;
import static com.scrabble.backend.resolving.algorithm.solver.Solver.getWordsByLength;

@Service
public class ResolvingService {
    public static String preprocessHolder(char[] holderArray, String lang) {
        if (holderArray.length != holderSize)
            throw new InvalidParameterException("Invalid holder size: " + holderArray.length);

        StringBuilder builder = new StringBuilder();
        for (char letter : holderArray) {
            if (letter != emptySymbol) {
                char lowerCaseLetter = Character.toLowerCase(letter);

                getAlphabet(lang).throwIfIncorrectSymbol(lowerCaseLetter);
                builder.append(lowerCaseLetter);
            }
        }
        return builder.toString();
    }

    public List<Word> bestWords(GameStateDto gameState, String mode, int number) {
        String lang = gameState.getLang();
        String holder = preprocessHolder(gameState.getHolder(), lang);
        char[][] board = preprocessBoard(gameState.getBoard(), lang);

        return switch (mode) {
            case "length" -> getWordsByLength(board, holder, lang, number);
            case "score", default -> getWordsByBestScore(board, holder, lang, number);
        };
    }

    private static char[][] preprocessBoard(char[][] board, String lang) {
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

}

