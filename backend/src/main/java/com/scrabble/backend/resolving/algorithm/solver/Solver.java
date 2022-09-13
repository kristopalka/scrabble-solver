package com.scrabble.backend.resolving.algorithm.solver;

import com.scrabble.backend.resolving.algorithm.Word;

import java.util.ArrayList;
import java.util.List;

import static com.scrabble.backend.resolving.algorithm.settings.Alphabet.extractCorrectLetters;
import static com.scrabble.backend.resolving.algorithm.solver.PointsCalculator.calculatePoints;
import static com.scrabble.backend.resolving.algorithm.solver.wordsfinder.WordsFinder.getVerticalAndHorizontal;

public class Solver {
    public static List<Word> getBestWords(char[][] board, char[] holderArray, int number) {
        char[] holder = extractCorrectLetters(holderArray);

        toLowerCase(board);
        toLowerCase(holder);


        ArrayList<Word> possibleWords = getVerticalAndHorizontal(board, holder);

        return getNBestPointed(possibleWords, board, number);
    }

    public static Word getBestWord(char[][] board, char[] holder) {
        return getBestWords(board, holder, 1).get(0);
    }

    public static List<Word> getNBestPointed(ArrayList<Word> words, char[][] board, int number) {
        words.forEach(w -> w.setPoints(calculatePoints(w, board)));

        return words.stream()
                .sorted((w1, w2) -> Integer.compare(w2.getPoints(), w1.getPoints()))
                .limit(number)
                .toList();
    }

    private static void toLowerCase(char[][] array) {
        for (char[] chars : array) {
            toLowerCase(chars);
        }
    }

    private static void toLowerCase(char[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = Character.toLowerCase(array[i]);
        }
    }


}
