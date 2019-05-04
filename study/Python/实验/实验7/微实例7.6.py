# 微实例7.6
from PIL import Image
from PIL import ImageFilter
im = Image.open('birdnest.jpg')
om = im.filter(ImageFilter.CONTOUR)
om.save('birdnestContour.jpg')
