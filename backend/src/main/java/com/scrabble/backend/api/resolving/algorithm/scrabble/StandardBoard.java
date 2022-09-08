package com.scrabble.backend.api.resolving.algorithm.scrabble;

import java.security.InvalidParameterException;

public class StandardBoard { // singleton
    public enum Bonus {EMPTY, DOUBLE_LETTER, TRIPLE_LETTER, DOUBLE_WORD, TRIPLE_WORD}

    private static StandardBoard instance;
    private static int size;
    private static Bonus[][] bonuses;

    private static void ifNoInstanceCreate() {
        if (instance == null) instance = new StandardBoard();
    }

    private StandardBoard() {
        size = 15;
        bonuses = new Bonus[size][size];
        initializeBonuses();
    }

    private void initializeBonuses() {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                bonuses[x][y] = Bonus.EMPTY;
            }
        }

        initializeBonusesDOUBLELETTER();
        initializeBonusesTRIPLELETTER();
        initializeBonusesDOUBLEWORD();
        initializeBonusesTRIPLEWORD();
    }

    private void initializeBonusesDOUBLELETTER() {
        bonuses[0][3] = Bonus.DOUBLE_LETTER;
        bonuses[0][11] = Bonus.DOUBLE_LETTER;

        bonuses[2][6] = Bonus.DOUBLE_LETTER;
        bonuses[2][8] = Bonus.DOUBLE_LETTER;

        bonuses[3][0] = Bonus.DOUBLE_LETTER;
        bonuses[3][7] = Bonus.DOUBLE_LETTER;
        bonuses[3][14] = Bonus.DOUBLE_LETTER;

        bonuses[6][2] = Bonus.DOUBLE_LETTER;
        bonuses[6][6] = Bonus.DOUBLE_LETTER;
        bonuses[6][8] = Bonus.DOUBLE_LETTER;
        bonuses[6][12] = Bonus.DOUBLE_LETTER;


        bonuses[7][3] = Bonus.DOUBLE_LETTER;
        bonuses[7][11] = Bonus.DOUBLE_LETTER;


        bonuses[14][3] = Bonus.DOUBLE_LETTER;
        bonuses[14][11] = Bonus.DOUBLE_LETTER;

        bonuses[12][6] = Bonus.DOUBLE_LETTER;
        bonuses[12][8] = Bonus.DOUBLE_LETTER;

        bonuses[11][0] = Bonus.DOUBLE_LETTER;
        bonuses[11][7] = Bonus.DOUBLE_LETTER;
        bonuses[11][14] = Bonus.DOUBLE_LETTER;

        bonuses[8][2] = Bonus.DOUBLE_LETTER;
        bonuses[8][6] = Bonus.DOUBLE_LETTER;
        bonuses[8][8] = Bonus.DOUBLE_LETTER;
        bonuses[8][12] = Bonus.DOUBLE_LETTER;
    }

    private void initializeBonusesTRIPLELETTER() {

        bonuses[1][5] = Bonus.TRIPLE_LETTER;
        bonuses[1][9] = Bonus.TRIPLE_LETTER;

        bonuses[5][1] = Bonus.TRIPLE_LETTER;
        bonuses[5][5] = Bonus.TRIPLE_LETTER;
        bonuses[5][9] = Bonus.TRIPLE_LETTER;
        bonuses[5][13] = Bonus.TRIPLE_LETTER;

        bonuses[9][1] = Bonus.TRIPLE_LETTER;
        bonuses[9][5] = Bonus.TRIPLE_LETTER;
        bonuses[9][9] = Bonus.TRIPLE_LETTER;
        bonuses[9][13] = Bonus.TRIPLE_LETTER;

        bonuses[13][5] = Bonus.TRIPLE_LETTER;
        bonuses[13][9] = Bonus.TRIPLE_LETTER;
    }

    private void initializeBonusesDOUBLEWORD() {
        bonuses[1][1] = Bonus.DOUBLE_WORD;
        bonuses[2][2] = Bonus.DOUBLE_WORD;
        bonuses[3][3] = Bonus.DOUBLE_WORD;
        bonuses[4][4] = Bonus.DOUBLE_WORD;

        bonuses[10][10] = Bonus.DOUBLE_WORD;
        bonuses[11][11] = Bonus.DOUBLE_WORD;
        bonuses[12][12] = Bonus.DOUBLE_WORD;
        bonuses[13][13] = Bonus.DOUBLE_WORD;

        bonuses[1][13] = Bonus.DOUBLE_WORD;
        bonuses[2][12] = Bonus.DOUBLE_WORD;
        bonuses[3][11] = Bonus.DOUBLE_WORD;
        bonuses[4][10] = Bonus.DOUBLE_WORD;

        bonuses[10][4] = Bonus.DOUBLE_WORD;
        bonuses[11][3] = Bonus.DOUBLE_WORD;
        bonuses[12][2] = Bonus.DOUBLE_WORD;
        bonuses[13][1] = Bonus.DOUBLE_WORD;

    }

    private void initializeBonusesTRIPLEWORD() {
        bonuses[0][0] = Bonus.TRIPLE_WORD;
        bonuses[14][0] = Bonus.TRIPLE_WORD;
        bonuses[0][14] = Bonus.TRIPLE_WORD;
        bonuses[14][14] = Bonus.TRIPLE_WORD;

        bonuses[0][7] = Bonus.TRIPLE_WORD;
        bonuses[7][0] = Bonus.TRIPLE_WORD;
        bonuses[7][14] = Bonus.TRIPLE_WORD;
        bonuses[14][7] = Bonus.TRIPLE_WORD;
    }


    public static Bonus[][] getBonuses() {
        ifNoInstanceCreate();
        return bonuses;
    }

    public static Bonus getBonusAt(int x, int y) {
        ifNoInstanceCreate();
        if (x < 0 || x >= size || y < 0 || y >= size)
            throw new InvalidParameterException(String.format("Given coordinates (%d,%d) goes beyond field", x, y));
        return bonuses[x][y];
    }

    public static int getSize() {
        ifNoInstanceCreate();
        return size;
    }

}
