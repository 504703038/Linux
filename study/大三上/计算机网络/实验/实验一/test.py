import json
import scipy.io as sio
# file_name = '队列长度分布'
file_name = '等待时间分布'
file = open(file_name+'.txt', 'r+')
dic = eval(file.read())
file.close()
sio.savemat(file_name+'.mat', dic)
