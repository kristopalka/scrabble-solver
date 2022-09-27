package com.scrabble.backend.api.dto;

import com.scrabble.backend.solving.scrabble.Static;
import com.scrabble.backend.solving.scrabble.resources.Alphabet;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class LettersValuesDto {
    private final List<String> letters;
    private final List<Integer> values;

    public LettersValuesDto(String lang) {
        Alphabet alphabet = Static.getAlphabet(lang);

        this.letters = new ArrayList<>();
        this.values = new ArrayList<>();
        for (Character letter: alphabet.getLetters()) {
            letters.add(String.valueOf(letter));
            values.add(alphabet.valueOfLetter(letter));
        }
    }
}
