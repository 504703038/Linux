# 习题7.4
import json
import csv

ls = []
fr = open('./实验/实验7/price2016.csv')
content = csv.reader(fr)
for line in content:
    ls.append(line)

fw = open("./实验/实验7/price2016.json", "w")
for i in range(1, len(ls)):
    ls[i] = dict(zip(ls[0], ls[i]))
json.dump(ls[1:], fw, sort_keys=True, indent=4, ensure_ascii=False)
fw.close()
