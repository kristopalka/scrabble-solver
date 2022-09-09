const letters = "aąbcćdeęfghijklłmnńoóprsśtuwyzźż".toUpperCase();
const values =  "15326215533132232171521152312195";

export function getLetters() {
    return letters;
}

export function isValidLetter(letter) {
    return letters.toUpperCase().indexOf(letter.toUpperCase()) !== -1;
}

export function getLetterValue(letter) {
    if(letter === "") return ""

    let index = letters.indexOf(letter);
    return index === -1 ? "" : values[index];
}

export default {getLetters, getLetterValue, isValid: isValidLetter}