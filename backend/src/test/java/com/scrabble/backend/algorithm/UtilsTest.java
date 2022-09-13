package com.scrabble.backend.algorithm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.scrabble.backend.resolving.algorithm.Utils.mergeArrays;

public class UtilsTest {

    @Test
    public void mergingArraysTest() {
        char[] a = {'b', 'd', 'z'};
        char[] b = {'f', 'o', 'p', 'f'};

        char[] merged = mergeArrays(a, b);

        Assertions.assertEquals(7, merged.length);
        Assertions.assertArrayEquals(new char[] {'b', 'd', 'z', 'f', 'o', 'p', 'f'}, merged);
    }
}
