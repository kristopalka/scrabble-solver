package com.scrabble.backend.algorithm.scrabble;


import com.scrabble.backend.resolving.algorithm.settings.Dictionary;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class DictionaryTest {

    @Test
    void getAllFromDictionaryTest() {
        ArrayList<String> allWords = Dictionary.getDictionary();

        int size = allWords.size();

        Assertions.assertEquals(3045853, size);
    }


    @Test
    void findWordTest() {
        Assertions.assertTrue(Dictionary.containsWord("kot"));
        Assertions.assertTrue(Dictionary.containsWord("dupa"));
        Assertions.assertTrue(Dictionary.containsWord("kubek"));

        Assertions.assertFalse(Dictionary.containsWord("kindybał"));
        Assertions.assertFalse(Dictionary.containsWord("sdfdsegrgerg"));
        Assertions.assertFalse(Dictionary.containsWord("kindsdfsdfdfybał"));
    }
}
