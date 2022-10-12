import numpy as np
import cv2 as cv

from .cv_methods import green, blue


def draw_hough_lines(img, lines, color=blue, thickness=1):
    _img = img.copy()
    for line in lines:
        rho, theta = line[0]
        a, b = np.cos(theta), np.sin(theta)
        x0, y0 = a * rho, b * rho
        x1 = int(x0 + 1000 * (-b))
        y1 = int(y0 + 1000 * a)
        x2 = int(x0 - 1000 * (-b))
        y2 = int(y0 - 1000 * a)
        cv.line(_img, (x1, y1), (x2, y2), color, thickness)

    return _img


def draw_points(img, points, color=blue, radius=5, thickness=2):
    _img = img.copy()
    if len(_img.shape) == 2: _img = cv.cvtColor(_img, cv.COLOR_GRAY2BGR)

    for point in points:
        cv.circle(_img, point, radius, color, thickness)
    return _img


def draw_scrabble_grid_on_board(board, color=blue, thickness=2):
    _img = board.image.copy()
    if len(_img.shape) == 2: _img = cv.cvtColor(_img, cv.COLOR_GRAY2BGR)

    (top, left, bottom, right) = (board.margin, board.margin,
                                  _img.shape[0] - board.margin, _img.shape[1] - board.margin)

    for width in np.linspace(left, right, 16):
        width_int = round(width)
        cv.line(_img, (width_int, top), (width_int, bottom), color=color, thickness=thickness)

    for height in np.linspace(top, bottom, 16):
        height_int = round(height)
        cv.line(_img, (left, height_int), (right, height_int), color=color, thickness=thickness)

    return _img


def draw_mask_on_board(board, mask):
    _img = board.image.copy()
    if len(_img.shape) == 2: _img = cv.cvtColor(_img, cv.COLOR_GRAY2BGR)

    for x in range(0, 15):
        for y in range(0, 15):
            (w, h, size) = board.get_field_coords(x, y)
            if mask[x, y]: cv.rectangle(_img, (w, h), (w + size, h + size), green, 8)
    return _img


def draw_grid_letters_and_confidences_on_board(board, letters, confidences):
    _img = draw_scrabble_grid_on_board(board)
    if len(_img.shape) == 2: _img = cv.cvtColor(_img, cv.COLOR_GRAY2BGR)

    for x in range(0, 15):
        for y in range(0, 15):
            if letters[x, y] != ' ':
                (w, h, size) = board.get_field_coords(x, y)
                cv.putText(_img, letters[x, y], (w, h + size - 40), cv.FONT_HERSHEY_TRIPLEX, 3, green, 3)
                cv.putText(_img, str(confidences[x, y]), (w, h + size - 10), cv.FONT_HERSHEY_TRIPLEX, 1, green, 2)
    return _img
