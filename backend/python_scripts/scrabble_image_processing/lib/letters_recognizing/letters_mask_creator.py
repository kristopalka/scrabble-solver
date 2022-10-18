import cv2 as cv
import numpy as np

from ..utils import draw_scrabble_grid_on_board, print_image


def _count_black_pixels(image, threshold=20):
    count = 0
    for row in image:
        for pixel in row:
            if pixel < threshold:
                count += 1
    return count


def _percent_of_black_on_field(image):
    black_count = _count_black_pixels(image)
    field_size = image.shape[0] * image.shape[1]
    return (black_count / field_size) * 100


def _map_to_boolean(confidence):
    return confidence > 10


def _preprocess_image(image):
    image = cv.cvtColor(image, cv.COLOR_BGR2GRAY)
    image = cv.bilateralFilter(image, 25, 80, 80)
    image = cv.adaptiveThreshold(image, 255, cv.ADAPTIVE_THRESH_MEAN_C, cv.THRESH_BINARY, 31, 20)
    image = cv.dilate(image, np.ones((3, 3), np.uint8))
    return image


def _check_fields_for_letter(board):
    confidence_matrix = np.zeros((15, 15), float)
    for x in range(0, 15):
        for y in range(0, 15):
            field = board.get_field(x, y, field_margin=-board.field_size // 4)
            confidence_matrix[x, y] = _percent_of_black_on_field(field)

    return _map_to_boolean(confidence_matrix)


def get_letters_mask(board, debug=False):
    board.image = _preprocess_image(board.image)
    if debug: print_image('1. Preprocessed image with grid', draw_scrabble_grid_on_board(board))

    mask = _check_fields_for_letter(board)
    if debug: print_image('2. Found letters', draw_scrabble_grid_on_board(board, mask))

    return mask
