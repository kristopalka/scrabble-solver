import os
import json
from libs.cv_utils import *
import cv2 as cv

print('On each picture click on four corners or board clockwise. Then, click "N" key. To close, click ESC')
path = '../resources/photos/red/'
scale_fraction = 1 / 4
image = None
image_name = ''
click_counter = 0
image_points = []


def click_event(event, x, y, flags, params):
    global click_counter
    if event == cv.EVENT_LBUTTONDOWN and click_counter < 4:
        click_counter = click_counter + 1

        print(x, y)
        cv.circle(image, (x, y), 5, (255, 0, 0), 2)
        cv.imshow(image_name, image)

        real_x, real_y = int(x // scale_fraction), int(y // scale_fraction)
        image_points.append((real_x, real_y))


def save_result():
    assert len(image_points) <= 4

    obj = {
        "image": image_name,
        "points": image_points
    }
    json_name = path + 'data/' + image_name + '.json'
    with open(json_name, 'w') as outfile:
        json_string = json.dumps(obj)
        outfile.write(json_string)


for filename in os.listdir(path):
    if filename.endswith(".jpg"):
        original = cv.imread(path + filename)
        image = resize(original, scale_fraction)
        image_name = filename
        click_counter = 0
        image_points = []

        print('Image:', image_name)
        cv.imshow(image_name, image)
        cv.setMouseCallback(image_name, click_event)

        while True:
            k = cv.waitKey(0)
            if k == ord('n'):
                save_result()
                cv.destroyWindow(image_name)
                break
            if k == 27:
                cv.destroyAllWindows()
                exit()
