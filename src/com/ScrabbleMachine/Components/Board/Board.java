package com.ScrabbleMachine.Components.Board;

import com.ScrabbleMachine.Components.Alphabet;
import com.ScrabbleMachine.Components.Direction;
import com.ScrabbleMachine.Components.Word;

import java.security.InvalidParameterException;

public abstract class Board {
    protected int length;
    protected Field[][] fields;


    public void addWord(Word word) {
        if (word.direction == Direction.VERTICAL) addWordVertically(word.value, word.xStart, word.yStart);
        else addWordHorizontally(word.value, word.xStart, word.yStart);
    }

    public void addWordHorizontally(String word, int xStart, int yStart) {
        if (xStart < 0 || xStart >= length || yStart < 0 || yStart >= length)
            throw new InvalidParameterException("Start parameter out of range");
        if (xStart + word.length() >= length + 1) throw new InvalidParameterException("Word goes off board");

        for (int x = 0; x < word.length(); x++) {
            insertValueToField(fields[x + xStart][yStart], word.charAt(x));
        }
    }

    public void addWordVertically(String word, int xStart, int yStart) {
        if (xStart < 0 || xStart >= length || yStart < 0 || yStart >= length)
            throw new InvalidParameterException("Start parameter out of range");
        if (yStart + word.length() >= length + 1) throw new InvalidParameterException("Word goes off scale");

        for (int y = 0; y < word.length(); y++) {
            insertValueToField(fields[xStart][yStart + y], word.charAt(y));
        }
    }

    private void insertValueToField(Field field, char letter) {
        if(!field.isEmpty() || field.getValue() != letter) throw new InvalidParameterException("You try to override letter in board with other one");
        field.setValue(letter);
    }



    public void printBoard() {
        for (int y = 0; y < length; y++) {
            for (int x = 0; x < length; x++) {
                System.out.print(fields[x][y].getValue() + " ");
            }
            System.out.println("");
        }
    }

}
