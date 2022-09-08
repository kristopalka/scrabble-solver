package com.scrabble.backend.api.resolving.algorithm.scrabble.board;

public class StandardBoard extends Board {
    public StandardBoard() {
        this.length = 15;
        this.fields = new Field[length][length];
        initializeFields();
        initializeBonuses();
    }
    

    private void initializeFields() {
        for (int x = 0; x < length; x++) {
            for (int y = 0; y < length; y++) {
                fields[x][y] = new Field(Field.Bonus.EMPTY);
            }
        }
    }

    private void initializeBonuses() {
        initializeBonusesDOUBLELETTER();
        initializeBonusesTRIPLELETTER();
        initializeBonusesDOUBLEWORD();
        initializeBonusesTRIPLEWORD();
    }

    private void initializeBonusesDOUBLELETTER() {
        fields[0][3] = new Field(Field.Bonus.DOUBLE_LETTER);
        fields[0][11] = new Field(Field.Bonus.DOUBLE_LETTER);

        fields[2][6] = new Field(Field.Bonus.DOUBLE_LETTER);
        fields[2][8] = new Field(Field.Bonus.DOUBLE_LETTER);

        fields[3][0] = new Field(Field.Bonus.DOUBLE_LETTER);
        fields[3][7] = new Field(Field.Bonus.DOUBLE_LETTER);
        fields[3][14] = new Field(Field.Bonus.DOUBLE_LETTER);

        fields[6][2] = new Field(Field.Bonus.DOUBLE_LETTER);
        fields[6][6] = new Field(Field.Bonus.DOUBLE_LETTER);
        fields[6][8] = new Field(Field.Bonus.DOUBLE_LETTER);
        fields[6][12] = new Field(Field.Bonus.DOUBLE_LETTER);


        fields[7][3] = new Field(Field.Bonus.DOUBLE_LETTER);
        fields[7][11] = new Field(Field.Bonus.DOUBLE_LETTER);


        fields[14][3] = new Field(Field.Bonus.DOUBLE_LETTER);
        fields[14][11] = new Field(Field.Bonus.DOUBLE_LETTER);

        fields[12][6] = new Field(Field.Bonus.DOUBLE_LETTER);
        fields[12][8] = new Field(Field.Bonus.DOUBLE_LETTER);

        fields[11][0] = new Field(Field.Bonus.DOUBLE_LETTER);
        fields[11][7] = new Field(Field.Bonus.DOUBLE_LETTER);
        fields[11][14] = new Field(Field.Bonus.DOUBLE_LETTER);

        fields[8][2] = new Field(Field.Bonus.DOUBLE_LETTER);
        fields[8][6] = new Field(Field.Bonus.DOUBLE_LETTER);
        fields[8][8] = new Field(Field.Bonus.DOUBLE_LETTER);
        fields[8][12] = new Field(Field.Bonus.DOUBLE_LETTER);
    }

    private void initializeBonusesTRIPLELETTER() {

        fields[1][5] = new Field(Field.Bonus.TRIPLE_LETTER);
        fields[1][9] = new Field(Field.Bonus.TRIPLE_LETTER);

        fields[5][1] = new Field(Field.Bonus.TRIPLE_LETTER);
        fields[5][5] = new Field(Field.Bonus.TRIPLE_LETTER);
        fields[5][9] = new Field(Field.Bonus.TRIPLE_LETTER);
        fields[5][13] = new Field(Field.Bonus.TRIPLE_LETTER);

        fields[9][1] = new Field(Field.Bonus.TRIPLE_LETTER);
        fields[9][5] = new Field(Field.Bonus.TRIPLE_LETTER);
        fields[9][9] = new Field(Field.Bonus.TRIPLE_LETTER);
        fields[9][13] = new Field(Field.Bonus.TRIPLE_LETTER);

        fields[13][5] = new Field(Field.Bonus.TRIPLE_LETTER);
        fields[13][9] = new Field(Field.Bonus.TRIPLE_LETTER);
    }

    private void initializeBonusesDOUBLEWORD() {
        fields[1][1] = new Field(Field.Bonus.DOUBLE_WORD);
        fields[2][2] = new Field(Field.Bonus.DOUBLE_WORD);
        fields[3][3] = new Field(Field.Bonus.DOUBLE_WORD);
        fields[4][4] = new Field(Field.Bonus.DOUBLE_WORD);

        fields[10][10] = new Field(Field.Bonus.DOUBLE_WORD);
        fields[11][11] = new Field(Field.Bonus.DOUBLE_WORD);
        fields[12][12] = new Field(Field.Bonus.DOUBLE_WORD);
        fields[13][13] = new Field(Field.Bonus.DOUBLE_WORD);

        fields[1][13] = new Field(Field.Bonus.DOUBLE_WORD);
        fields[2][12] = new Field(Field.Bonus.DOUBLE_WORD);
        fields[3][11] = new Field(Field.Bonus.DOUBLE_WORD);
        fields[4][10] = new Field(Field.Bonus.DOUBLE_WORD);

        fields[10][4] = new Field(Field.Bonus.DOUBLE_WORD);
        fields[11][3] = new Field(Field.Bonus.DOUBLE_WORD);
        fields[12][2] = new Field(Field.Bonus.DOUBLE_WORD);
        fields[13][1] = new Field(Field.Bonus.DOUBLE_WORD);

    }

    private void initializeBonusesTRIPLEWORD() {
        fields[0][0] = new Field(Field.Bonus.TRIPLE_WORD);
        fields[14][0] = new Field(Field.Bonus.TRIPLE_WORD);
        fields[0][14] = new Field(Field.Bonus.TRIPLE_WORD);
        fields[14][14] = new Field(Field.Bonus.TRIPLE_WORD);

        fields[0][7] = new Field(Field.Bonus.TRIPLE_WORD);
        fields[7][0] = new Field(Field.Bonus.TRIPLE_WORD);
        fields[7][14] = new Field(Field.Bonus.TRIPLE_WORD);
        fields[14][7] = new Field(Field.Bonus.TRIPLE_WORD);
    }
}
