from libs.utils import *
import json
import cv2 as cv
import numpy as np


def loader(path):
    board = cv.imread(path + '/board.jpg')

    file = open(path + '/config.json')
    config = json.load(file)
    file.close()
    return board, config


def draw_lines(photo, config, color=(0, 255, 0), thickness=2):
    (top, left, bottom, right) = (config['top'], config['left'], config['bottom'], config['right'])

    for width in np.linspace(left, right, 16):
        width_int = round(width)
        photo = cv.line(photo, (width_int, top), (width_int, bottom), color, thickness=thickness)

    for height in np.linspace(top, bottom, 16):
        height_int = round(height)
        photo = cv.line(photo, (left, height_int), (right, height_int), color, thickness=thickness)

    return photo


def find_corner_harris(image):
    image_gray = cv.cvtColor(image, cv.COLOR_BGR2GRAY)

    mask = np.zeros_like(image_gray)
    dst = cv.cornerHarris(image_gray, 10, 3, 0.04)
    dst = cv.dilate(dst, None, iterations=5)
    image[dst > 0.01 * dst.max()] = [0, 0, 255]
    mask[dst > 0.01 * dst.max()] = 255

    return image, mask


def find_board(frame_original, board_original, fraction=1 / 2, align_image_sizes=False, debug=False,
               debug_resize_factor=1):
    if align_image_sizes:
        ratio = min(frame_original.shape[:2]) / min(board_original.shape[:2])
    else:
        ratio = 1

    board = resize(board_original, fraction * ratio)
    frame = resize(frame_original, fraction)

    if debug:
        print('Board shape:', board.shape)
        print('Frame shape:', frame.shape)

    board_gray = cv.cvtColor(board, cv.COLOR_BGR2GRAY)
    frame_gray = cv.cvtColor(frame, cv.COLOR_BGR2GRAY)

    # find descriptors
    BRISK = cv.BRISK_create()
    keypoints_board, descriptors_board = BRISK.detectAndCompute(board_gray, None)
    keypoints_frame, descriptors_frame = BRISK.detectAndCompute(frame_gray, None)

    if descriptors_board is None or descriptors_frame is None:
        print("Error: can not find descriptors. Photo may be to dark")
        return None

    # find matches between descriptors
    bf = cv.BFMatcher(normType=cv.NORM_HAMMING, crossCheck=True)
    matches = bf.match(descriptors_board, descriptors_frame)

    # matches = sorted(matches, key=lambda x: x.distance)

    if debug:
        print('Matches:', len(matches))

    if len(matches) < 20:
        print('Too low matches')
        return

    # find homography
    board_pts = np.float32([keypoints_board[m.queryIdx].pt for m in matches]).reshape(-1, 1, 2)
    frame_pts = np.float32([keypoints_frame[m.trainIdx].pt for m in matches]).reshape(-1, 1, 2)

    if debug:
        M, mask = cv.findHomography(board_pts, frame_pts, cv.RANSAC, 100)
        h, w = board.shape[:2]
        pts = np.float32([[0, 0], [0, h - 1], [w - 1, h - 1], [w - 1, 0]]).reshape(-1, 1, 2)
        cv.polylines(frame, [np.int32(cv.perspectiveTransform(pts, M))], True, 255, 3, cv.LINE_AA)

        draw_params = dict(matchColor=(0, 255, 0), singlePointColor=None, flags=2, matchesMask=mask.ravel().tolist())
        debugImage = cv.drawMatches(board, keypoints_board, frame, keypoints_frame, matches, None, **draw_params)
        cv.imshow('Debug matches', resize(debugImage, debug_resize_factor))

        draw_params = dict(matchColor=(255, 0, 0), singlePointColor=None, flags=2)
        debugImage = cv.drawMatches(board, keypoints_board, frame, keypoints_frame, matches, None, **draw_params)
        cv.imshow('Debug matches all', resize(debugImage, debug_resize_factor))

    board_pts_original = board_pts / (fraction * ratio)  # /factor to get output from original board img
    frame_pts_original = frame_pts / fraction  # /factor to get output from original (not scaled) image

    M_original, mask_original = cv.findHomography(frame_pts_original, board_pts_original, cv.RANSAC, 100)

    (h, w) = board_original.shape[:2]
    return cv.warpPerspective(frame_original, M_original, (w, h))


frame = cv.imread('resources/photos/red/_1.jpg')  # _1, _6, _8
board, config = loader('resources/boards/red')

match = find_board(frame, board, debug=True, align_image_sizes=True, debug_resize_factor=1 / 2)

lines = draw_lines(match, config)

cv.imshow('result', resize(lines, 1 / 3))
cv.waitKey(0)
cv.destroyAllWindows()