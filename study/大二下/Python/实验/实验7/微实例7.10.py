# 微实例7.10
fo = open("./实验/实验7/price2016.csv", "r")
ls = []
for line in fo:
    line = line.replace("\n", "")
    ls = line.split(",")
    lns = ""
    for s in ls:
        lns += "{}\t".format(s)
    print(lns)
fo.close()
