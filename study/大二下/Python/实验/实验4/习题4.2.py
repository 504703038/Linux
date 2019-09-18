# 习题4.2
s = input("请输入一行字符：")
num = space = word = 0
for i in s:
    if i >= '0' and i <= '9':
        num += 1
    elif i >= 'a' and i <= 'z' or i >= 'A' and i <= 'Z':
        word += 1
    else:
        space += 1
print("英文字符有{}个，数字有{}个，空格有{}个".format(word, num, space))
