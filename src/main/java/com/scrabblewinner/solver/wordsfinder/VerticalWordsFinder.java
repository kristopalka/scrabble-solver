package com.scrabblewinner.solver.wordsfinder;

import com.scrabblewinner.scrabble.board.components.Word;
import com.scrabblewinner.solver.wordsfinder.correctselector.CorrectWordsSelector;
import com.scrabblewinner.utility.Timer;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;


@Log4j2
public class VerticalWordsFinder {
    public static ArrayList<Word> getVertical(char[][] board, char[] holder, int size) {
        ArrayList<Word> words = new ArrayList<>();
        for (int colNum = 0; colNum < size; colNum++) {


            Timer potentialTimer = new Timer();
            ArrayList<String> potentialWords = InDictWordsFinder.getAll(board[colNum], holder);
            potentialTimer.stop();

            Timer correctTimer = new Timer();
            ArrayList<Word> correctWords = CorrectWordsSelector.select(board, colNum, potentialWords);
            correctTimer.stop();

            log.info(String.format("\t%d: %d strings in %fs | %d words in %fs\n",
                    colNum,
                    potentialWords.size(), potentialTimer.get(),
                    correctWords.size(), correctTimer.get()));

            words.addAll(correctWords);
        }
        return words;
    }


}
