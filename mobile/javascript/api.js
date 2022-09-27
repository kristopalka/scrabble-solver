export const connectionError = "connection error";
export const notFoundBoard = "not found board";
export const somethingWentWrong = "somethingWentWrong";


export function requestImageToText(url, image, lang) {
    url = url + `/image-to-text?lang=${lang}`;

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

export function requestSolveScrabble(url, board, holder, lang, mode, number) {
    const data = {"board": board, "holder": holder};
    url = url + `/solve-scrabble?lang=${lang}&mode=${mode}&number=${number}`;

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
}

export function requestInfo(url) {
    return fetch(url + "/info")
        .then(async (response) => {
            switch(response.status) {
                case 200:
                    return await response.json()
                default:
                    throw somethingWentWrong;
            }
        })
}
