package com.scrabble.backend.algorithm.scrabble;

import com.scrabble.backend.resolving.algorithm.settings.Alphabet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AlphabetTest {
    @Test
    public void getEmptySymbolTest() {
        Assertions.assertEquals(Alphabet.getEmptySymbol(), ' ');
    }

    @Test
    public void valueOfLetterTest() {
        Assertions.assertEquals(Alphabet.valueOfLetter('l'), 2);
    }

    @Test
    public void isLetterTest() {
        Assertions.assertTrue(Alphabet.isCorrectLetter('l'));
        Assertions.assertFalse(Alphabet.isCorrectLetter('3'));
        Assertions.assertFalse(Alphabet.isCorrectLetter('%'));
    }

    @Test
    public void isAllowedCharacterTest() {
        Assertions.assertTrue(Alphabet.isAllowedCharacter('g'));
        Assertions.assertTrue(Alphabet.isAllowedCharacter(' '));
        Assertions.assertFalse(Alphabet.isAllowedCharacter('('));
    }

    @Test
    public void getRandomLetterVisualTest() {
        System.out.println(Alphabet.getRandomLetter());
    }


}
