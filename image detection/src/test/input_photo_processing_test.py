import json
import os
from src.libs.input_photo_processing import *
from math import dist
from statistics import mean
import pandas as pd
import numpy as np
import time

path = '../../resources/photos/red/'
marks_folder = 'marks/'
debug = False
data_image_name = []
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
    distances = []
    assert len(array_a) == len(array_b)
    for i in range(len(array_b)):
        distance = dist(array_a[i], array_b[i])
        distances.append(distance)
    return distances


def try_to_perform_algorithm(image_name):
    try:
        img = cv.imread(path + image_name)
        start = time.time()
        corners = find_corners(img, debug=False)
        end = time.time()

        data_processing_times.append(end - start)
        return corners
    except Exception:
        print('Error occurs on photo', image_name, )
        return None


def debugging(image_name, corners, corners_correct, errors, error_mean):
    if error_mean > 100:
        print(image_name, error_mean, errors)

        image = draw_points(cv.imread(path + image_name), corners_correct, color=(0, 255, 0), thickness=-1, radius=20)
        image = draw_points(image, corners, color=(255, 0, 0), thickness=-1, radius=10)
        cv.imshow(image_name, resize(image, 1 / 4))

        while True:
            k = cv.waitKey(0)
            if k == ord('n'):
                break
            if k == 27:
                exit()
        cv.destroyAllWindows()


for image_name in os.listdir(path):
    if image_name.endswith(".jpg"):
        corners_correct = load_marks(image_name)
        corners = try_to_perform_algorithm(image_name)
        if corners is not None:
            errors = calculate_distances(corners_correct, corners)
            error_mean = mean(errors)

            data_image_name.append(image_name)
            data_errors.append(errors)
            data_mean_errors.append(error_mean)

            if debug: debugging(image_name, corners, corners_correct, errors, error_mean)


array_data_errors = np.array(data_errors)
df = pd.DataFrame()
df['image_name'] = data_image_name
df['top_left'] = array_data_errors[:, 0]
df['top_right'] = array_data_errors[:, 1]
df['bottom_right'] = array_data_errors[:, 2]
df['bottom_left'] = array_data_errors[:, 3]
df['mean'] = data_mean_errors
df['time'] = data_processing_times
description = df.describe(percentiles=[]).loc[['mean', 'std', '50%', 'min', 'max']]

print('------------------------------------ DATA ------------------------------------')
print(df)
print('------------------------------------ STAT ------------------------------------')
print(description)
