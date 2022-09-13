package com.scrabble.backend.resolving.algorithm.settings;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Random;


public class Alphabet {
    private static final HashMap<Character, Integer> values = new HashMap<>();
    private static final char emptySymbol;

    static {
        System.out.println("Processing alphabet...");

        String letters = "aąbcćdeęfghijklłmnńoóprsśtuwyzźż";
        String values =  "15326215533132232171521152312195";

        for (int i = 0; i < letters.length(); i++) Alphabet.values.put(letters.charAt(i), values.charAt(i) - '0');
        emptySymbol = ' ';
    }



    public static char getEmptySymbol() {
        return emptySymbol;
    }


    public static int valueOfLetter(char letter) {
        if (!isCorrectLetter(letter)) throw new InvalidParameterException("Parameter is not a letter: " + letter);
        return values.get(letter);
    }


    public static boolean isCorrectLetter(char symbol) {
        return values.containsKey(symbol);
    }

    public static boolean isEmptySymbol(char symbol) {
        return symbol == emptySymbol;
    }

    public static boolean isAllowedCharacter(char symbol) {
        return isEmptySymbol(symbol) || isCorrectLetter(symbol);
    }

    public static char getRandomLetter() {
        Character[] letters = Alphabet.values.keySet().toArray(new Character[0]);
        int random = new Random().nextInt(letters.length);
        return letters[random];
    }


    public static String extractCorrectLetters(char[] array){
        StringBuilder builder = new StringBuilder();
        for (char element : array) {
            if (element != Alphabet.getEmptySymbol()) builder.append(element);
        }
        return builder.toString();
    }
}
