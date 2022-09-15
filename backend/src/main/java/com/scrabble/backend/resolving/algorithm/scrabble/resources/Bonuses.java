package com.scrabble.backend.resolving.algorithm.scrabble.resources;

import java.awt.*;
import java.util.Arrays;

import static com.scrabble.backend.resolving.algorithm.scrabble.Static.boardSize;

public class Bonuses {
    public enum Bonus {EMPTY, DOUBLE_LETTER, TRIPLE_LETTER, DOUBLE_WORD, TRIPLE_WORD}

    private static final Bonus[][] bonuses;

    static {
        bonuses = new Bonus[boardSize][boardSize];
        initializeBonuses();
    }

    private static void initializeBonuses() {
        for (Bonus[] row : bonuses) Arrays.fill(row, Bonus.EMPTY);
        initializeBonusesDOUBLELETTER();
        initializeBonusesTRIPLELETTER();
        initializeBonusesDOUBLEWORD();
        initializeBonusesTRIPLEWORD();
    }

    private static void initializeBonusesDOUBLELETTER() {
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

    private static void initializeBonusesTRIPLELETTER() {

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

    private static void initializeBonusesDOUBLEWORD() {
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

    private static void initializeBonusesTRIPLEWORD() {
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
        return bonuses;
    }

    public static Bonus getBonusAt(Point point) {
        if (point.x < 0 || point.x >= boardSize || point.y < 0 || point.y >= boardSize)
            throw new IllegalArgumentException(String.format("Given coordinates (%d,%d) goes beyond field", point.x, point.y));
        return bonuses[point.x][point.y];
    }

}
