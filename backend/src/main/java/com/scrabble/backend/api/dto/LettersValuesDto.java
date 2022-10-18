package com.scrabble.backend.api.dto;

import com.scrabble.backend.solving.scrabble.ScrabbleResources;
import com.scrabble.backend.solving.scrabble.resources.Alphabet;
import lombok.Data;
import org.eclipse.collections.impl.list.mutable.FastList;

import java.util.List;

@Data
public class LettersValuesDto {
    private final List<String> letters;
    private final List<Integer> values;

    public LettersValuesDto(String lang) {
        Alphabet alphabet = ScrabbleResources.getAlphabet(lang);

        this.letters = new FastList<>();
        this.values = new FastList<>();
        for (Character letter : alphabet.getLetters()) {
            letters.add(String.valueOf(letter));
            values.add(alphabet.valueOfLetter(letter));
        }
    }
}
