from methods import *
import cv2 as cv

capture = cv.VideoCapture(2)
if not capture.isOpened():
    exit(0)

while True:
    isTrue, frame = capture.read()
    cv.imshow('frame', frame)


    if cv.waitKey(20) & 0xFF == ord('d'):
        break

capture.release()
cv.destroyAllWindows()
