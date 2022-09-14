package com.scrabble.backend.algorithm.solver.wordsfinder;

import com.scrabble.backend.resolving.algorithm.BoardBuilder;
import com.scrabble.backend.resolving.algorithm.Word;
import com.scrabble.backend.resolving.algorithm.settings.Dictionary;
import com.scrabble.backend.resolving.algorithm.solver.ScoreCalculator;
import com.scrabble.backend.resolving.algorithm.solver.wordsfinder.WordsFinder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.StopWatch;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.scrabble.backend.resolving.algorithm.solver.wordsfinder.WordsFinder.getVerticalAndHorizontal;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;

public class WordsFinderTest {

    @Test
    public void getVerticalAndHorizontalTest() {
        BoardBuilder boardBuilder = new BoardBuilder();
        boardBuilder.addWord(new Word("ma",  4, 4, Word.Direction.HORIZONTAL));

        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        List<Word> words = getVerticalAndHorizontal(boardBuilder.toCharArray(), "abcde");
        stopWatch.stop();
        System.out.println("Calculated in: " + stopWatch.getTotalTimeMillis() + " [ms]");

        List<String> expectedWords = Arrays.asList("am", "ma", "em", "me", "amb", "bam", "mac", "dam", "mad", "dem", "cabem", "maceb", "badem", "damce", "madce", "dbam", "ameb", "mace", "dame", "dema", "aa", "aa", "ba", "ad", "da", "bad", "dba", "cab", "abace", "abace", "baca", "baca", "caba", "caba", "bada", "bada", "bace", "maa", "mac", "mad", "maceb", "madce", "maceba", "dama", "mada", "maca", "mace", "dema");
        List<String> actualWords = words.stream().map(w -> w.value).toList();
        Assertions.assertEquals(48, words.size());
        Assertions.assertEquals(expectedWords.stream().sorted().toList(), actualWords.stream().sorted().toList());
    }


    @Test
    public void test_1() {
        BoardBuilder boardBuilder = new BoardBuilder()
                .addWord(new Word("próbuje", 1, 0, Word.Direction.HORIZONTAL));

        Word foundWord = getVerticalAndHorizontal(boardBuilder.toCharArray(), "smy")
                .stream().filter(w -> Objects.equals(w.value, "spróbujemy")).toList().get(0);

        Assertions.assertEquals("spróbujemy", foundWord.value);
        Assertions.assertEquals(Word.Direction.HORIZONTAL, foundWord.direction);
        Assertions.assertEquals(new Point(0, 0), foundWord.begin);
        Assertions.assertEquals(new Point(1, 0), foundWord.entryBegin);
        Assertions.assertEquals(7, foundWord.entryLength);
    }

    @Test
    public void test_2() {
        BoardBuilder boardBuilder = new BoardBuilder()
                .addWord(new Word("pa", 3, 1, Word.Direction.VERTICAL));
        Word newWord = new Word("prymas", 3, 1, Word.Direction.HORIZONTAL);

        Word foundWord = getVerticalAndHorizontal(boardBuilder.toCharArray(), "rymas")
                .stream().filter(w -> Objects.equals(w.value, "prymas")).toList().get(0);

        Assertions.assertEquals("prymas", foundWord.value);
        Assertions.assertEquals(Word.Direction.HORIZONTAL, foundWord.direction);
        Assertions.assertEquals(new Point(3, 1), foundWord.begin);
        Assertions.assertEquals(new Point(3, 1), foundWord.entryBegin);
        Assertions.assertEquals(1, foundWord.entryLength);
    }

    @Test
    public void test_3() {
        BoardBuilder boardBuilder = new BoardBuilder()
                .addWord(new Word("podaruje", 2, 2, Word.Direction.VERTICAL))
                .addWord(new Word("y", 3, 10, Word.Direction.VERTICAL));
        System.out.println(boardBuilder);

        Word foundWord = getVerticalAndHorizontal(boardBuilder.toCharArray(), "dar")
                .stream().filter(w -> Objects.equals(w.value, "dary")).toList().get(0);

        Assertions.assertEquals("dary", foundWord.value);
        Assertions.assertEquals(Word.Direction.VERTICAL, foundWord.direction);
        Assertions.assertEquals(new Point(3, 7), foundWord.begin);
        Assertions.assertEquals(new Point(3, 10), foundWord.entryBegin);
        Assertions.assertEquals(1, foundWord.entryLength);

        List<Word> expected = List.of(new Word[]{
                new Word("ud", 2, 7, Word.Direction.HORIZONTAL),
                new Word("ja", 2, 8, Word.Direction.HORIZONTAL),
                new Word("er", 2, 9, Word.Direction.HORIZONTAL),
        });
        Assertions.assertEquals(expected, foundWord.additionalWords);
    }
}
