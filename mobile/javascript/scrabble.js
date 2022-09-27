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


export const exampleHolder = ["A", "B", "Ć", "D", "E", "E", "G"];

export const exampleBoard = [
    [' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '],
    [' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '],
    [' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '],
    [' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '],
    [' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'M', ' '],
    [' ', ' ', ' ', ' ', ' ', ' ', ' ', 'Ż', 'A', 'B', 'K', 'A', ' ', 'O', ' '],
    [' ', ' ', ' ', ' ', ' ', ' ', 'M', ' ', ' ', ' ', 'O', ' ', ' ', 'R', ' '],
    [' ', ' ', ' ', ' ', ' ', ' ', 'I', ' ', ' ', ' ', 'T', ' ', ' ', 'D', ' '],
    [' ', ' ', ' ', ' ', ' ', ' ', 'S', 'Ł', 'O', 'N', 'E', 'C', 'Z', 'K', 'O'],
    [' ', ' ', ' ', ' ', ' ', ' ', 'I', ' ', ' ', ' ', 'K', ' ', ' ', 'A', ' '],
    [' ', ' ', ' ', ' ', ' ', ' ', 'U', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '],
    [' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '],
    [' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '],
    [' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '],
    [' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ']]

export const exampleBestWords = [
    {
        "value": "geod",
        "direction": "VERTICAL",
        "x": 6,
        "y": 8,
        "score": 24
    },
    {
        "value": "bean",
        "direction": "HORIZONTAL",
        "x": 5,
        "y": 9,
        "score": 23
    },
    {
        "value": "badać",
        "direction": "HORIZONTAL",
        "x": 2,
        "y": 8,
        "score": 22
    },
    {
        "value": "gadać",
        "direction": "HORIZONTAL",
        "x": 2,
        "y": 8,
        "score": 22
    },
    {
        "value": "ogać",
        "direction": "HORIZONTAL",
        "x": 8,
        "y": 14,
        "score": 21
    },
    {
        "value": "begu",
        "direction": "VERTICAL",
        "x": 10,
        "y": 3,
        "score": 20
    },
    {
        "value": "gaćże",
        "direction": "HORIZONTAL",
        "x": 2,
        "y": 7,
        "score": 20
    },
    {
        "value": "zdeba",
        "direction": "HORIZONTAL",
        "x": 8,
        "y": 12,
        "score": 19
    },
    {
        "value": "badu",
        "direction": "VERTICAL",
        "x": 10,
        "y": 3,
        "score": 18
    },
    {
        "value": "baud",
        "direction": "VERTICAL",
        "x": 10,
        "y": 4,
        "score": 18
    }
]