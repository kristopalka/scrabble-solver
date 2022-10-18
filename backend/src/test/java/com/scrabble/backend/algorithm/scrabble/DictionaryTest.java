package com.scrabble.backend.algorithm.scrabble;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static com.scrabble.backend.solving.scrabble.ScrabbleResources.getDictionary;
import static com.scrabble.backend.solving.scrabble.resources.Dictionary.sortString;

public class DictionaryTest {

    @Test
    void sizesTest() {
        Assertions.assertEquals(3045853, getDictionary("pl").getWords().size());
        Assertions.assertEquals(178691, getDictionary("en").getWords().size());

        Assertions.assertEquals(2747782, getDictionary("pl").getAllRequiredLettersList().size());
        Assertions.assertEquals(161019, getDictionary("en").getAllRequiredLettersList().size());
    }


    @Test
    void findWordTest() {
        Assertions.assertTrue(getDictionary("pl").containsWord("kot"));
        Assertions.assertTrue(getDictionary("pl").containsWord("dupa"));
        Assertions.assertTrue(getDictionary("pl").containsWord("kubek"));

        Assertions.assertFalse(getDictionary("pl").containsWord("kindybał"));
        Assertions.assertFalse(getDictionary("pl").containsWord("sdfdsegrgerg"));
        Assertions.assertFalse(getDictionary("pl").containsWord("kindsdfsdfdfybał"));

        Assertions.assertTrue(getDictionary("en").containsWord("as"));
        Assertions.assertTrue(getDictionary("en").containsWord("bad"));
        Assertions.assertTrue(getDictionary("en").containsWord("any"));
    }

    @Test
    void getAllFromDictionaryTest() {
        Assertions.assertEquals(getDictionary("pl").getWordsByRequiredLetters(sortString("krzysztof")),
                new ArrayList<>());
        Assertions.assertEquals(getDictionary("pl").getWordsByRequiredLetters(sortString("pałka")),
                new ArrayList<>() {{
                    add("kapał");
                    add("łapak");
                    add("łapka");
                    add("pałka");
                }});
    }

}
