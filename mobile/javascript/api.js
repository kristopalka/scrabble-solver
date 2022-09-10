import {decode} from 'base-64'
import {logger} from "./logger";


const backendUrl = "http://192.168.1.11:8080"

export function imageToText(image) {
    logger("Sending to backend");
    fetch(backendUrl + "/image-to-text", {method: 'POST', body: image})
        .then(r => r.json())
        .then((data) => {
            logger(`response: ${data}`)
        })
}