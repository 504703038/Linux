# 习题6.4
text = input("请输入一段文本字符：")
lab = list('!"#$%&()*+,-./:;<=>?@[\\]^_‘{|}~\' ，。、；“”')
words = list(text)
count = {}
for word in words:
    word = word.lower()
    if word in lab:
        continue
    count[word] = count.get(word, 0) + 1
ls = list(count.items())
ls.sort(key=lambda x: x[1], reverse=True)
# print(ls)
for i in ls:
    print(i[0], i[1])
