package com.scrabblewinner.scrabble.board.components;

import com.scrabblewinner.scrabble.alphabet.Alphabet;
import com.scrabblewinner.scrabble.board.Board;
import lombok.AllArgsConstructor;
import lombok.ToString;

import static com.scrabblewinner.scrabble.board.components.Direction.VERTICAL;

@AllArgsConstructor
@ToString
public class Word {
    public String value;
    public int xStart;
    public int yStart;
    public Direction direction;

    public int getLength() {
        return value.length();
    }

    public char charAt(int index) {
        return value.charAt(index);
    }

    public int getPoints(Board board) {
        int totalPoints = 0;
        int multiplier = 1;

        for (int i = 0; i < getLength(); i++) {
            Bonus bonus;
            if (direction == VERTICAL) bonus = board.getField(xStart, yStart + i).getBonus();
            else bonus = board.getField(xStart + i, yStart).getBonus();


            int letterPoints = Alphabet.valueOfLetter(charAt(i));

            switch (bonus) {
                case EMPTY -> totalPoints += letterPoints;
                case DOUBLE_LETTER -> totalPoints += 2 * letterPoints;
                case TRIPLE_LETTER -> totalPoints += 3 * letterPoints;
                case DOUBLE_WORD -> {
                    totalPoints += letterPoints;
                    multiplier *= 2;
                }
                case TRIPLE_WORD -> {
                    totalPoints += letterPoints;
                    multiplier *= 3;
                }
            }
        }
        totalPoints *= multiplier;
        return totalPoints;
    }
}
