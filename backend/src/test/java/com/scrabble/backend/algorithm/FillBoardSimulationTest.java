package com.scrabble.backend.algorithm;

import com.scrabble.backend.solving.scrabble.BoardBuilder;
import com.scrabble.backend.solving.scrabble.ScrabbleResources;
import com.scrabble.backend.solving.solver.Solver;
import com.scrabble.backend.solving.solver.finder.Word;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import java.util.List;
import java.util.Random;

import static com.scrabble.backend.solving.scrabble.ScrabbleResources.getAlphabet;
import static com.scrabble.backend.solving.scrabble.ScrabbleResources.holderSize;

@SpringBootTest
public class FillBoardSimulationTest {
    private final BoardBuilder boardBuilder = new BoardBuilder();
    private long totalTime = 0;

    @Value("${config.scrabble_resources}")
    String scrabbleResourcesPath;


    @Test
    void fillBoardWithNWords() {
        ScrabbleResources.path = scrabbleResourcesPath;
        int movesCounter = runMoves(100);

        System.out.printf("Calculated %d moves in %d [ms] average time", movesCounter, (totalTime / movesCounter));
        System.out.println(boardBuilder);
    }


    private int runMoves(int numberOfMoves) {
        for (int movesCounter = 0; movesCounter < numberOfMoves; movesCounter++) {
            String holder = getRandomHolder();

            final StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            List<Word> bestWords = Solver.getWords(boardBuilder.toCharArray(), holder, "pl", 1, "score");
            stopWatch.stop();

            if (bestWords.size() == 0) {
                System.out.println("Cannot find any word. Holder: " + holder);
                return movesCounter;
            }

            System.out.printf("%s [ms] for %s\n", bestWords, stopWatch.getTotalTimeMillis());
            totalTime += stopWatch.getTotalTimeMillis();

            boardBuilder.addWord(bestWords.get(0));
        }
        return numberOfMoves;
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
