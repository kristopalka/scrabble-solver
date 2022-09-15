package com.scrabble.backend.algorithm;

import com.scrabble.backend.resolving.algorithm.scrabble.BoardBuilder;
import com.scrabble.backend.resolving.algorithm.solver.Solver;
import com.scrabble.backend.resolving.algorithm.solver.finder.Word;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.StopWatch;

import java.util.List;
import java.util.Random;

import static com.scrabble.backend.resolving.algorithm.scrabble.Static.getAlphabet;
import static com.scrabble.backend.resolving.algorithm.scrabble.Static.holderSize;

public class FillBoardSimulationTest {
    private BoardBuilder boardBuilder;
    private long totalTime = 0;

    @BeforeEach
    void prepare() {
        boardBuilder = new BoardBuilder();
        boardBuilder.addWord(new Word("a", 7, 7, Word.Direction.VERTICAL));
    }

    @Test
    void fillBoardWithNWords() {
        int moves = 10;
        run(moves);

        System.out.println("Average time: " + (totalTime / moves));
        System.out.println(boardBuilder);
    }


    private void run(int numberOfMoves) {
        for (int i = 0; i < numberOfMoves; i++) {
            String holder = getRandomHolder();

            final StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            List<Word> bestWords = Solver.getWordsByBestScore(boardBuilder.toCharArray(), holder, "pl", 1);
            stopWatch.stop();

            if (bestWords.size() == 0) {
                System.out.println("Cannot find any word. Holder: " + holder + "\n" + boardBuilder);
                return;
            }

            System.out.printf("word %s in %s ms\n", bestWords, stopWatch.getTotalTimeMillis());
            totalTime += stopWatch.getTotalTimeMillis();

            boardBuilder.addWord(bestWords.get(0));
        }
    }

    private String getRandomHolder() {
        List<Character> letters = getAlphabet("pl").getLetters();

        char[] holder = new char[holderSize];
        for (int i = 0; i < holder.length; i++) {
            holder[i] = letters.get(new Random().nextInt(letters.size()));
        }
        return new String(holder);
    }
}
