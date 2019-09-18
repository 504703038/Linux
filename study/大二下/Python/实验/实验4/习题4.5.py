# 习题4.5
from random import randint
x = randint(0, 100)
y = 'a'
tot = 0
while x != y:
    tot = tot + 1
    y = input("请输入你猜的数：")
    while not y.isdigit():
        y = input("输入内容必须为整数！请重新输入：")
    y = eval(y)
    if y > x:
        print("遗憾，太大了")
    elif y < x:
        print("遗憾，太小了")
    else:
        break
print("预测{}次，你猜中了！".format(tot))
