import cv2 as cv

from src.libs.corners_detection import find_corners
from src.libs.letters_mask import calculate_letters_mask
from src.libs.util.board import Board
from src.libs.util.cv_methods import load_image

photo = load_image('red/008.jpg', prefix="../../resources/photos/")  # should be good for 1, 3, 8
corners = find_corners(photo, debug=False)
board = Board(photo, corners, margin=50)
calculate_letters_mask(board, debug=True)
cv.waitKey(0)
cv.destroyAllWindows()