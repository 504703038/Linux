# 微实例7.11
fr = open("price2016.csv", "r")
fw = open("price2016out.csv", "w")
ls = []
for line in fr:  # 将CSV 文件中的二维数据读入到列表变量
    line = line.replace("\n", "")
    ls.append(line.split(","))
for i in range(len(ls)):  # 遍历列表变量计算百分数
    for j in range(len(ls[i])):
        if ls[i][j].replace(".", "").isnumeric():
            ls[i][j] = "{:.2}%".format(float(ls[i][j]) / 100)
for row in ls:  # 将列表变量中的二位数据输出到CSV 文件
    print(row)
    fw.write(",".join(row) + "\n")
fr.close()
fw.close()
