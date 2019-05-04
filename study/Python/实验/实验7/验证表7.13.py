# 表7.13 json库的操作类函数
import json
dt = {'b': 2, 'c': 4, 'a': 6}
# json.dumps()函数
s1 = json.dumps(dt)
s2 = json.dumps(dt, sort_keys=True, indent=4)
print(s1)
print(s2)
# json.loads()函数
dt2 = json.loads(s2)
print(dt2, type(dt2))
# json.dump()函数
fp = open('test.json', "w+")
json.dump(dt, fp, sort_keys=True, indent=4)
fp.close()
# json.load()函数
fp = open('test.json', "r+")
s3 = json.load(fp)
print(s3)
