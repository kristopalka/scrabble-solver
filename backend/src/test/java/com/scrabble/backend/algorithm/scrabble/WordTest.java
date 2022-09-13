package com.scrabble.backend.algorithm.scrabble;

import com.scrabble.backend.resolving.algorithm.Word;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.scrabble.backend.resolving.algorithm.solver.PointsCalculator.calculatePoints;

public class WordTest {
    @Test
    public void getPointsForWordManyLettersTest() {
        Word word = new Word("aąbcćdeę",  0, 0, Word.Direction.HORIZONTAL);

        int points = calculatePoints(word, null);
        Assertions.assertEquals(points, 243);
    }

    @Test
    public void getPointsForWordTestVerticalHorizontal() {
        Word wordVert = new Word("aaaaaaaaaaaaaaa",  5, 0, Word.Direction.VERTICAL);
        Word wordHor = new Word("aaaaaaaaaaaaaaa",  0, 5, Word.Direction.HORIZONTAL);

        int pointsVert = calculatePoints(wordVert, null);
        int pointsHor = calculatePoints(wordHor, null);
        int points = 23;
        Assertions.assertEquals(points, pointsVert);
        Assertions.assertEquals(points, pointsHor);
    }
}
