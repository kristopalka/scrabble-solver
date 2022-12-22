import os

from scrabble_image_processing.lib import *
# -------------------------------------------
from scrabble_image_processing.lib.board_extraction.grouping_points import sort_points_clockwise

print('On each picture click on four corners or board clockwise. Then, click "N" key. To close, click ESC')
path = '/home/krist/Downloads/inżynierka/rozpoznawanie zdjęcia/'
extracted_folder = 'extracted/'
# -------------------------------------------

scale_fraction = None
image = None
image_name = None
click_counter = 0
image_points = []

#filenames = ["002.jpg"]
filenames = [file for file in os.listdir(path) if file.endswith(".jpg")]


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

    original_image = cv.imread(path + image_name)
    board_photo = get_board(original_image, image_points)

    cv.imwrite(path + extracted_folder + image_name, board_photo)



def change_image_size(img, lower_dim):
    ratio = lower_dim / min(img.shape[:2])
    img = resize(img, ratio)
    return img, ratio


def get_board(img, corners):
    corners = np.float32(corners)
    (x, y), (width, height), angle = cv.minAreaRect(corners)
    image_size = int(min(width, height))
    dest = [[0, 0], [image_size, 0], [image_size, image_size], [0, image_size]]

    src = sort_points_clockwise(np.float32(corners))
    dest = sort_points_clockwise(np.float32(dest))

    matrix = cv.getPerspectiveTransform(src, dest)
    return cv.warpPerspective(img, matrix, (image_size, image_size))


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
