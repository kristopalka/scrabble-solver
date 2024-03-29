import json
import os
import time
from math import dist
from statistics import mean

import pandas as pd

from scrabble_image_processing.lib import *
from scrabble_image_processing.lib.board_extraction.grouping_points import sort_points_clockwise

# -------------------------------------------
path = '/home/krist/Downloads/images/'
marks_folder = '.marks/'
debug = False
# -------------------------------------------


if debug: print("correct=green, detected=blue")
data_image_name = []
data_board_edge_length = []
data_errors = []
data_mean_errors = []
data_processing_times = []


def load_marks(image_name):
    json_file = path + marks_folder + image_name + '.json'
    with open(json_file, 'r') as infile:
        marks_json = json.load(infile)
        points = marks_json['points']
        return sort_points_clockwise(points)


def calculate_distances(array_a, array_b):
    array_a = sort_points_clockwise(array_a)
    array_b = sort_points_clockwise(array_b)
    distances = []
    assert len(array_a) == len(array_b)
    for i in range(len(array_b)):
        distance = dist(array_a[i], array_b[i])
        distances.append(distance)
    return distances


def try_to_perform_algorithm(image_name):
    try:
        image = cv.imread(path + image_name)
        start = time.time()

        corners = BoardExtractor(image).find_corners().get_corners()

        data_processing_times.append(time.time() - start)
        return corners
    except Exception as e:
        print('Error occurs on photo', image_name, e)
        return None


def debugging(image_name, corners, corners_correct, errors, error_mean):
    if error_mean > 100:
        print(image_name, error_mean, errors)

        image = draw_points(cv.imread(path + image_name), corners_correct, color=green, thickness=-1, radius=20)
        image = draw_points(image, corners, color=blue, thickness=-1, radius=10)
        cv.imshow(image_name, resize(image, 1 / 4))

        while True:
            k = cv.waitKey(0)
            if k == ord('n'):
                break
            if k == 27:
                exit()
        cv.destroyAllWindows()


def calculate_mean_board_length(corners):
    return (dist(corners[0], corners[1]) + dist(corners[1], corners[2]) +
            dist(corners[2], corners[3]) + dist(corners[3], corners[0])) / 4


for image_name in os.listdir(path):
    if image_name.endswith(".jpg"):
        corners_correct = load_marks(image_name)
        corners = try_to_perform_algorithm(image_name)
        if corners is not None:
            errors = calculate_distances(corners_correct, corners)
            data_board_edge_length.append(calculate_mean_board_length(corners_correct))
            error_mean = mean(errors)

            data_image_name.append(image_name)
            data_errors.append(errors)
            data_mean_errors.append(error_mean)

            if debug: debugging(image_name, corners, corners_correct, errors, error_mean)

array_data_errors = np.array(data_errors)
df = pd.DataFrame()
df['image_name'] = data_image_name
df['top_left'] = np.array(array_data_errors[:, 0]).astype(int)
df['top_right'] = np.array(array_data_errors[:, 1]).astype(int)
df['bottom_right'] = np.array(array_data_errors[:, 2]).astype(int)
df['bottom_left'] = np.array(array_data_errors[:, 3]).astype(int)
df['mean'] = np.array(data_mean_errors).astype(int)
df['time'] = data_processing_times
description = df.describe(percentiles=[]).loc[['mean', 'std', '50%', 'min', 'max']]

print('------------------------------------ DATA ------------------------------------')
print(df)
print('------------------------------------ STAT ------------------------------------')
print(description)

print('------------------------------------ BOARD EDGE LENGTH ------------------------------------')
print(data_board_edge_length)
print('Mean', mean(data_board_edge_length))
print('Max', max(data_board_edge_length))
print('Min', min(data_board_edge_length))
