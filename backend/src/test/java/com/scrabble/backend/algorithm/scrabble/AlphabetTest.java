package com.scrabble.backend.algorithm.scrabble;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.scrabble.backend.solving.scrabble.Static.getAlphabet;

public class AlphabetTest {
    @Test
    public void valueOfLetterTest() {
        Assertions.assertEquals(2, getAlphabet("pl").valueOfLetter('l'));
        Assertions.assertEquals(10, getAlphabet("en").valueOfLetter('z'));
    }

    @Test
    public void isLetterTest() {
        Assertions.assertTrue(getAlphabet("pl").isLetter('l'));
        Assertions.assertTrue(getAlphabet("en").isLetter('l'));
        Assertions.assertFalse(getAlphabet("en").isLetter('ł'));
        Assertions.assertTrue(getAlphabet("en").isLetter('q'));
        Assertions.assertFalse(getAlphabet("pl").isLetter('3'));
        Assertions.assertFalse(getAlphabet("pl").isLetter('%'));
    }

    @Test
    public void isAllowedCharacterTest() {
        Assertions.assertTrue(getAlphabet("pl").isLetterOrEmptySymbol('g'));
        Assertions.assertTrue(getAlphabet("pl").isLetterOrEmptySymbol(' '));
        Assertions.assertFalse(getAlphabet("en").isLetterOrEmptySymbol('('));
    }

}
