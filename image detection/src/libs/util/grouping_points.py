from math import dist
import numpy as np


def center_of_points(points):
    length = len(points)
    assert length != 0

    sum_x = sum([p[0] for p in points])
    sum_y = sum([p[1] for p in points])
    return int(sum_x / length), int(sum_y / length)


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


def group_and_get_centers(points, shape):
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

    return corner_centers
