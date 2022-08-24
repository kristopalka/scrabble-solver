import sys
import json
from importlib.machinery import SourceFileLoader

import cv2 as cv

library_path = "/home/krist/Projects/Scrabble-Solver/python/scrabble_image_processing/__init__.py"
sip = SourceFileLoader("scrabble_image_processing", library_path).load_module()

path = sys.argv[1]

print(f'File path: {path}')
image = sip.load_image(path)
board = sip.BoardExtractor(image).set_debug(False).process().get_board(margin=0)
sip.save_image(path, board.image)

cv.waitKey(0)
cv.destroyAllWindows()
