import json
import sys

import cv2 as cv

import scrabble_image_processing as sip

path = sys.argv[1]

print(f'File path: {path}')
image = sip.load_image(path)

try:
    board = sip.BoardExtractor(image).set_debug(False).process().get_board(margin=50)
    letters = sip.LettersRecognizer(board.copy()).set_debug(False).process().get_letters()
    output = {"board": letters.tolist()}
except:
    output = "ERROR"

print(json.dumps({"output": output}))
cv.waitKey(0)
cv.destroyAllWindows()
