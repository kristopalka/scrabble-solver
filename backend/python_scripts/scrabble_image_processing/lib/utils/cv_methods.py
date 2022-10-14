import cv2 as cv

red = (0, 0, 255)
green = (0, 255, 0)
blue = (255, 0, 0)


def resize(img, ratio=1.0):
    assert img is not None
    x = int(round(img.shape[1] * ratio))
    y = int(round(img.shape[0] * ratio))
    return cv.resize(img, (x, y))


def load_image(path):
    image = cv.imread(path)
    assert image is not None, "Photo is null!"
    return image


def save_image(path, image):
    assert image is not None, "Photo is null!"
    cv.imwrite(path, image)


def print_image(name, image, max_h=1000, max_w=1800):
    (h, w) = image.shape[:2]
    ratio_h = max_h / h
    ratio_w = max_w / w

    ratio = min(ratio_w, ratio_h)
    image = resize(image, ratio)

    cv.imshow(name, image)
