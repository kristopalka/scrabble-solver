from libs.methods import *
from tools import *

board = cv.imread('resources/photos/red/board.jpg')
board = resize(board, 1/7)


# camera
capture = cv.VideoCapture(3)
if not capture.isOpened():
    print('camera dont work')
    exit(0)

while True:
    isTrue, frame = capture.read()
    cv.imshow('frame', frame)

    frame = find_board(board, frame)
    cv.imshow('match', frame)

    if cv.waitKey(20) & 0xFF == ord('d'):
        break

capture.release()
cv.destroyAllWindows()
