package com.scrabblewinner.scrabble.alphabet;

import java.security.InvalidParameterException;
import java.util.HashMap;


public class Alphabet // singleton
{
    private static Alphabet instance;
    private static HashMap<Character, Integer> letters = new HashMap<>();
    private static char emptySymbol;

    private static void ifNoInstanceCreate() {
        if (instance == null) instance = new Alphabet();
    }

    private Alphabet() {
        String letters = "aąbcćdeęfghijklłmnńoóprsśtuwyzźż";
        String values =  "15326215533132232171521152312195";

        for (int i = 0; i < letters.length(); i++) Alphabet.letters.put(letters.charAt(i), values.charAt(i) - '0');
        emptySymbol = ' ';
    }



    public static char getEmptySymbol() {
        ifNoInstanceCreate();
        return emptySymbol;
    }


    public static int valueOfLetter(char letter) {
        ifNoInstanceCreate();
        if (!isLetter(letter)) throw new InvalidParameterException("Parameter is not a letter");
        return letters.get(letter);
    }


    public static boolean isLetter(char symbol) {
        ifNoInstanceCreate();
        return letters.keySet().contains(symbol);
    }

    public static boolean isEmptySymbol(char symbol) {
        ifNoInstanceCreate();
        return symbol == emptySymbol;
    }

    public static boolean isAllowedCharacter(char symbol) {
        ifNoInstanceCreate();
        return isEmptySymbol(symbol) || isLetter(symbol);
    }


}
