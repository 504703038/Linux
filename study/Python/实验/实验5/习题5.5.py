# 习题5.5
def isPrime(n):
    if n == 2:
        return True
    if n <= 1:
        return False
    f = 1
    for i in range(2, n):
        if i * i > n:
            break
        if n % i == 0:
            f = 0
            break
    if f == 1:
        return True
    else:
        return False


while True:
    try:
        n = eval(input("请输入一个整数："))
        ans = isPrime(n)
    except NameError:
        print("输入必须是自然数!请重新输入")
    except TypeError:
        print("输入必须是自然数!请重新输入")
    else:
        print(ans)
        break
