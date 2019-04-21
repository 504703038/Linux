# 习题6.2
def Elemental_weight(ls):
    count = {}
    for item in ls:
        count[item] = count.get(item, 0) + 1
        if count[item] > 1:
            return True
    return False


ls = []
tot = 0
while True:
    item = input("请输入列表的第{}个元素,换行结束输入：".format(tot + 1))
    if item == "":
        break
    ls.append(item)

if Elemental_weight(ls):
    print("列表元素有重复")
else:
    print("列表元素没有重复")