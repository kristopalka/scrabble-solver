package com.scrabble.backend.algorithm.solver.wordsfinder;

import com.scrabble.backend.solving.scrabble.BoardBuilder;
import com.scrabble.backend.solving.solver.finder.Word;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.StopWatch;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.scrabble.backend.solving.solver.finder.BoardFinder.getAll;


public class BoardFinderTest {

    @Test
    public void getAllTest() {
        BoardBuilder boardBuilder = new BoardBuilder();
        boardBuilder.addWord(new Word("ma", 4, 4, Word.Direction.HORIZONTAL));

        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        List<Word> words = getAll(boardBuilder.toCharArray(), "abcde", "pl");
        stopWatch.stop();
        System.out.println("Calculated in: " + stopWatch.getTotalTimeMillis() + " [ms]");

        List<String> expectedWords = Arrays.asList("am", "ma", "em", "me", "amb", "bam", "mac", "dam", "mad", "dem", "cabem", "maceb", "badem", "damce", "madce", "dbam", "ameb", "mace", "dame", "dema", "aa", "aa", "ba", "ad", "da", "bad", "dba", "cab", "abace", "abace", "baca", "baca", "caba", "caba", "bada", "bada", "bace", "maa", "mac", "mad", "maceb", "madce", "maceba", "dama", "mada", "maca", "mace", "dema");
        List<String> actualWords = words.stream().map(w -> w.value).toList();
        Assertions.assertEquals(48, words.size());
        Assertions.assertEquals(expectedWords.stream().sorted().toList(), actualWords.stream().sorted().toList());
    }


    @Test
    public void addingAtTheBeginningAndEndTest() {
        BoardBuilder boardBuilder = new BoardBuilder()
                .addWord(new Word("próbuje", 1, 0, Word.Direction.HORIZONTAL));

        Word foundWord = getAll(boardBuilder.toCharArray(), "smy", "pl")
                .stream().filter(w -> Objects.equals(w.value, "spróbujemy")).toList().get(0);

        Assertions.assertEquals("spróbujemy", foundWord.value);
        Assertions.assertEquals(Word.Direction.HORIZONTAL, foundWord.direction);
        Assertions.assertEquals(new Point(0, 0), foundWord.begin);
        Assertions.assertEquals(new Point(1, 0), foundWord.entryBegin);
        Assertions.assertEquals(7, foundWord.entryLength);
    }

    @Test
    public void addingPerpendicularlyTest() {
        BoardBuilder boardBuilder = new BoardBuilder()
                .addWord(new Word("pa", 3, 1, Word.Direction.VERTICAL));

        Word foundWord = getAll(boardBuilder.toCharArray(), "rymas", "pl")
                .stream().filter(w -> Objects.equals(w.value, "prymas")).toList().get(0);

        Assertions.assertEquals("prymas", foundWord.value);
        Assertions.assertEquals(Word.Direction.HORIZONTAL, foundWord.direction);
        Assertions.assertEquals(new Point(3, 1), foundWord.begin);
        Assertions.assertEquals(new Point(3, 1), foundWord.entryBegin);
        Assertions.assertEquals(1, foundWord.entryLength);
    }

    @Test
    public void parallelWordFindTest() {
        BoardBuilder boardBuilder = new BoardBuilder();
        boardBuilder.addWord(new Word("mama", 3, 0, Word.Direction.VERTICAL));
        System.out.println(boardBuilder);

        Word foundWord = getAll(boardBuilder.toCharArray(), "amadyn", "pl").stream()
                .filter(w -> Objects.equals(w.value, "amadyn"))
                .filter(w -> Objects.equals(w.direction, Word.Direction.HORIZONTAL))
                .filter(w -> Objects.equals(w.begin, new Point(2, 2)))
                .toList().get(1);

        System.out.println(boardBuilder.addWord(foundWord));


        Assertions.assertEquals(2, foundWord.additionalWords.size());
    }

    @Test
    public void bridgeWordFindTest() {
        BoardBuilder boardBuilder = new BoardBuilder();
        boardBuilder.addWord(new Word("d", 2, 2, Word.Direction.HORIZONTAL));
        boardBuilder.addWord(new Word("o", 9, 2, Word.Direction.HORIZONTAL));
        System.out.println(boardBuilder);

        Word foundWord = getAll(boardBuilder.toCharArray(), "laczeg", "pl")
                .stream().filter(w -> Objects.equals(w.value, "dlaczego")).toList().get(1);

        System.out.println(boardBuilder.addWord(foundWord));


        Assertions.assertEquals(new Point(2, 2), foundWord.begin);
        Assertions.assertEquals(Word.Direction.HORIZONTAL, foundWord.direction);
    }

    @Test
    public void additionalWordsTest() {
        BoardBuilder boardBuilder = new BoardBuilder()
                .addWord(new Word("podaruje", 2, 2, Word.Direction.VERTICAL))
                .addWord(new Word("y", 3, 10, Word.Direction.VERTICAL));
        System.out.println(boardBuilder);

        Word foundWord = getAll(boardBuilder.toCharArray(), "dar", "pl")
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

    @Test
    public void additionalWordsTest2() {
        BoardBuilder boardBuilder = new BoardBuilder()
                .addWord(new Word("każ", 2, 1, Word.Direction.VERTICAL))
                .addWord(new Word("hałd", 1, 2, Word.Direction.HORIZONTAL))
                .addWord(new Word("ce", 3, 4, Word.Direction.VERTICAL));
        System.out.println(boardBuilder);

        List<Word> foundWord = getAll(boardBuilder.toCharArray(), "mą", "pl")
                .stream().filter(w -> Objects.equals(w.value, "mżą")).toList();

        System.out.println(foundWord);
        Assertions.assertEquals(2, foundWord.get(0).additionalWords.size());
    }
}
