# 微实例7.7
from PIL import Image
from PIL import ImageEnhance
im = Image.open('./实验/实验7/birdnest.jpg')
om = ImageEnhance.Contrast(im)
om.enhance(20).save('birdnestEnContrast.jpg')
