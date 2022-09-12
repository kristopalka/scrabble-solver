const letters = "aąbcćdeęfghijklłmnńoóprsśtuwyzźż".toUpperCase();
const values =  "15326215533132232171521152312195";
export const empty = " ";

export const maxHolderSize = 7;

export function getLetters() {
    return letters;
}

export function isValidLetter(letter) {
    return letters.indexOf(letter.toUpperCase()) !== -1;
}

export function getLetterValue(letter) {
    if(letter === empty) return ""

    let index = letters.indexOf(letter);
    return index === -1 ? "" : values[index];
}

export const exampleHolder = ['a','b','b','d',' ',' ',' '];

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
        "value": "bid",
        "direction": "VERTICAL",
        "ybegin": 11,
        "xbegin": 0
    },
    {
        "value": "bi",
        "direction": "VERTICAL",
        "ybegin": 11,
        "xbegin": 0
    },
    {
        "value": "bai",
        "direction": "VERTICAL",
        "ybegin": 10,
        "xbegin": 0
    },
    {
        "value": "bam",
        "direction": "VERTICAL",
        "ybegin": 11,
        "xbegin": 4
    },
    {
        "value": "ci",
        "direction": "VERTICAL",
        "ybegin": 11,
        "xbegin": 0
    }
]