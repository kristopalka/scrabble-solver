package com.scrabble.backend.algorithm.scrabble.holder;

import com.scrabble.backend.api.resolving.algorithm.scrabble.Word;
import com.scrabble.backend.api.resolving.algorithm.scrabble.holder.StandardHolder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class StandardHolderTest {
    @Test
    public void holderTest() {
        StandardHolder holder = new StandardHolder();

        holder.add('a').add('b').add('c').add('d').add('e').add('f').add('g');

        Assertions.assertEquals(holder.getLetters(),
                new ArrayList<>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g')));

        System.out.println(holder);

        try {
            holder.add('a');
            Assertions.fail();
        } catch (Exception ignored) {
        }

        holder.getWord(new Word("abcd", 0, 0, Word.Direction.VERTICAL));
        Assertions.assertEquals(holder.getLetters(),
                new ArrayList<>(Arrays.asList('e', 'f', 'g')));

        try {
            holder.add('x');
            Assertions.fail();
        } catch (Exception ignored) {
        }
    }

}
