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

export function markWordOnBoard(board, word) {
    let newBoard = cloneBoard(board);
    if (word.direction === "VERTICAL") {
        for (let y = 0; y < word.value.length; y++) {
            newBoard[word.x][word.y + y] = word.value[y] + mark;
        }
    } else {
        for (let x = 0; x < word.value.length; x++) {
            newBoard[x + word.x][word.y] = word.value[x] + mark;
        }
    }
    return newBoard;
}


export function markUsedLettersOnHolder(holder, word) {
    let newHolder = holder.slice();
    for(let i=0; i<word.usedLetters.length; i++){
        const letter = word.usedLetters[i].toUpperCase();
        const foundIndex = newHolder.findIndex((e) => e === letter)
        newHolder[foundIndex] = newHolder[foundIndex] + mark;
    }
    return newHolder;
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


export const defaultCorners = [{"x": 1/8, "y": 7/32}, {"x": 1/8, "y": 25/32}, {"x": 7/8, "y": 7/32}, {"x": 7/8, "y": 25/32}];

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