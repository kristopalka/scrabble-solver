import cv2 as cv
import numpy as np


def resize(img, ratio=1.0):
    assert img is not None
    x = int(round(img.shape[1] * ratio))
    y = int(round(img.shape[0] * ratio))
    return cv.resize(img, (x, y))


def normalize_size(image, lower_dim=1000):
    ratio = lower_dim / min(image.shape[:2])
    image = resize(image, ratio)

    return image, ratio


def restore_ratio(points, ratio):
    restored_points = []
    for point in points:
        restored = [int(point[0] // ratio), int(point[1] // ratio)]
        restored_points.append(restored)
    return np.array(restored_points)


def load_image(path, prefix="../resources/photos/"):
    photo = cv.imread(prefix + path)
    assert photo is not None, "Photo is null"
    return photo


def print_image(name, image, max_h=1000, max_w=1800):
    (h, w) = image.shape[:2]
    ratio_h = max_h / h
    ratio_w = max_w / w

    ratio = min(ratio_w, ratio_h)
    image = resize(image, ratio)

    cv.imshow(name, image)


def fill_all_contours(img):
    contours, hierarchy = cv.findContours(img, cv.RETR_CCOMP, cv.CHAIN_APPROX_SIMPLE)
    for contour in contours:
        cv.drawContours(img, [contour], 0, 255, -1)
    return img


def find_largest_contour(img):
    largest_area = 0
    largest_index = 0

    contours, hierarchy = cv.findContours(img, cv.RETR_CCOMP, cv.CHAIN_APPROX_SIMPLE)
    for i, contour in enumerate(contours):
        area = cv.contourArea(contour)
        if area > largest_area:
            largest_area = area
            largest_index = i

    return contours[largest_index]
