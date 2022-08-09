import cv2

from libs.methods import *
import math
import statistics


def calculate_region_dim(region):
    max_x = region[0][0]
    min_x = region[0][0]
    max_y = region[0][1]
    min_y = region[0][1]
    for point in region:
        if point[0] > max_x: max_x = point[0]
        if point[0] < min_x: min_x = point[0]
        if point[1] > max_y: max_y = point[1]
        if point[1] < min_y: min_y = point[1]
    x = max_x - min_x
    y = max_y - min_y

    return x, y


def get_good_regions(regions, max_differ=0.2):
    regions_good = []
    for region in regions:
        x, y = calculate_region_dim(region)
        differ = math.fabs(x - y)
        if differ < x * max_differ and differ < y * max_differ:
            regions_good.append(region)
    return regions_good


def calculate_mser(image):
    board_gray = cv.cvtColor(image, cv.COLOR_BGR2GRAY)
    board_gray = cv.equalizeHist(board_gray)

    max_tile_area = (image.shape[0] * image.shape[1]) // (15 * (15 + 5))
    MSER = cv.MSER_create(max_area=max_tile_area, delta=20, max_variation=0.1)

    regions, _ = MSER.detectRegions(board_gray)
    good_regions = get_good_regions(regions)
    print(len(regions))
    print(len(good_regions))

    hulls = [cv.convexHull(p.reshape(-1, 1, 2)) for p in good_regions]
    cv.polylines(image, hulls, 1, (0, 0, 255), 2)

    return regions, image


def imfill(image):  # filling holes https://learnopencv.com/filling-holes-in-an-image-using-opencv-python-c/
    # todo repairdis
    th, im_th = cv2.threshold(image, 220, 255, cv2.THRESH_BINARY_INV)
    im_floodfill = im_th.copy()
    h, w = im_th.shape[:2]
    mask = np.zeros((h+2, w+2), np.uint8)
    cv2.floodFill(im_floodfill, mask, (0, 0), 255)
    im_floodfill_inv = cv2.bitwise_not(im_floodfill)
    return im_th | im_floodfill_inv


frame = cv.imread('../resources/photos/red/_9.jpg')  # _1, _6, _8
frame = resize(frame, 1 / 4)

regions, frame_with_regions = calculate_mser(frame)
cv.imshow('frame_with_regions', frame_with_regions)

# edges = cv.Canny(frame, 100, 200)
# dialated = cv.morphologyEx(edges, cv2.MORPH_CLOSE, kernel=np.ones((5, 5), np.uint8))
# im = imfill(frame)
# cv.imshow('edges', im)

cv.waitKey(0)
cv.destroyAllWindows()
