function intToChar(int) {
    console.log(String.fromCharCode(int).length)
    return String.fromCharCode(int);
}

function charToInt(char) {
    return char.charCodeAt(0);
}

export function capitalize(text) {
    let destText = '';
    for (let i = 0; i < text.length; i++) {
        let char = text.charAt(i);

        if(charToInt(char) >= 61 && charToInt(char) <= 122) {
            console.log(destText)
            destText += char.toUpperCase();
        }

    }
    return destText;
}