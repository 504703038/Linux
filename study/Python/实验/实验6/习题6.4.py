# 习题6.4
import jieba
text = input("请输入一段文本字符：")
words = jieba.cut(text)
print(words)