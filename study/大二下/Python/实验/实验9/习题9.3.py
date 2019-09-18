# 习题9.3
from PIL import Image
import numpy as np
vec_el = np.pi / 2.2
vec_az = np.pi / 4.
depth = 2.
im = Image.open('./实验/实验9/fcity.jpg').convert('L')
a = np.asarray(im).astype('float')
grad = np.gradient(a)  # 取图像灰度的梯度值
grad_x, grad_y = grad  # 分别取横纵图像梯度值
grad_x = grad_x * depth / 100.
grad_y = grad_y * depth / 100.
dx = np.cos(vec_el) * np.cos(vec_az)  # 光源对x 轴的影响
dy = np.cos(vec_el) * np.sin(vec_az)  # 光源对y 轴的影响
dz = np.sin(vec_el)  # 光源对z 轴的影响
A = np.sqrt(grad_x**2 + grad_y**2 + 1.)
uni_x = grad_x / A
uni_y = grad_y / A
uni_z = 1. / A
a2 = 255 * (dx * uni_x + dy * uni_y + dz * uni_z)
a2 = a2.clip(0, 255)
im2 = Image.fromarray(a2.astype('uint8'))
im2.save('./实验/实验9/myHandDraw.jpg')
