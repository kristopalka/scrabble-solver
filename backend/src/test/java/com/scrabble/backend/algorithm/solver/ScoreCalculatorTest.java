package com.scrabble.backend.algorithm.solver;

import com.scrabble.backend.resolving.algorithm.BoardBuilder;
import com.scrabble.backend.resolving.algorithm.Word;
import com.scrabble.backend.resolving.algorithm.solver.ScoreCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;


public class ScoreCalculatorTest {
    @Test
    public void getScoreForWordManyLettersTest() {
        Word word = new Word("aąbcćdeę", 0, 0, Word.Direction.HORIZONTAL);


        ScoreCalculator calculator = new ScoreCalculator(new BoardBuilder().toCharArray());
        int points = calculator.getScore(word);
        Assertions.assertEquals(points, 243);
    }

    @Test
    public void getScoreForWordTestVerticalHorizontal() {
        Word wordVert = new Word("aaaaaaaaaaaaaaa", 5, 0, Word.Direction.VERTICAL);
        Word wordHor = new Word("aaaaaaaaaaaaaaa", 0, 5, Word.Direction.HORIZONTAL);

        ScoreCalculator calculator = new ScoreCalculator(new BoardBuilder().toCharArray());
        int pointsVert = calculator.getScore(wordVert);
        int pointsHor = calculator.getScore(wordHor);
        int points = 23;
        Assertions.assertEquals(points, pointsVert);
        Assertions.assertEquals(points, pointsHor);
    }

    @Test
    public void scoreCalculationTest1() {
        BoardBuilder board = new BoardBuilder()
                .addWord(new Word("próbuje", 1, 0, Word.Direction.HORIZONTAL));
        Word newWord = new Word("spróbujemy", 0, 0, Word.Direction.HORIZONTAL);

        ScoreCalculator calculator = new ScoreCalculator(board.toCharArray());
        Assertions.assertEquals(69, calculator.getScore(newWord));
    }

    @Test
    public void scoreCalculationTest2() {
        BoardBuilder board = new BoardBuilder()
                .addWord(new Word("p", 3, 1, Word.Direction.VERTICAL));
        Word newWord = new Word("prymas", 3, 1, Word.Direction.HORIZONTAL);

        ScoreCalculator calculator = new ScoreCalculator(board.toCharArray());
        Assertions.assertEquals(13, calculator.getScore(newWord));
    }

    @Test
    public void scoreCalculationTest3() {
        BoardBuilder board = new BoardBuilder()
                .addWord(new Word("uje", 13, 2, Word.Direction.VERTICAL));
        Word newWord = new Word("dar", 14, 2, Word.Direction.VERTICAL);
        newWord.additionalWords = List.of(new Word[]{
                new Word("ud", 13, 2, Word.Direction.HORIZONTAL),
                new Word("ja", 13, 3, Word.Direction.HORIZONTAL),
                new Word("er", 13, 4, Word.Direction.HORIZONTAL),
        });
        System.out.println(board);

        ScoreCalculator calculator = new ScoreCalculator(board.toCharArray());
        Assertions.assertEquals(17, calculator.getScore(newWord));
    }

}
