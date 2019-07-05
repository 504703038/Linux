# 微实例7.8
fo = open("./实验/实验7/price2016.csv", "r")
ls = []
for line in fo:
    line = line.replace("\n", "")
    ls.append(line.split(","))
print(ls)
fo.close()
