package test.scrabblewinner.scrabble.dictionary;


import com.scrabblewinner.scrabble.dictionary.Dictionary;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class DictionaryTest {

    @Test
    void getAllFromDictionaryTest() {
        ArrayList<String> allWords = Dictionary.getAllWords();

        int size = allWords.size();

        Assertions.assertTrue(size == 3045853);
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
