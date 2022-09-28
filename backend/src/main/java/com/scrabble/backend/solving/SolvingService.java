package com.scrabble.backend.solving;

import com.scrabble.backend.api.dto.GameStateDto;
import com.scrabble.backend.solving.solver.finder.Word;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.scrabble.backend.solving.scrabble.Static.*;
import static com.scrabble.backend.solving.scrabble.resources.Alphabet.emptySymbol;
import static com.scrabble.backend.solving.solver.Solver.getWords;

@Service
public class SolvingService {
    public static final String[] modes = {"score", "length"};

    public SolvingService() {
        // init alphabet and dictionaries on startup
        getAlphabet("pl");
        getAlphabet("en");
        getDictionary("pl");
        getDictionary("en");
    }

    public List<Word> bestWords(GameStateDto request, String lang, String mode, Integer number) {
        throwIfIncorrectLang(lang);
        String holder = preprocessHolder(request.getHolder(), lang);
        char[][] board = preprocessBoard(request.getBoard(), lang);

        return getWords(board, holder, lang, number, mode);
    }

    public void throwIfIncorrectLang(String lang) {
        if (!supportedLanguages.contains(lang))
            throw new IllegalArgumentException("Language \"" + lang + "\" is not supported");
    }

    public static char[][] preprocessBoard(String[][] board, String lang) {
        if (board == null) throw new IllegalArgumentException("Board is mandatory");
        if (board.length != boardSize || board[0].length != boardSize)
            throw new IllegalArgumentException("Invalid board size");

        char[][] charBoard = new char[boardSize][boardSize];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                String lowerCase = board[i][j].length() == 0 ? String.valueOf(emptySymbol) : board[i][j].toLowerCase();

                throwIfIncorrectValue(lowerCase, lang);

                charBoard[i][j] = lowerCase.charAt(0);
            }
        }
        return charBoard;
    }

    public static String preprocessHolder(String[] holder, String lang) {
        if (holder == null) throw new IllegalArgumentException("Holder is mandatory");
        if (holder.length != holderSize)
            throw new IllegalArgumentException("Invalid holder size: " + holder.length);

        StringBuilder builder = new StringBuilder();
        for (String letter : holder) {
            String lowerCase = letter.length() == 0 ? String.valueOf(emptySymbol) : letter.toLowerCase();
            throwIfIncorrectValue(lowerCase, lang);
            builder.append(lowerCase.charAt(0));
        }
        return builder.toString();
    }

    public static void throwIfIncorrectValue(String symbol, String lang) {
        if (symbol.length() != 1)
            throw new IllegalArgumentException("Invalid symbol \"" + symbol + "\"");

        if (!getAlphabet(lang).isLetterOrEmptySymbol(symbol.charAt(0)))
            throw new IllegalArgumentException("Invalid letter: \"" + symbol + "\"");
    }


}

