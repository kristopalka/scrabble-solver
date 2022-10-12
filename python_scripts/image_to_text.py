import json
import sys

import cv2 as cv

import scrabble_image_processing as sip

image = sip.load_image(sys.argv[1])


board = sip.BoardExtractor(image).set_debug(False).find_corners().get_board(margin=50)
letters = sip.LettersRecognizer(board.copy()).set_debug(False).recognize().get_letters()
output = {"board": letters.tolist()}


print(json.dumps({"output": output}))
cv.waitKey(0)
cv.destroyAllWindows()
