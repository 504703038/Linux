# 微实例7.5
from PIL import Image
im = Image.open('./实验/实验7/birdnest.jpg')
r, g, b = im.split()
om = Image.merge("RGB", (b, g, r))
om.save('birdnestBGR.jpg')
