import cv2 as cv
from pytesseract import Output
import pytesseract as tr
from math import dist

from src.libs.corners_detection import *
from src.libs.util.board import *


def box_comparator(box, image_center):
    x, y, w, h = box

    size = (w * h)
    center = (x + w // 2, y + h // 2)
    distance = dist(center, image_center)

    if distance == 0: return size
    return size / distance

def calculate_mser(image):
    board_gray = cv.cvtColor(image, cv.COLOR_BGR2GRAY)
    board_gray = cv.equalizeHist(board_gray)

    max_tile_area = (image.shape[0] * image.shape[1]) // 4
    min_tile_area = (image.shape[0] * image.shape[1]) // 25
    MSER = cv.MSER_create(max_area=max_tile_area, min_area=min_tile_area, delta=30)

    regions, boxes = MSER.detectRegions(board_gray)

    if len(boxes) == 0: return None

    image_center = (image.shape[0] // 2, image.shape[1] // 2)
    sorted_boxes = sorted(boxes, reverse=True, key=lambda box: box_comparator(box, image_center))

    return sorted_boxes[0]


photo = load_image('red/001.jpg')

corners = find_corners(photo, debug=False)

board = Board(photo, corners, margin=50)
print_image('Scrabble', board.get_board_with_grid())


board_image = board.board_image
for x in range(0, 15):
    for y in range(0, 15):
        field_image = board.get_field((x, y), field_margin=0)

        box = calculate_mser(field_image)
        if box is not None:
            field = board.get_field_coords(x, y)

            p1 = (field[0] + box[0], field[1] + box[1])
            p2 = (field[0] + box[0] + box[2], field[1] + box[1] + box[3])

            cv.rectangle(board_image, p1, p2, red, 2)

print_image('Letters', board_image)


cv.waitKey(0)
cv.destroyAllWindows()
