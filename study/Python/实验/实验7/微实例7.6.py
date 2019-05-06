# 微实例7.6
from PIL import Image
from PIL import ImageFilter
im = Image.open('./实验/实验7/birdnest.jpg')
om = im.filter(ImageFilter.CONTOUR)
om.save('birdnestContour.jpg')
