from libs.methods import *


def calculate_mser(image):
    board_gray = cv.cvtColor(image, cv.COLOR_BGR2GRAY)
    board_gray = cv.equalizeHist(board_gray)
    board_gray = cv.equalizeHist(board_gray)

    max_tile_area = (image.shape[0] * image.shape[1]) // (15 * 15)
    MSER = cv.MSER_create(max_area=max_tile_area, delta=20)

    regions, _ = MSER.detectRegions(board_gray)
    print(len(regions))
    hulls = [cv.convexHull(p.reshape(-1, 1, 2)) for p in regions]
    cv.polylines(image, hulls, 1, (0, 0, 255), 2)

    return image


board, config = loader('../resources/boards/red')

capture = cv.VideoCapture(2)
if not capture.isOpened():
    exit(0)

while True:
    isTrue, frame = capture.read()
    cv.imshow('frame', frame)

    #frame = find_board(frame, resize(board, 1 / 7), fraction=1, debug=True, debug_resize_factor=1)

    frame = calculate_mser(frame)
    cv.imshow('match', frame)

    if cv.waitKey(20) & 0xFF == ord('d'):
        break

capture.release()
cv.destroyAllWindows()
