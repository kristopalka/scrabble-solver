const backendUrl = "http://192.168.0.139:8080"

export function requestImageToText(image) {
    return fetch(backendUrl + "/image-to-text", {method: 'POST', body: image})
        .then(response => response.json())
        .then((data) => {
            return data["board"];
        })
}

export function solveScrabble(request) {
    return fetch(backendUrl + "/solve-scrabble", {
        method: 'POST',
        body: JSON.stringify(request),
        headers: {'Content-Type': 'application/json'},
    })
        .then(response => response.json())
        .then((data) => {
            return data;
        })
}