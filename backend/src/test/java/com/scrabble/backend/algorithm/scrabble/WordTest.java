package com.scrabble.backend.algorithm.scrabble;

import com.scrabble.backend.resolving.algorithm.Word;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;


public class WordTest {
    @Test
    public void endTest() {
        Word wordVert = new Word("abcdefg", 5, 1, Word.Direction.VERTICAL);
        Assertions.assertEquals(5, wordVert.xEnd());
        Assertions.assertEquals(7, wordVert.yEnd());

        Word wordHor = new Word("abcdefg", 5, 5, Word.Direction.HORIZONTAL);
        Assertions.assertEquals(11, wordHor.xEnd());
        Assertions.assertEquals(5, wordHor.yEnd());
    }

    @Test
    public void pointAtTest() {
        Word wordVert = new Word("abcdefg", 5, 1, Word.Direction.VERTICAL);
        Assertions.assertEquals(new Point(5, 5), wordVert.pointAt(4));

        Word wordHor = new Word("abcdefg", 4, 3, Word.Direction.HORIZONTAL);
        Assertions.assertEquals(new Point(8, 3), wordHor.pointAt(4));
    }
}
