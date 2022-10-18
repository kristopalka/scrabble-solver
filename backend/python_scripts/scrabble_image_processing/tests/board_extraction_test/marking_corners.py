import json

from scrabble_image_processing.lib import *

# -------------------------------------------
print('On each picture click on four corners or board clockwise. Then, click "N" key. To close, click ESC')
path = '/home/krist/Projects/Scrabble-Solver/image detection/resources/photos/red/'
marks_folder = '.marks/'
# -------------------------------------------

scale_fraction = None
image = None
image_name = None
click_counter = 0
image_points = []
filenames = ["020.jpg", "016.jpg"]


# filenames = [file for file in os.listdir(path) if file.endswith(".jpg")]


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
    json_file = path + marks_folder + image_name + '.json'
    with open(json_file, 'w') as outfile:
        json_string = json.dumps(obj)
        outfile.write(json_string)


def change_image_size(img, lower_dim):
    ratio = lower_dim / min(img.shape[:2])
    img = resize(img, ratio)
    return img, ratio


for filename in filenames:
    original = cv.imread(path + filename)
    image, scale_fraction = change_image_size(original, 800)
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
