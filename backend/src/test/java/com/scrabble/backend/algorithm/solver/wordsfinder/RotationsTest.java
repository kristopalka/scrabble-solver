package com.scrabble.backend.algorithm.solver.wordsfinder;

import com.scrabble.backend.resolving.algorithm.scrabble.BoardBuilder;
import com.scrabble.backend.resolving.algorithm.solver.finder.Word;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static com.scrabble.backend.resolving.algorithm.solver.finder.Rotations.rotateVerticalToHorizontal;
import static com.scrabble.backend.resolving.algorithm.solver.finder.Rotations.transpose;

public class RotationsTest {

    @Test
    public void transposeTest() {
        char[][] board = new BoardBuilder().addWord(new Word("abcdef", 0, 0, Word.Direction.HORIZONTAL)).toCharArray();
        char[][] transposed = transpose(board);

        Assertions.assertEquals('a', transposed[0][0]);
        Assertions.assertEquals('d', transposed[0][3]);
        Assertions.assertEquals('f', transposed[0][5]);
    }

    @Test
    public void rotateWordsTest() {
        List<Word> words = new ArrayList<>();
        words.add(new Word("daruj", new Point(5, 0), Word.Direction.VERTICAL, new Point(5, 4), 2));
        words.get(0).additionalWords = List.of(new Word[]{
                new Word("uda", 4, 0, Word.Direction.HORIZONTAL),
        });

        Word rotated = rotateVerticalToHorizontal(words).get(0);

        Assertions.assertEquals("daruj", rotated.value);
        Assertions.assertEquals(new Point(0, 5), rotated.begin);
        Assertions.assertEquals(new Point(4, 5), rotated.entryBegin);
        Assertions.assertEquals(2, rotated.entryLength);
        Assertions.assertEquals(Word.Direction.HORIZONTAL, rotated.direction);

        Word rotatedAdditional = rotated.additionalWords.get(0);
        Assertions.assertEquals("uda", rotatedAdditional.value);
        Assertions.assertEquals(new Point(0, 4), rotatedAdditional.begin);
        Assertions.assertEquals(Word.Direction.VERTICAL, rotatedAdditional.direction);
    }
}
