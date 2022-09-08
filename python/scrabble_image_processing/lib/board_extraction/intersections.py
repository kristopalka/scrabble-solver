import numpy as np


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

