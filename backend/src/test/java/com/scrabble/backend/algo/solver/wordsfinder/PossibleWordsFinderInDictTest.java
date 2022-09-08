package com.scrabble.backend.algo.solver.wordsfinder;

import com.scrabble.backend.algorithm.scrabble.Alphabet;
import com.scrabble.backend.algorithm.solver.wordsfinder.possibleselector.PossibleWordsFinderInDict;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class PossibleWordsFinderInDictTest extends PossibleWordsFinderInDict {
    @Test
    void getLettersToUseTest() {
        char e = Alphabet.getEmptySymbol();
        char[] column = {e, 'r', e, 't', 'g', e, 'y'};
        char[] holder = {'a', 'b', 'c', 'd'};

        char[] lettersToUse = PossibleWordsFinderInDict.getLettersToUse(column, holder);
        char[] correct = {'r', 't', 'g', 'y', 'a', 'b', 'c', 'd'};

        Object[] arr1 = {lettersToUse};
        Object[] arr2 = {correct};
        Assertions.assertTrue(Arrays.deepEquals(arr1, arr2));
    }

    @Test
    void isEnoughLettersForWord() {
        char[] letters = {'a', 'b', 'c', 'd', 'd', 'e', 'f', 'g'};
        String word1 = "abcdd";
        String word2 = "adg";
        String word3 = "a";
        String word4 = "chrtyzół";
        String word5 = "fsdfgsdfg";


        Assertions.assertTrue(PossibleWordsFinderInDict.isEnoughLettersToAlignWord(word1, letters));
        Assertions.assertTrue(PossibleWordsFinderInDict.isEnoughLettersToAlignWord(word2, letters));
        Assertions.assertTrue(PossibleWordsFinderInDict.isEnoughLettersToAlignWord(word3, letters));
        Assertions.assertFalse(PossibleWordsFinderInDict.isEnoughLettersToAlignWord(word4, letters));
        Assertions.assertFalse(PossibleWordsFinderInDict.isEnoughLettersToAlignWord(word5, letters));
    }

    @Test
    void getAllTest() {
        char e = Alphabet.getEmptySymbol();
        char[] column = {e, 'r', e, 't'};
        char[] holder = {'a', 'b', 'e'};

        ArrayList<String> correct = new ArrayList<>(Arrays.asList("ba", "ar", "at", "ta", "be", "er", "re", "et", "te", "arb", "bar", "rab", "bat", "tab", "era", "rea", "ate", "eta", "rat", "tar", "tra", "ber", "erb", "reb", "bet", "ret", "ter", "berta", "bera", "rabe", "beat", "beta", "bart", "brat", "tera", "bert", "terb"));
        Assertions.assertEquals(correct, PossibleWordsFinderInDict.getWordsPossibleToArrangeFromLetters(column, holder));
    }
}
