# 习题4.7

while True:
    try:
        TempStr = input("请输入温度值：")
        if TempStr[-1] in ['f', 'F']:
            C = (eval(TempStr[0:-1]) - 32) / 1.8
            print("转换后的温度是{:.2f}C".format(C))
        elif TempStr[-1] in ['C', 'c']:
            F = 1.8 * eval(TempStr[0:-1]) + 32
            print("转换后的温度是{:.2f}F".format(F))
        else:
            print("温度符号输入有误请重新输入!")
            continue
    except NameError:
        print("温度数值中不能含有其他符号!")
    except SyntaxError:
        print("温度数值中不能含有其他符号!")
    except IndexError:
        print("输入不能为空！")
    else:
        break
