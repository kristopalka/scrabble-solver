package com.scrabble.backend.algorithm.scrabble.dictionary;

import com.scrabble.backend.resolving.algorithm.settings.DictionarySorted;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class SortedDirectoryTest {

    @Test
    void getAllFromDictionaryTest() {
        Assertions.assertEquals(DictionarySorted.getWordsByRequiredLetters(DictionarySorted.sortString("krzysztof")) , new ArrayList<>());
        Assertions.assertEquals(DictionarySorted.getWordsByRequiredLetters(DictionarySorted.sortString("pałka")) , new ArrayList<>() {{
            add("kapał");
            add("łapak");
            add("łapka");
            add("pałka");
        }});
    }
}
