package com.scrabblewinner.solver.finder;

import com.scrabblewinner.scrabble.board.components.Direction;
import com.scrabblewinner.scrabble.board.components.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CorrectWordsSelector {
    public static ArrayList<Word> select(ArrayList<String> potentialWords) {
        //todo wybierz te które: realnie da się ułożyć, nie kolidują z niczym
        //todo dodaj do globalnej possibleWords



         List<Word> list = potentialWords.stream().map(word -> new Word(word, 0, 0, Direction.VERTICAL)).collect(Collectors.toList());
         return new ArrayList<>(list);
    }
}
