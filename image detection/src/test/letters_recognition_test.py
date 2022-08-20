from src.libs.corners_detection import *
from src.libs.letters_mask import calculate_letters_mask
from src.libs.letters_recognition import *
from src.libs.util.board import *
from src.libs.util.cv_methods import *
import cv2 as cv

photo = load_image('red/001.jpg', prefix="../../resources/photos/")  # should be good for 1, 3, 8

corners = find_corners(photo, debug=False)
board = Board(photo, corners, margin=50)
mask = calculate_letters_mask(board, debug=False)

board_to_text(board, mask, debug=True)

cv.waitKey(0)
cv.destroyAllWindows()
