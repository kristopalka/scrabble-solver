package com.scrabble.backend.algorithm.scrabble;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static com.scrabble.backend.resolving.algorithm.settings.DictionarySorted.*;

public class SortedDirectoryTest {

    @Test
    void getAllFromDictionaryTest() {
        Assertions.assertEquals(getWordsByRequiredLetters(sortString("krzysztof")), new ArrayList<>());
        Assertions.assertEquals(getWordsByRequiredLetters(sortString("pałka")), new ArrayList<>() {{
            add("kapał");
            add("łapak");
            add("łapka");
            add("pałka");
        }});
    }

    @Test
    void requiredLetters() {
       Assertions.assertEquals(2747782, getAllRequiredLettersList().size());
    }

}
