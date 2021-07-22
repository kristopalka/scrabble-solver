package test.scrabblewinner.scrabble.dictionary;

import com.scrabblewinner.scrabble.dictionary.SortedDictionary;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class SortedDirectoryTest {

    @Test
    void getAllFromDictionaryTest() {
        Assertions.assertEquals(SortedDictionary.get(SortedDictionary.sortString("krzysztof")) , new ArrayList<>());
        Assertions.assertEquals(SortedDictionary.get(SortedDictionary.sortString("pałka")) , new ArrayList<>() {{
            add("kapał");
            add("łapak");
            add("łapka");
            add("pałka");
        }});
    }
}
