from pytesseract import Output
import pytesseract as tr
import easyocr.easyocr as ocr
from skimage import morphology



from src.libs.corners_detection import *
from src.libs.letters_mask import calculate_letters_mask
from src.libs.util.board import *



photo = load_image('red/008.jpg')
corners = find_corners(photo, debug=False)
board = Board(photo, corners, margin=50)

field = board.get_field((14, 0))


print_image('Letters', field)



cv.waitKey(0)
cv.destroyAllWindows()
