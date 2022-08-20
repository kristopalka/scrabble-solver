import sys
import cv2 as cv
from libs.corners_detection import find_corners

path = sys.argv[1][1:-1]
photo = cv.imread(path)
assert photo is not None, f"Photo is null: {path}"

corners = find_corners(photo, debug=False)
print(corners)

cv.waitKey(0)
cv.destroyAllWindows()
