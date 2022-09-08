package com.scrabble.backend.algo.scrabble.dictionary;

import com.scrabble.backend.algorithm.scrabble.dictionary.SortedDictionary;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class SortedDirectoryTest {

    @Test
    void getAllFromDictionaryTest() {
        Assertions.assertEquals(SortedDictionary.getWordsByKey(SortedDictionary.sortString("krzysztof")) , new ArrayList<>());
        Assertions.assertEquals(SortedDictionary.getWordsByKey(SortedDictionary.sortString("pałka")) , new ArrayList<>() {{
            add("kapał");
            add("łapak");
            add("łapka");
            add("pałka");
        }});
    }
}
