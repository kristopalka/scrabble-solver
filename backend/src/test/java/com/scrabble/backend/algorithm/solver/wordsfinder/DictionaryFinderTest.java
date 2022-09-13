package com.scrabble.backend.algorithm.solver.wordsfinder;

import com.scrabble.backend.resolving.algorithm.solver.wordsfinder.DictionaryFinder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

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
        String holder = "abe";

        ArrayList<String> correct = new ArrayList<>(Arrays.asList("ba", "ar", "at", "ta", "be", "er", "re", "et", "te", "arb", "bar", "rab", "bat", "tab", "era", "rea", "ate", "eta", "rat", "tar", "tra", "ber", "erb", "reb", "bet", "ret", "ter", "berta", "bera", "rabe", "beat", "beta", "bart", "brat", "tera", "bert", "terb"));
        Assertions.assertEquals(correct.stream().sorted().toList(), DictionaryFinder.getPotentialWords(column, holder).stream().sorted().toList());

    }
}
