import json
import sys

import cv2 as cv

import scrabble_image_processing as sip

path = sys.argv[1]

image = sip.load_image(path)

try:
    corners = sip.BoardExtractor(image).set_debug(False).find_corners().get_corners()
    (height, width) = image.shape[:2]

    output = json.dumps({
        "output": [
            {"x": float(corners[0][0] / width), "y": float(corners[0][1] / height)},
            {"x": float(corners[1][0] / width), "y": float(corners[1][1] / height)},
            {"x": float(corners[2][0] / width), "y": float(corners[2][1] / height)},
            {"x": float(corners[3][0] / width), "y": float(corners[3][1] / height)}
        ]})
except:
    output = json.dumps({"output": "NOT_FOUND"})

print(output)

cv.waitKey(0)
cv.destroyAllWindows()
