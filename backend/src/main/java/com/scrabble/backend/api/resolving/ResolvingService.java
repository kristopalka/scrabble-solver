package com.scrabble.backend.api.resolving;

import com.scrabble.backend.api.resolving.algorithm.scrabble.Word;
import com.scrabble.backend.api.resolving.algorithm.scrabble.board.Board;
import com.scrabble.backend.api.resolving.algorithm.scrabble.board.StandardBoard;
import com.scrabble.backend.api.resolving.dto.WordDto;
import org.springframework.stereotype.Service;

@Service
public class ResolvingService {
    public Word bestWord(char[][] board) {
        Board boardObj = new StandardBoard();
        return null;
    }


}

