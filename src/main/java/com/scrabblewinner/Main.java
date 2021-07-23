package com.scrabblewinner;

import com.scrabblewinner.scrabble.board.StandardBoard;
import com.scrabblewinner.scrabble.board.components.Word;
import com.scrabblewinner.scrabble.holder.StandardHolder;
import com.scrabblewinner.solver.wordsfinder.WordsFinder;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        StandardBoard board = new StandardBoard();
        StandardHolder holder = new StandardHolder();

        holder.addLetter('m')
                .addLetter('a')
                .addLetter('m')
                .addLetter('a');

        ArrayList<Word> words = WordsFinder.getAllPossibleWords(board, holder);

        words.forEach(System.out::println);
    }
}
