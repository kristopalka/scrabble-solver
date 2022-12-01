package com.scrabble.backend;

import com.scrabble.backend.api.dto.InfoDto;
import com.scrabble.backend.api.dto.LettersValuesDto;
import com.scrabble.backend.solving.scrabble.ScrabbleResources;
import com.scrabble.backend.solving.scrabble.Alphabet;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class InfoConstructionIntegrationTest {
    @Test
    public void lettersValuesDtoConstructorTest() {
        LettersValuesDto dto = new LettersValuesDto("en");

        Alphabet alphabet = ScrabbleResources.getAlphabet("en");

        for (int i=0; i<alphabet.getLetters().size(); i++) {
            String letter = dto.getLetters().get(i);
            Integer value = dto.getValues().get(i);
            assertThat(alphabet.valueOfLetter(letter.charAt(0))).isEqualTo(value);
        }
    }

    @Test
    public void infoLettersValuesTest() {
        InfoDto dto = new InfoDto();

        int pl = dto.getLangs().indexOf("pl");
        int en = dto.getLangs().indexOf("en");
        assertThat(dto.getLettersValues().get(pl).getLetters()).contains("ą");
        assertThat(dto.getLettersValues().get(en).getLetters()).doesNotContain("ą");
    }
}
