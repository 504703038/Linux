import random
import matplotlib.pyplot as plt
from matplotlib.ticker import LogLocator, FormatStrFormatter
INF = 1e9
lambd = 8
mu = 9
Q = 0  # 队列
jobs = 0  # 已服务人数
arrival_next = 0  # 下一人到达时间
depart_next = INF  # 下一人离开时间
num = 0  # 已到达人数
QLenth_arr = []  # 队列长度数组
# num_arr = []
wait_time = []  # 等待时间
arrive_time = []  # 到达时间
depart_time = []  # 离开时间
QLenth_cnt = {}  # 队列长度出现次数
QLenth_time = {}  # 队列长度占用时间
QLenth_last_time = 0
wait_time_cnt = {}  # 等待时间


# 更新等待时间出现次数
def update_wait_time(time):
    time = int(time)
    if not wait_time_cnt.__contains__(time):
        wait_time_cnt[time] = 0
    wait_time_cnt[time] += 1


# 更新队列长度出现次数
def que_length_cnt(length):
    if not QLenth_cnt.__contains__(length):
        QLenth_cnt[length] = 0
    QLenth_cnt[length] = QLenth_cnt[length] + 1


# 更新队列长度时间占比
def que_length_time(length, cur_time):
    global QLenth_last_time
    if not QLenth_time.__contains__(length):
        QLenth_time[length] = 0
    QLenth_time[length] += cur_time - QLenth_last_time  # 记录队列长度占比时间
    QLenth_last_time = cur_time  # 更新队列改变时间


def arrive():
    global Q, arrival_next, num, depart_next, QLenth_last_time
    Q += 1  # 排队队列长度+1
    # print(Q)
    num += 1  # 到达人数+1
    arrive_time.append(arrival_next)  # 记录到达时间
    QLenth_arr.append(Q-1)  # 记录队列长度
    que_length_cnt(Q)  # 更新队列长度出现次数
    que_length_time(Q, arrival_next)  # 记录队列长度时间占比
    if depart_next == INF:  # 判断当前是否在服务，若不在服务状态那么开始服务
        depart_next = arrival_next + random.expovariate(mu)
    arrival_next += random.expovariate(lambd)  # 更新下人一到达时间


def depart():
    global Q, depart_next, jobs
    Q -= 1  # 服务结束队列长度-1
    wait_time.append(depart_next - arrive_time[jobs])  # 记录等待时间
    update_wait_time(depart_next - arrive_time[jobs])  # 记录等待时间出现次数
    depart_time.append(depart_next)  # 记录离开时间
    que_length_time(Q+1, depart_next)  # 记录队列长度占比时间
    if Q > 0:  # 继续服务下一人
        depart_next += random.expovariate(mu)
    else:
        depart_next = INF
    jobs += 1  # 已服务人数+1


while jobs < 1e6:
    if arrival_next < depart_next:  # 推进系统时间
        arrive()
    else:
        depart()

# 平均队列长度
# pylab.plot(range(1, num + 1), QLenth_arr)
# 平均等待时间
# pylab.plot(depart_time, wait_time)

# ax = plt.axes(yscale='log')
# plt.sca(ax)

plt.figure(1)
ax1 = plt.subplot(1, 2, 1)
ax2 = plt.subplot(1, 2, 2)
# # 队列长度分布
plt.sca(ax1)

# 队列长度出现次数
# pylab.plot(list(QLenth_cnt.keys()), [
#            y / num for y in list(QLenth_cnt.values())], label="Queue length distribution")
# pylab.legend(loc='upper left')
# 队列长度占用时间
sum = 0
x = []
y = []
for key in QLenth_time:
    value = QLenth_time[key]
    x.append(key)
    y.append((QLenth_last_time - sum) / QLenth_last_time)
    sum += value
plt.plot(x, y, label="Queue length distribution")
plt.yscale('log')
plt.legend(loc='upper left')

# 等待时间分布
plt.sca(ax2)
plt.plot(list(wait_time_cnt.keys()), [
    y / jobs for y in list(wait_time_cnt.values())], label="Waiting time distribution")
plt.yscale('log')
plt.legend(loc='upper left')

plt.show()
