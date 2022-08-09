from libs.methods import *
import cv2 as cv
import math



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


frame = cv.imread('../resources/photos/red/_1.jpg')  # _1, _6, _8
frame = resize(frame, 1 / 4)

regions, frame_with_regions = calculate_mser(frame)
cv.imshow('frame_with_regions', frame_with_regions)


cv.waitKey(0)
cv.destroyAllWindows()
