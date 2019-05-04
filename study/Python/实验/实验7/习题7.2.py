# 习题7.2
from PIL import Image
import math
im = Image.open('fcity.jpg')
w, h = im.size
x = math.sqrt(4*10*1024*w/h)
y = x*h/w
om = im.resize((int(x), int(y)), Image.NEAREST)
om.save('test.jpg')