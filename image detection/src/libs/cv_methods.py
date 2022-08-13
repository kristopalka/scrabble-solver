import cv2 as cv
import numpy as np


def resize(img, ratio=1.0):
    assert img is not None
    x = int(round(img.shape[1] * ratio))
    y = int(round(img.shape[0] * ratio))
    return cv.resize(img, (x, y))


def standardize_size(image, lower_dim=1000):
    ratio = lower_dim / min(image.shape[:2])
    image = resize(image, ratio)

    return image, ratio


def restore_ratio(points, ratio):
    restored_points = []
    for point in points:
        restored = [int(point[0] // ratio), int(point[1] // ratio)]
        restored_points.append(restored)
    return restored_points


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


def draw_lines(image, lines):
    for line in lines:
        rho, theta = line[0]
        a, b = np.cos(theta), np.sin(theta)
        x0, y0 = a * rho, b * rho
        x1 = int(x0 + 1000 * (-b))
        y1 = int(y0 + 1000 * a)
        x2 = int(x0 - 1000 * (-b))
        y2 = int(y0 - 1000 * a)
        cv.line(image, (x1, y1), (x2, y2), (0, 255, 0), 1)

    return image


def draw_points(image, points, color=(0, 0, 255), radius=5, thickness=2):
    for point in points:
        cv.circle(image, point, radius, color, thickness)
    return image
