# 习题4.1
from random import randrange
x = randrange(0, 9, 1)
y = 10
tot = 0
while x != y:
    tot = tot + 1
    y = eval(input("请输入你猜的数："))
    if y > x:
        print("遗憾，太大了")
    elif y < x:
        print("遗憾，太小了")
    else:
        break
print("预测{}次，你猜中了！".format(tot))
