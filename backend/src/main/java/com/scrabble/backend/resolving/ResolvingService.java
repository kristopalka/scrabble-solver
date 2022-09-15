package com.scrabble.backend.resolving;

import com.scrabble.backend.resolving.algorithm.Word;
import com.scrabble.backend.resolving.algorithm.settings.Alphabet;
import com.scrabble.backend.resolving.dto.GameStateDto;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.List;

import static com.scrabble.backend.resolving.algorithm.settings.Settings.boardSize;
import static com.scrabble.backend.resolving.algorithm.solver.Solver.getWordsByBestScore;
import static com.scrabble.backend.resolving.algorithm.solver.Solver.getWordsByLength;

@Service
public class ResolvingService {
    public List<Word> bestWords(GameStateDto gameState, String mode, int number) {
        String holder = preprocessHolder(gameState.getHolder());
        char[][] board = preprocessBoard(gameState.getBoard());

        return switch (mode) {
            case "length" -> getWordsByLength(board, holder, number);
            case "score", default -> getWordsByBestScore(board, holder, number);
        };
    }


    private char[][] preprocessBoard(char[][] board) {
        if (board.length != boardSize || board[0].length != boardSize)
            throw new InvalidParameterException("Invalid board size");

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] != Alphabet.getEmptySymbol()) {
                    board[i][j] = Character.toLowerCase(board[i][j]);
                    throwIfIncorrectLetter(board[i][j]);
                }
            }
        }
        return board;
    }

    public static String preprocessHolder(char[] holderArray) {
        if (holderArray.length != boardSize)
            throw new InvalidParameterException("Invalid holder size: " + holderArray.length);

        StringBuilder builder = new StringBuilder();
        for (char letter : holderArray) {
            if (letter != Alphabet.getEmptySymbol()) {
                char lowerCaseLetter = Character.toLowerCase(letter);

                throwIfIncorrectLetter(lowerCaseLetter);
                builder.append(lowerCaseLetter);
            }
        }
        return builder.toString();
    }

    public static void throwIfIncorrectLetter(char letter) {
        if (!(Alphabet.isCorrectLetter(letter) || Alphabet.isEmptySymbol(letter)))
            throw new InvalidParameterException("Invalid letter: " + letter);
    }

}

