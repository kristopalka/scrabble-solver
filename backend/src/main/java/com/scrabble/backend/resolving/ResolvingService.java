package com.scrabble.backend.resolving;

import com.scrabble.backend.resolving.algorithm.Word;
import com.scrabble.backend.resolving.algorithm.settings.ScrabbleSettings;
import com.scrabble.backend.resolving.dto.GameStateDto;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import static com.scrabble.backend.resolving.algorithm.solver.Solver.getBestWords;

@Service
public class ResolvingService {
    private static final int numberOfWords = 5;

    public List<Word> bestWords(GameStateDto gameState) {
        validateGameState(gameState);
        return getBestWords(gameState.getBoard(), gameState.getHolder(), numberOfWords);
    }

    private void validateGameState(GameStateDto gameState) {
        assert gameState.getBoard().length == ScrabbleSettings.getBoardSize();
        assert gameState.getBoard()[0].length == ScrabbleSettings.getBoardSize();
        assert gameState.getHolder().length <= ScrabbleSettings.getHolderSize();
    }


}

