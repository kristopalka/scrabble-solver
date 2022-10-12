import json
import sys

import cv2 as cv

import scrabble_image_processing as sip

path = sys.argv[1]

image = sip.load_image(path)

corners = sip.BoardExtractor(image).set_debug(False).find_corners().get_corners()

print(corners)

output = json.dumps({
    "output": [
        {"x": int(corners[0][0]), "y": int(corners[0][1])},
        {"x": int(corners[1][0]), "y": int(corners[1][1])},
        {"x": int(corners[2][0]), "y": int(corners[2][1])},
        {"x": int(corners[3][0]), "y": int(corners[3][1])}
    ]})

print(output)

cv.waitKey(0)
cv.destroyAllWindows()
