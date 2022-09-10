import {decode} from 'base-64'


const backendUrl = "http://192.168.1.11:8080"


function base64ToArrayBuffer(base64) {
    const binary_string = decode(base64);
    const len = binary_string.length;
    const bytes = new Uint8Array(len);
    for (let i = 0; i < len; i++) {
        bytes[i] = binary_string.charCodeAt(i);
    }
    return bytes.buffer;
}

export async function imageToText(image) {
    console.log("Sending to backend");
    fetch(backendUrl + "/image-to-text", {
        method: 'POST',
        body: image,
    }).then(r => {
        console.log(`Result: ${r.status}`);
    })
}