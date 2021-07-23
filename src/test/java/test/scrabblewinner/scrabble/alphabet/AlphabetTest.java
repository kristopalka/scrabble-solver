package test.scrabblewinner.scrabble.alphabet;

import com.scrabblewinner.scrabble.alphabet.Alphabet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AlphabetTest {
    @Test
    public void getEmptySymbolTest() {
        Assertions.assertEquals(Alphabet.getEmptySymbol(), ' ');
    }

    @Test
    public void valueOfLetterTest() {
        Assertions.assertEquals(Alphabet.valueOfLetter('L'), 2);
    }

    @Test
    public void isLetterTest() {
        Assertions.assertTrue(Alphabet.isLetter('l'));
        Assertions.assertFalse(Alphabet.isLetter('3'));
        Assertions.assertFalse(Alphabet.isLetter('%'));
    }

    @Test
    public void isAllowedCharacterTest() {
        Assertions.assertTrue(Alphabet.isAllowedCharacter('g'));
        Assertions.assertTrue(Alphabet.isAllowedCharacter(' '));
        Assertions.assertFalse(Alphabet.isAllowedCharacter('('));
    }
}
