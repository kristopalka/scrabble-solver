from math import dist

import numpy as np


def sort_points_clockwise(points):
    points = np.array(points)
    center = np.mean(points, axis=0)

    cyclic_pts = [
        points[np.where(np.logical_and(points[:, 0] < center[0], points[:, 1] < center[1]))[0][0], :],
        points[np.where(np.logical_and(points[:, 0] > center[0], points[:, 1] < center[1]))[0][0], :],
        points[np.where(np.logical_and(points[:, 0] > center[0], points[:, 1] > center[1]))[0][0], :],
        points[np.where(np.logical_and(points[:, 0] < center[0], points[:, 1] > center[1]))[0][0], :]
    ]
    return np.array(cyclic_pts)


def find_four_corners(points, shape):
    (h, w) = shape
    corner_points = [[], [], [], []]

    for point in points:
        distances = [dist(point, (0, 0)), dist(point, (w, 0)), dist(point, (0, h)), dist(point, (w, h))]
        index_min = min(range(len(distances)), key=distances.__getitem__)
        corner_points[index_min].append(point)

    corner_centers = []
    for points in corner_points:
        center = np.mean(points, axis=0).astype(int)
        corner_centers.append(center)

    return corner_centers
