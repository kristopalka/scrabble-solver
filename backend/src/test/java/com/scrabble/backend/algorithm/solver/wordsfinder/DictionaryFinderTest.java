package com.scrabble.backend.algorithm.solver.wordsfinder;

import com.scrabble.backend.resolving.algorithm.solver.wordsfinder.correctselector.DictionaryFinder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class DictionaryFinderTest extends DictionaryFinder {

    @Test
    void isSubsetOfTest() {
        String letters = "abcdefg";
        String word1 = "abcd";
        String word2 = "adg";
        String word3 = "a";
        String word4 = "chrtyzół";
        String word5 = "fsdfgsdfg";


        Assertions.assertTrue(DictionaryFinder.isSubsetOf(word1, letters));
        Assertions.assertTrue(DictionaryFinder.isSubsetOf(word2, letters));
        Assertions.assertTrue(DictionaryFinder.isSubsetOf(word3, letters));
        Assertions.assertFalse(DictionaryFinder.isSubsetOf(word4, letters));
        Assertions.assertFalse(DictionaryFinder.isSubsetOf(word5, letters));
    }

    @Test
    void getPotentialWordsForColumnTest() {
        String column = "rt";
        String holder = "abe";

        ArrayList<String> correct = new ArrayList<>(Arrays.asList("ba", "ar", "at", "ta", "be", "er", "re", "et", "te", "arb", "bar", "rab", "bat", "tab", "era", "rea", "ate", "eta", "rat", "tar", "tra", "ber", "erb", "reb", "bet", "ret", "ter", "berta", "bera", "rabe", "beat", "beta", "bart", "brat", "tera", "bert", "terb"));
        Assertions.assertEquals(correct, DictionaryFinder.getPotentialWords(column, holder));

    }
}
