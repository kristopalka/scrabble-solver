import numpy as np
import cv2 as cv
from math import dist


def get_intersection_point(line1, line2):
    rho1, theta1 = line1[0]
    rho2, theta2 = line2[0]
    A = np.array([[np.cos(theta1), np.sin(theta1)], [np.cos(theta2), np.sin(theta2)]])
    b = np.array([[rho1], [rho2]])
    try:
        x0, y0 = np.linalg.solve(A, b)
    except np.linalg.LinAlgError:
        return None  # None if parallel
    return [int(np.round(x0)), int(np.round(y0))]


def point_outside(point, shape):
    (x, y) = (point[0], point[1])
    (h, w) = shape
    return x < 0 or x > w or y < 0 or y > h


def too_low_theta_diff(line1, line2, min_angle=np.pi * 0.2):
    rho1, theta1 = line1[0]
    rho2, theta2 = line2[0]

    diff = abs(theta1 - theta2)
    return diff < min_angle or diff > np.pi - min_angle


def find_good_intersections(lines, shape):
    intersections = []

    for i in range(0, len(lines)):
        for j in range(i + 1, len(lines)):
            if too_low_theta_diff(lines[i], lines[j]): continue

            point = get_intersection_point(lines[i], lines[j])
            if point is None: continue
            if point_outside(point, shape): continue

            intersections.append(point)
    return intersections


def center_of_points(points):
    length = len(points)
    sum_x = sum([p[0] for p in points])
    sum_y = sum([p[1] for p in points])
    return int(sum_x / length), int(sum_y / length)


def group_points(points, shape):
    (h, w) = shape
    corner_points = [[], [], [], []]

    for point in points:
        distances = [dist(point, (0, 0)), dist(point, (w, 0)), dist(point, (0, h)), dist(point, (w, h))]
        index_min = min(range(len(distances)), key=distances.__getitem__)
        corner_points[index_min].append(point)

    corner_centers = []
    for points in corner_points:
        center = center_of_points(points)
        corner_centers.append(center)

    print(corner_centers)
    return corner_centers


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


def draw_points(image, intersections, color=(0, 0, 255), radius=1, thickness=2):
    for intersection in intersections:
        cv.circle(image, intersection, radius, color, thickness)

    return image
