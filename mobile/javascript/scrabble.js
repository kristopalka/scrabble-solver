export const empty = " ";
export const mark = "^";


export class ScrabbleLettersValues {
    letters;
    values;

    constructor(settings, langIndex) {
        this.letters = settings.lettersValues[langIndex].letters;
        this.values = settings.lettersValues[langIndex].values;
    }


    isLetterOrEmptySymbol(letter) {
        return this.letters.indexOf(letter.toLowerCase()) !== -1 || letter === empty;
    }

    getLetterValue(letter) {
        if (letter === empty) return ""

        let index = this.letters.indexOf(letter.toLowerCase());
        return index === -1 ? "" : this.values[index];
    }
}

export function cloneBoard(board) {
    let newBoard = [];

    for (let i = 0; i < board.length; i++)
        newBoard[i] = board[i].slice();
    return newBoard;
}

export function addWordToBoard(board, word) {
    //let required = [];
    if (word.direction === "VERTICAL") {
        for (let y = 0; y < word.value.length; y++) {
            //if(board[word.x][word.y + y] === empty) required.push(word.value[y]);
            board[word.x][word.y + y] = word.value[y] + mark;
        }
    } else {
        for (let x = 0; x < word.value.length; x++) {
            //if(board[x + word.x][word.y] === empty) required.push(word.value[x]);
            board[x + word.x][word.y] = word.value[x] + mark;
        }
    }
    return board;
}

export function boardToString(board) {
    let builder = "+------------------------------+\n"

    for (let y = 0; y < board.length; y++) {
        builder += "|";
        for (let x = 0; x < board.length; x++) {
            if (board[x][y] === '') builder += '  ';
            else builder += board[x][y] + ' ';
        }
        builder += "|\n";
    }
    builder += "+------------------------------+";
    return builder;
}


export const emptyHolder = [' ', ' ', ' ', ' ', ' ', ' ', ' '];

export const emptyBoard = [
    [' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '],
    [' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '],
    [' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '],
    [' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '],
    [' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '],
    [' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '],
    [' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '],
    [' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '],
    [' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '],
    [' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '],
    [' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '],
    [' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '],
    [' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '],
    [' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '],
    [' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ']]