import json
import os
from libs.corner_extraction import *
from math import dist
from statistics import mean
import pandas as pd
import numpy as np

path = '../resources/photos/red/'
marks_folder = 'marks/'
debug = False
errors = []


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
        return get_corners_from_image(img, debug=False)
    except Exception:
        print('Error occurs on photo', image_name)


for image_name in os.listdir(path):
    if image_name.endswith(".jpg"):
        corners_correct = load_marks(image_name)
        corners = try_to_perform_algorithm(image_name)


        error_distances = calculate_distances(corners_correct, corners)
        error_mean = mean(error_distances)

        error_data = error_distances
        error_data.append(error_mean)
        errors.append(error_data)

        if debug and error_mean > 100:
            print(image_name, error_mean, error_distances)

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


df = pd.DataFrame(np.array(errors), columns={'top_left', 'top_right', 'bottom_right', 'bottom_left', 'mean'})
description = df.describe(percentiles=[])
print(description)
