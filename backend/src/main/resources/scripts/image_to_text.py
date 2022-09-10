import json
import sys
from importlib.machinery import SourceFileLoader

import cv2 as cv

library_path = "/home/krist/Projects/Scrabble-Solver/python_scripts/scrabble_image_processing/__init__.py"
sip = SourceFileLoader("scrabble_image_processing", library_path).load_module()

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
