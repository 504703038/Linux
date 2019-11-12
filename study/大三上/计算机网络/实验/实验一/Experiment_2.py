import random
import matplotlib.pyplot as plt
INF = 1e9
lambd = 8
mu = 10
# QLength = 0  # 队列长度
# num = 0  # 已到达人数
# jobs = 0  # 已服务人数
# arrival_time = 0  # 下一人到达时间
# depart_time = INF  # 下一人离开时间
# last_time = 0
# QLength_P = {}  # 队列长度分布
# Wait_P = {}  # 等待时间分布
# wait_time = []  # 等待时间


# 数据统计
def static(current_time, type):
    global a
#     if not QLength_P.__contains__(QLength):
#         QLength_P[QLength] = 0
#     QLength_P[QLength] += 1
#     # QLength_P[QLength] += current_time - last_time
#     if type == 'arrive':
#         wait_time.append(current_time)
#     else:
#         det = int(current_time - wait_time[jobs])
#         if not Wait_P.__contains__(det):
#             Wait_P[det] = 0
#         Wait_P[det] += 1


# 到达模拟
def arrive():
    global QLength, arrival_time, depart_time, last_time, num
    # QLength += 1
    # static(arrival_time, 'arrive')  # 数据统计
    # num += 1
    # if depart_time == INF:
    #     depart_time = arrival_time + random.expovariate(mu)
    # last_time = arrival_time
    # arrival_time += random.expovariate(lambd)


# 离开模拟
def depart():
    global QLength, depart_time, last_time, jobs
    # QLength -= 1
    # # static(depart_time, 'depart')  # 数据统计
    # # jobs += 1
    # last_time = depart_time
    # if QLength > 0:
    #     depart_time += random.expovariate(mu)
    # else:
    #     depart_time = INF


# 画图
def paint():
    plt.figure(1)
    # 队列长度分布
    # 等待时间分布
    # plt.show()


if __name__ == '__main__':
    while jobs < 1e7:
        if arrival_time < depart_time:
            arrive()
        else:
            depart()
    paint()
