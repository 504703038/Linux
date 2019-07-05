# 习题6.1
import random
ls = list('123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz')
for i in range(10):
    password = ""
    for j in range(8):
        x = random.randint(0, ls.__len__() - 1)
        password += ls[x]
    print(password)
