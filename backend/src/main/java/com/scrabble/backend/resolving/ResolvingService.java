package com.scrabble.backend.resolving;

import com.scrabble.backend.resolving.algorithm.solver.finder.Word;
import com.scrabble.backend.resolving.dto.GameStateDto;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.scrabble.backend.resolving.algorithm.scrabble.Static.getAlphabet;
import static com.scrabble.backend.resolving.algorithm.scrabble.Static.getDictionary;
import static com.scrabble.backend.resolving.algorithm.solver.Solver.getWordsByBestScore;
import static com.scrabble.backend.resolving.algorithm.solver.Solver.getWordsByLength;

@Service
public class ResolvingService {
    public ResolvingService() {
        getAlphabet("pl");
        getDictionary("pl");
    }

    public List<Word> bestWords(GameStateDto gameState, String mode, int number) {
        String lang = gameState.getLang();
        String holder = gameState.preprocessHolder();
        char[][] board = gameState.preprocessBoard();

        return switch (mode) {
            case "length" -> getWordsByLength(board, holder, lang, number);
            case "score", default -> getWordsByBestScore(board, holder, lang, number);
        };
    }


}

