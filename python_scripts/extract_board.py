import sys

import cv2 as cv

import scrabble_image_processing as sip

path = sys.argv[1]

print(f'File path: {path}')
image = sip.load_image(path)
board = sip.BoardExtractor(image).set_debug(False).process().get_board(margin=0)
sip.save_image(path, board.image)

cv.waitKey(0)
cv.destroyAllWindows()
