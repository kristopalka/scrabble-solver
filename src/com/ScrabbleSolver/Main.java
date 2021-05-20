package com.ScrabbleSolver;

import com.ScrabbleSolver.Components.Dictionary;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> words = (ArrayList<String>) Dictionary.getAllWords();

        int[] tab = new int[17];

        for (String word : words) {
            tab[word.length()]++;
        }

        for(int i=0; i<17; i++) System.out.println("" + i + ": " + tab[i]);
    }
}
