import cv2 as cv
from statistics import stdev, mean

from src.libs.corners_detection import find_corners
from src.libs.util.board import *
import numpy as np


def mark_found_letters(board, mask):
    image = board.get_board()
    for x in range(0, 15):
        for y in range(0, 15):
            (w, h, size) = board.get_field_coords(x, y)
            if mask[x, y]: cv.rectangle(image, (w, h), (w + size, h + size), green, 8)
    return image


def preprocess_image(image):
    image = cv.cvtColor(image, cv.COLOR_BGR2GRAY)
    image = cv.bilateralFilter(image, 25, 80, 80)
    image = cv.adaptiveThreshold(image, 255, cv.ADAPTIVE_THRESH_MEAN_C, cv.THRESH_BINARY, 31, 20)
    image = cv.dilate(image, np.ones((3, 3), np.uint8))
    return image


def count_black_pixels(image, threshold=20):
    count = 0
    for row in image:
        for pixel in row:
            if pixel < threshold:
                count += 1
    return count


def percent_of_black(field):
    black_count = count_black_pixels(field)
    print(field.shape)
    field_size = field.shape[0] * field.shape[1]

    return (black_count / field_size) * 100


def is_letter(f):
    return f > 10


def calculate_letters_mask(board, debug=False):
    board.preprocess_image(preprocess_image)
    if debug: print_image('1. Preprocessed image', board.get_board_with_grid(preprocessed=True))

    confidence_matrix = np.zeros((15, 15), float)
    for x in range(0, 15):
        for y in range(0, 15):
            field = board.get_field((x, y), field_margin=-board.field_size // 4, preprocessed=True)
            confidence_matrix[x, y] = percent_of_black(field)

    mask = is_letter(confidence_matrix)
    if debug:
        print(confidence_matrix.astype(int))
        print_image('2. Found letters', mark_found_letters(board, mask))
    return mask
