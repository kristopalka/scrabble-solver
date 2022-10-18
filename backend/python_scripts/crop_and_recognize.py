import json
import sys

import cv2 as cv

import scrabble_image_processing as sip

path = sys.argv[1]
argsCorners = json.loads(sys.argv[2])
lang = sys.argv[3]
allow_letters = sys.argv[4]

image = sip.load_image(path)
(height, width) = image.shape[:2]

print(allow_letters)

corners = []
for argsCorner in argsCorners:
    corners.append([
        int(argsCorner["x"] * width),
        int(argsCorner["y"] * height)
    ])

board = sip.BoardExtractor(image).set_debug(False).set_corners(corners).get_board(margin=20)
letters = sip.LettersRecognizer(board.copy(), allow_letters=allow_letters, lang=lang).set_debug(True) \
    .recognize().get_letters()

print(json.dumps({"output": letters.tolist()}))
cv.waitKey(0)
cv.destroyAllWindows()
