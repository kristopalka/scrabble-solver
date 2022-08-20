import cv2 as cv

from src.libs.util.cv_methods import *
from src.libs.util.drawing import *


def init_extract_board_from_raw_image(raw_image, corners, margin):
    (x, y), (width, height), angle = cv.minAreaRect(corners)
    board_size = int(min(width, height))
    image_size = int(board_size + 2 * margin)

    destination = [[margin, margin],
                   [image_size - margin, margin],
                   [image_size - margin, image_size - margin],
                   [margin, image_size - margin]]

    matrix = cv.getPerspectiveTransform(np.float32(corners), np.float32(destination))
    board_image = cv.warpPerspective(raw_image, matrix, (image_size, image_size))

    return board_image, board_size



class Board:
    board_image = None
    board_image_preprocessed = None
    board_size = None
    margin = None
    field_size = None
    letters_mask = None

    def __init__(self, raw_image, corners, margin=20):
        self.board_image, self.board_size = init_extract_board_from_raw_image(raw_image, corners, margin)
        self.margin = margin
        self.field_size = self.board_size // 15
        self.board_image_preprocessed = self.board_image

    def preprocess_image(self, function):
        self.board_image_preprocessed = function(self.board_image)

    def get_field_coords(self, row, column):
        x = int(self.margin + (self.board_size * (row / 15))) + 1
        y = int(self.margin + (self.board_size * (column / 15))) + 1

        return x, y, self.field_size

    def get_board(self, preprocessed=False):
        if preprocessed:
            return self.board_image_preprocessed.copy()
        else:
            return self.board_image.copy()

    def get_field(self, coords, field_margin=0, preprocessed=False):
        assert -field_margin < self.field_size // 2, "Minus field margin can not be grater than half of field"
        (row, column) = coords

        x = int(self.margin + (self.board_size * (row / 15))) + 1
        y = int(self.margin + (self.board_size * (column / 15))) + 1

        x1 = max(x - field_margin, 0)
        y1 = max(y - field_margin, 0)
        x2 = min(x + self.field_size + field_margin, self.board_size + 2 * self.margin)
        y2 = min(y + self.field_size + field_margin, self.board_size + 2 * self.margin)

        if preprocessed:
            return self.board_image_preprocessed[y1:y2, x1:x2]
        else:
            return self.board_image[y1:y2, x1:x2]

    def get_board_with_grid(self, color=(0, 255, 0), thickness=2, preprocessed=False):
        if preprocessed:
            image = self.board_image_preprocessed.copy()
            image = cv.cvtColor(image, cv.COLOR_GRAY2BGR)
        else:
            image = self.board_image.copy()

        (top, left, bottom, right) = (
            self.margin, self.margin, image.shape[0] - self.margin, image.shape[1] - self.margin)

        for width in np.linspace(left, right, 16):
            width_int = round(width)
            cv.line(image, (width_int, top), (width_int, bottom), color, thickness=thickness)

        for height in np.linspace(top, bottom, 16):
            height_int = round(height)
            cv.line(image, (left, height_int), (right, height_int), color, thickness=thickness)

        return image
