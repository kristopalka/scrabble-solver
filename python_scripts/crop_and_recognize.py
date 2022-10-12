import json
import sys

import cv2 as cv

import scrabble_image_processing as sip

path = sys.argv[1]
argsCorners = json.loads(sys.argv[2])
lang = sys.argv[3]

corners = []
for argsCorner in argsCorners:
    corners.append([argsCorner["x"], argsCorner["y"]])

image = sip.load_image(path)



board = sip.BoardExtractor(image).set_debug(False).set_corners(corners).get_board(margin=50)
letters = sip.LettersRecognizer(board.copy()).set_debug(False).recognize().get_letters()
output = {"board": letters.tolist()}


print(json.dumps({"output": output}))
cv.waitKey(0)
cv.destroyAllWindows()
