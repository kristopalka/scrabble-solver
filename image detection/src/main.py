from libs.corner_extraction import *


photo = cv.imread('../resources/photos/red/020.jpg')

corners = get_corners_from_image(photo, debug=True)
photo = draw_points(photo, corners, color=(0, 255, 0), radius=20, thickness=-1)

cv.imshow('main', resize(photo, 1/4))

cv.waitKey(0)
cv.destroyAllWindows()
