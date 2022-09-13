package com.scrabble.backend.algorithm.solver.wordsfinder;

import com.scrabble.backend.resolving.algorithm.BoardBuilder;
import com.scrabble.backend.resolving.algorithm.Word;
import com.scrabble.backend.resolving.algorithm.settings.Dictionary;
import com.scrabble.backend.resolving.algorithm.solver.wordsfinder.WordsFinder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WordsFinderTest {

    @Test
    public void getVerticalAndHorizontalTest() {
        BoardBuilder boardBuilder = new BoardBuilder();
        boardBuilder.addWord(new Word("ma",  4, 4, Word.Direction.HORIZONTAL));

        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        ArrayList<Word> words = WordsFinder.getVerticalAndHorizontal(boardBuilder.toCharArray(), "abcde");
        stopWatch.stop();
        System.out.println("Calculated in: " + stopWatch.getTotalTimeMillis() + " [ms]");

        List<String> expectedWords = Arrays.asList("am", "ma", "em", "me", "amb", "bam", "mac", "dam", "mad", "dem", "cabem", "maceb", "badem", "damce", "madce", "dbam", "ameb", "mace", "dame", "dema", "aa", "aa", "ba", "ad", "da", "bad", "dba", "cab", "abace", "abace", "baca", "baca", "caba", "caba", "bada", "bada", "bace", "maa", "mac", "mad", "maceb", "madce", "maceba", "dama", "mada", "maca", "mace", "dema");
        List<String> actualWords = words.stream().map(w -> w.value).toList();
        Assertions.assertEquals(48, words.size());
        Assertions.assertEquals(expectedWords, actualWords);
    }
}
