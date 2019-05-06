# 习题5.7
def Hanoi(a, b, c, n):
    if n > 1:
        Hanoi(a, c, b, n - 1)
    print("{} -> {}".format(a, c))
    if n > 1:
        Hanoi(b, a, c, n - 1)


while True:
    try:
        n = eval(input("请输入汉诺塔的层数："))
        Hanoi('A', 'B', 'C', n)
    except NameError:
        print("请输入一个正整数")
    else:
        break
