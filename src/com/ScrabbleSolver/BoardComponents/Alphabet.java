package com.ScrabbleSolver.BoardComponents;

import java.util.HashMap;


public class Alphabet // singleton
{
    private static Alphabet instance;

    private static HashMap<Character, Integer> letters = new HashMap<>();
    private static char emptySymbol;

    private Alphabet()
    {
        String letters = "AĄBCĆDEĘFGHIJKLŁMNŃOÓPRSŚTUWYZŹŻ";
        String values = "15326215533132232171521152312195";

        for (int i=0; i<letters.length(); i++) this.letters.put(letters.charAt(i), values.charAt(i) - '0');
        emptySymbol = ' ';
    }

    private static void ifNoInstanceCreate()
    {
        if (instance == null) instance = new Alphabet();
    }


    public static char getEmptySymbol()
    {
        ifNoInstanceCreate();
        return emptySymbol;
    }

    public static boolean isEmptySymbol(char symbol)
    {
        ifNoInstanceCreate();
        return symbol == emptySymbol;
    }


    public static int valueOfLetter(char letter)
    {
        ifNoInstanceCreate();
        if (!isLetter(letter)) return 0;

        letter = toUpperCase(letter);
        return letters.get(letter);
    }

    public static boolean areEquals(char letter1, char letter2)
    {
        return toUpperCase(letter1) == toUpperCase(letter2);
    }

    public static boolean isLetter(char symbol)
    {
        ifNoInstanceCreate();
        return letters.keySet().contains(toUpperCase(symbol));
    }

    private static char toUpperCase(char letter)
    {
        return (""+letter).toUpperCase().toCharArray()[0];
    }

}
