# 微实例7.3
fname = input("请输入要写入的文件: ")
fo = open("./实验/实验7/"+fname, "w+")
ls = ["唐诗", "宋词", "元曲"]
fo.writelines(ls)
for line in fo:
    print(line)
fo.close()
