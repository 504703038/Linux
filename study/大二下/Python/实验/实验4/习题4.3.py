# 习题4.3
def gcd(a, b):
    if b == 0:
        return a
    else:
        return gcd(b, a % b)


def lcm(a, b):
    return a * b / gcd(a, b)


x, y = eval(input("请输入两个整数（用逗号隔开）："))
print("{}和{}的最大公约数是{},最小公倍数是{:.0f}".format(x, y, gcd(x, y), lcm(x, y)))
