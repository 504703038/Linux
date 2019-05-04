# 习题7.4
import json
import csv
ls = []

with open('price2016.csv') as csvfile:
    content = csv.reader(csvfile)
    for line in content:
        ls.append(line)

fw = open("price2016.json", "w")
for i in range(1, len(ls)):
    ls[i] = dict(zip(ls[0], ls[i]))
json.dump(ls[1:], fw, sort_keys=True, indent=4, ensure_ascii=False)
fw.close()