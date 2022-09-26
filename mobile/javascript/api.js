const backend = "http://192.168.1.11:8080"

export const connectionError = "connection error";
export const notFoundBoard = "not found board";
export const somethingWentWrong = "somethingWentWrong";


export function requestImageToText(image) {
    const url = backend + "/image-to-text";

    return fetch(url, {method: 'POST', body: image})
        .then(async (response) => {
            switch(response.status) {
                case 200:
                    const data = await response.json()
                    return data["board"];
                case 500:
                    throw notFoundBoard;
                default:
                    throw somethingWentWrong;
            }
        })
}

export function requestSolveScrabble(board, holder, lang, mode, number) {
    const data = {"board": board, "holder": holder};
    const url = backend + `/solve-scrabble?lang=${lang}&mode=${mode}&number=${number}`;

    return fetch(url, {
        method: 'POST',
        body: JSON.stringify(data),
        headers: {'Content-Type': 'application/json'},
    })
        .then(async (response) => {
            switch(response.status) {
                case 200:
                    return await response.json()
                default:
                    throw somethingWentWrong;
            }
        })
        .catch((error) => {
            console.log(error)
        })
}

export function requestInfo() {
    return fetch(backend + "/info")
        .then(async (response) => {
            switch(response.status) {
                case 200:
                    return await response.json()
                default:
                    throw somethingWentWrong;
            }
        })
}