import json
from math import trunc
import cv2 as cv
import numpy as np


def resize(img, fraction=1.0):
    assert img is not None
    x = int(round(img.shape[1] * fraction))
    y = int(round(img.shape[0] * fraction))
    return cv.resize(img, (x, y))


def loader(path):
    board = cv.imread(path + '/board.jpg')

    file = open(path + '/config.json')
    config = json.load(file)
    file.close()
    return board, config


def find_board(frame_original, board_original, fraction=1 / 3):
    board = resize(board_original, fraction)
    frame = resize(frame_original, fraction)

    board_gray = cv.cvtColor(board, cv.COLOR_BGR2GRAY)
    frame_gray = cv.cvtColor(frame, cv.COLOR_BGR2GRAY)

    # find descryptors
    BRISK = cv.BRISK_create()
    keypoints_board, descriptors_board = BRISK.detectAndCompute(board, None)
    keypoints_frame, descriptors_frame = BRISK.detectAndCompute(frame, None)

    if descriptors_board is None or descriptors_frame is None:
        print("Error: can not find descriptors. Photo may be to dark")
        return None

    # find matches betwean descryptors
    bf = cv.BFMatcher(normType=cv.NORM_HAMMING, crossCheck=True)

    matches = bf.match(descriptors_board, descriptors_frame)
    matches = sorted(matches, key=lambda x: x.distance)

    if len(matches) < 100:
        print('Error: not enough matches (' + str(len(matches)) + ')')
        return None

    # find homography
    board_pts = np.float32([keypoints_board[m.queryIdx].pt for m in matches]).reshape(-1, 1, 2)
    frame_pts = np.float32([keypoints_frame[m.trainIdx].pt for m in matches]).reshape(-1, 1, 2)

    board_pts_original = board_pts / fraction  # /factor to get output from original board img
    frame_pts_original = frame_pts / fraction  # /factor to get output from original (not scaled) image

    M_original, mask_original = cv.findHomography(frame_pts_original, board_pts_original, cv.RANSAC, 5.0)

    (h, w) = board_original.shape[:2]
    return cv.warpPerspective(frame_original, M_original, (w, h))


def draw_lines(photo, config, color=(0, 255, 0), thickness=2):
    (top, left, bottom, right) = (config['top'], config['left'], config['bottom'], config['right'])
    print('Coordinates:', top, left, bottom, right)
    field_width = (bottom - top) / 15
    field_height = (left - right) / 15

    points = []
    for width in np.linspace(left, right, 16):
        width_int = round(width)
        photo = cv.line(photo, (width_int, top), (width_int, bottom), color, thickness=thickness)

    for height in np.linspace(top, bottom, 16):
        height_int = round(height)
        photo = cv.line(photo, (left, height_int), (right, height_int), color, thickness=thickness)

    return photo
