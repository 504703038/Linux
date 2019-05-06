# 微实例7.1
textFile = open("./实验/实验7/7.1.txt", "rt")  # t表示文本文件方式
print(textFile.readline())
textFile.close()
binFile = open("./实验/实验7/7.1.txt", "rb")  # r表示二进制文件方式
print(binFile.readline())
binFile.close()
