package com.ScrabbleSolver.BoardComponents;

public class StandardBoard extends Board
{
    public StandardBoard()
    {
        inicializeBoard();
    }

    private void inicializeBoard()
    {
        for (int x = 0; x<length; x++)
        {
            for (int y = 0; y<length; y++)
            {
                fields[x][y] = new Field();
            }
        }
        inicializeBonuses();
    }

    private void inicializeBonuses()
    {
        inicializeBonusesDOUBLELETTER();
        inicializeBonusesTRIPLELETTER();
        inicializeBonusesDOUBLEWORD();
        inicializeBonusesTRIPLEWORD();
    }

    private void inicializeBonusesDOUBLELETTER()
    {
        fields[0][3].setBonusType(Bonus.DOUBLE_LETTER);
        fields[0][11].setBonusType(Bonus.DOUBLE_LETTER);

        fields[2][6].setBonusType(Bonus.DOUBLE_LETTER);
        fields[2][8].setBonusType(Bonus.DOUBLE_LETTER);

        fields[3][0].setBonusType(Bonus.DOUBLE_LETTER);
        fields[3][7].setBonusType(Bonus.DOUBLE_LETTER);
        fields[3][14].setBonusType(Bonus.DOUBLE_LETTER);

        fields[6][2].setBonusType(Bonus.DOUBLE_LETTER);
        fields[6][6].setBonusType(Bonus.DOUBLE_LETTER);
        fields[6][8].setBonusType(Bonus.DOUBLE_LETTER);
        fields[6][12].setBonusType(Bonus.DOUBLE_LETTER);


        fields[7][3].setBonusType(Bonus.DOUBLE_LETTER);
        fields[7][11].setBonusType(Bonus.DOUBLE_LETTER);


        fields[14][3].setBonusType(Bonus.DOUBLE_LETTER);
        fields[14][11].setBonusType(Bonus.DOUBLE_LETTER);

        fields[12][6].setBonusType(Bonus.DOUBLE_LETTER);
        fields[12][8].setBonusType(Bonus.DOUBLE_LETTER);

        fields[11][0].setBonusType(Bonus.DOUBLE_LETTER);
        fields[11][7].setBonusType(Bonus.DOUBLE_LETTER);
        fields[11][14].setBonusType(Bonus.DOUBLE_LETTER);

        fields[8][2].setBonusType(Bonus.DOUBLE_LETTER);
        fields[8][6].setBonusType(Bonus.DOUBLE_LETTER);
        fields[8][8].setBonusType(Bonus.DOUBLE_LETTER);
        fields[8][12].setBonusType(Bonus.DOUBLE_LETTER);
    }

    private void inicializeBonusesTRIPLELETTER()
    {

        fields[1][5].setBonusType(Bonus.TRIPLE_LETTER);
        fields[1][9].setBonusType(Bonus.TRIPLE_LETTER);

        fields[5][1].setBonusType(Bonus.TRIPLE_LETTER);
        fields[5][5].setBonusType(Bonus.TRIPLE_LETTER);
        fields[5][9].setBonusType(Bonus.TRIPLE_LETTER);
        fields[5][13].setBonusType(Bonus.TRIPLE_LETTER);

        fields[9][1].setBonusType(Bonus.TRIPLE_LETTER);
        fields[9][5].setBonusType(Bonus.TRIPLE_LETTER);
        fields[9][9].setBonusType(Bonus.TRIPLE_LETTER);
        fields[9][13].setBonusType(Bonus.TRIPLE_LETTER);

        fields[13][5].setBonusType(Bonus.TRIPLE_LETTER);
        fields[13][9].setBonusType(Bonus.TRIPLE_LETTER);
    }

    private void inicializeBonusesDOUBLEWORD()
    {
        fields[1][1].setBonusType(Bonus.DOUBLE_WORD);
        fields[2][2].setBonusType(Bonus.DOUBLE_WORD);
        fields[3][3].setBonusType(Bonus.DOUBLE_WORD);
        fields[4][4].setBonusType(Bonus.DOUBLE_WORD);

        fields[10][10].setBonusType(Bonus.DOUBLE_WORD);
        fields[11][11].setBonusType(Bonus.DOUBLE_WORD);
        fields[12][12].setBonusType(Bonus.DOUBLE_WORD);
        fields[13][13].setBonusType(Bonus.DOUBLE_WORD);

        fields[1][13].setBonusType(Bonus.DOUBLE_WORD);
        fields[2][12].setBonusType(Bonus.DOUBLE_WORD);
        fields[3][11].setBonusType(Bonus.DOUBLE_WORD);
        fields[4][10].setBonusType(Bonus.DOUBLE_WORD);

        fields[10][4].setBonusType(Bonus.DOUBLE_WORD);
        fields[11][3].setBonusType(Bonus.DOUBLE_WORD);
        fields[12][2].setBonusType(Bonus.DOUBLE_WORD);
        fields[13][1].setBonusType(Bonus.DOUBLE_WORD);

    }

    private void inicializeBonusesTRIPLEWORD()
    {
        fields[0][0].setBonusType(Bonus.TRIPLE_WORD);
        fields[14][0].setBonusType(Bonus.TRIPLE_WORD);
        fields[0][14].setBonusType(Bonus.TRIPLE_WORD);
        fields[14][14].setBonusType(Bonus.TRIPLE_WORD);

        fields[0][7].setBonusType(Bonus.TRIPLE_WORD);
        fields[7][0].setBonusType(Bonus.TRIPLE_WORD);
        fields[7][14].setBonusType(Bonus.TRIPLE_WORD);
        fields[14][7].setBonusType(Bonus.TRIPLE_WORD);
    }
}
