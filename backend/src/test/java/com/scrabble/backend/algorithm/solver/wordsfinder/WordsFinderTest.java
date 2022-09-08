package com.scrabble.backend.algorithm.solver.wordsfinder;

import com.scrabble.backend.api.resolving.algorithm.scrabble.board.StandardBoard;
import com.scrabble.backend.api.resolving.algorithm.scrabble.Word;
import com.scrabble.backend.api.resolving.algorithm.scrabble.holder.StandardHolder;
import com.scrabble.backend.api.resolving.algorithm.solver.wordsfinder.WordsFinder;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class WordsFinderTest {

    @Test
    public void getAllPossibleWordsVisualTest()  {
        StandardBoard board = new StandardBoard();
        board.addWord(new Word("mama", 4, 4, Word.Direction.HORIZONTAL));


        StandardHolder holder = new StandardHolder();
        holder.add('a').add('b').add('c').add('d').add('e');

        ArrayList<Word> all = WordsFinder.getAllPossibleWords(board, holder);

        for (Word word : all) {
            StandardBoard newBoard = new StandardBoard();
            newBoard.addWord(new Word("mama", 4, 4, Word.Direction.HORIZONTAL));
            newBoard.addWord(word);

            System.out.println(newBoard);
        }
    }
}
