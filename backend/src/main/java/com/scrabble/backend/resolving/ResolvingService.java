package com.scrabble.backend.resolving;

import com.scrabble.backend.resolving.algorithm.solver.finder.Word;
import com.scrabble.backend.resolving.dto.RequestDto;
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
        getAlphabet("en");
        getDictionary("pl");
        getDictionary("en");
    }

    public List<Word> bestWords(RequestDto request) {
        Integer number = request.getNumber();
        String lang = request.getLang();
        String holder = request.getHolder();
        char[][] board = request.getBoard();

        return switch (request.getMode()) {
            case "length" -> getWordsByLength(board, holder, lang, number);
            case "score" -> getWordsByBestScore(board, holder, lang, number);
            default -> throw new IllegalStateException("Unexpected value: " + request.getMode());
        };
    }


}

