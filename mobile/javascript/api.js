import {logger} from "./logger";
import {defaultCorners} from "./scrabble";

export const notFoundWords = "not found words";
export const somethingWentWrong = "something went wrong";
export const networkFailed = "TypeError: Network request failed";

export function requestFindCorners(url, image) {
    logger("Finding corners in backend");
    url = url + '/find-corners';

    return fetch(url, {method: 'POST', body: image})
        .then(async (response) => {
            logger("Response status: " + response.status)

            switch(response.status) {
                case 200:
                    return await response.json();
                case 500:
                    return defaultCorners;
                default:
                    throw somethingWentWrong;
            }
        })
}


export function requestCropAndRecognize(url, image, corners, lang) {
    logger("Cropping and recognizing in backend");
    const data = {"base64Image": image, "corners": corners};
    url = url + `/crop-and-recognize?lang=${lang}`;

    return fetch(url, {
        method: 'POST',
        body: JSON.stringify(data),
        headers: {'Content-Type': 'application/json'},
    })
        .then(async (response) => {
            logger("Response status: " + response.status)
            switch(response.status) {
                case 200:
                    return await response.json()
                default:
                    throw somethingWentWrong;
            }
        })
}

export function requestSolveScrabble(url, board, holder, lang, mode, number) {
    logger("Solving in backend");
    const data = {"board": board, "holder": holder};
    url = url + `/solve?lang=${lang}&mode=${mode}&number=${number}`;

    return fetch(url, {
        method: 'POST',
        body: JSON.stringify(data),
        headers: {'Content-Type': 'application/json'},
    })
        .then(async (response) => {
            logger("Response status: " + response.status)
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
            logger("Response status: " + response.status)
            switch(response.status) {
                case 200:
                    return await response.json()
                default:
                    throw somethingWentWrong;
            }
        })
}
