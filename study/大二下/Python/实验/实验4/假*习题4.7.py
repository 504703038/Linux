# 习题4.7
from random import randrange
x = randrange(0, 9, 1)
y = -1
tot = 0
while x != y:
    tot = tot + 1
    while True:
        try:
            y = eval(input("请输入你猜的数："))
        except NameError:
            print("请输入整数,而不是字母!")
        except SyntaxError:
            print("请输入整数，而不是其他符号!")
        except:
            print("输入有误")
        else:
            break
    if y > x:
        print("遗憾，太大了")
    elif y < x:
        print("遗憾，太小了")
    else:
        break
print("预测{}次，你猜中了！".format(tot))
