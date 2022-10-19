package com.scrabble.backend.algorithm.solver.wordsfinder;

import com.scrabble.backend.solving.solver.finder.DictionaryFinder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.scrabble.backend.solving.scrabble.ScrabbleResources.getDictionary;

public class DictionaryFinderTest extends DictionaryFinder {

    @Test
    void isSubsetOfTest() {
        char[] letters = "abcdefg".toCharArray();
        char[] word1 = "abcd".toCharArray();
        char[] word2 = "adg".toCharArray();
        char[] word3 = "a".toCharArray();
        char[] word4 = "chrtyzół".toCharArray();
        char[] word5 = "fsdfgsdfg".toCharArray();


        Assertions.assertTrue(DictionaryFinder.isSubsetOf(word1, letters));
        Assertions.assertTrue(DictionaryFinder.isSubsetOf(word2, letters));
        Assertions.assertTrue(DictionaryFinder.isSubsetOf(word3, letters));
        Assertions.assertFalse(DictionaryFinder.isSubsetOf(word4, letters));
        Assertions.assertFalse(DictionaryFinder.isSubsetOf(word5, letters));
    }

    @Test
    void getPotentialWordsForColumnTest() {
        String column = "rt";
        String rack = "abe";

        List<String> expected = new ArrayList<>(Arrays.asList("ba", "ar", "at", "ta", "be", "er", "re", "et", "te", "arb", "bar", "rab", "bat", "tab", "era", "rea", "ate", "eta", "rat", "tar", "tra", "ber", "erb", "reb", "bet", "ret", "ter", "berta", "bera", "rabe", "beat", "beta", "bart", "brat", "tera", "bert", "terb"))
                .stream().sorted().toList();
        List<String> actual = DictionaryFinder.getPotentialWords(column, rack, getDictionary("pl")).stream().sorted().toList();

        Assertions.assertEquals(expected, actual);

    }
}
