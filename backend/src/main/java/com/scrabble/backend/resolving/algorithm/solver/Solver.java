package com.scrabble.backend.resolving.algorithm.solver;

import com.scrabble.backend.resolving.algorithm.Word;
import com.scrabble.backend.resolving.algorithm.solver.wordsfinder.WordsFinder;

import java.util.List;

import static com.scrabble.backend.resolving.algorithm.settings.Alphabet.extractCorrectLetters;

public class Solver {
    public static List<Word> getBestWords(char[][] board, char[] holderArray, int number) {
        String holder = extractCorrectLetters(holderArray).toLowerCase();
        toLowerCase(board);

        List<Word> possibleWords = WordsFinder.getVerticalAndHorizontal(board, holder);
        return getNBestPointed(possibleWords, board, number);
    }

    public static Word getBestWord(char[][] board, char[] holderArray) {
        return getBestWords(board, holderArray, 1).get(0);
    }

    public static List<Word> getNBestPointed(List<Word> words, char[][] board, int number) {
        ScoreCalculator calculator = new ScoreCalculator(board);
        words.forEach(w -> w.score = calculator.getScore(w));

        return words.stream()
                .sorted((w1, w2) -> Integer.compare(w2.score, w1.score))
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
