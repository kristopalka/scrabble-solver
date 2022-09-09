package com.scrabble.backend.resolving;

import com.scrabble.backend.resolving.algorithm.Word;
import com.scrabble.backend.resolving.algorithm.settings.ScrabbleSettings;
import com.scrabble.backend.resolving.dto.ResolveRequestDto;
import org.springframework.stereotype.Service;

import static com.scrabble.backend.resolving.algorithm.solver.Solver.getBestWord;

@Service
public class ResolvingService {
    public Word bestWord(ResolveRequestDto gameState) {
        validateGameState(gameState);
        return getBestWord(gameState.getBoard(), gameState.getHolder());
    }

    private void validateGameState(ResolveRequestDto gameState) {
        assert gameState.getBoard().length == ScrabbleSettings.getBoardSize();
        assert gameState.getBoard()[0].length == ScrabbleSettings.getBoardSize();
        assert gameState.getHolder().length <= ScrabbleSettings.getHolderSize();
    }


}

