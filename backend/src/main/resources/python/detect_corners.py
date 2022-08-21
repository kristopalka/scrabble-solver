import sys
import cv2 as cv
from importlib.machinery import SourceFileLoader

library_path = "/home/krist/Projects/Scrabble-Solver/python/scrabble_image_processing/__init__.py"
sip = SourceFileLoader("scrabble_image_processing", library_path).load_module()


path = sys.argv[1]

print(f'File path: {path}')
image = sip.load_image(path)
corners = sip.BoardExtractor(image).set_debug(False).process().get_corners()

print(corners)



cv.waitKey(0)
cv.destroyAllWindows()
