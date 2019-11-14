import numpy as np
import random
import matplotlib.pyplot as plt
INF = 1e9
lambd = 8
mu = 10
QLength = [0, 0, 0]

num = [0, 0, 0]  # 已到达人数
servered = [0, 0, 0]  # 已服务人数
csq = 0  # 当前服务队列
deficit = [200, 300, 500]  # 赤字大小
re_deficit = [0, 0, 0]  # 剩余赤字

arr_time = 0  # 下一人到达时间
depart_time = INF  # 下一人离开时间
serv_time = random.expovariate(mu)
last_time = [0, 0, 0]  # 上一次队列长度改变时间


# de_arrive_time = [[], [], []]
# de_depart_time = [[], [], []]

color = ['r', 'g', 'b']

QLength_P = [{}, {}, {}]  # 队列长度分布
wait_time = [[], [], []]
Wait_P = [{}, {}, {}]
# QLength = 0  # 队列长度
# last_time = 0
# QLength_P = {}  # 队列长度分布
# Wait_P = {}  # 等待时间分布
# wait_time = []  # 等待时间


# 数据统计
def statistics(current_time, type, q):
    ql = QLength[q]
    if not QLength_P[q].__contains__(ql):
        QLength_P[q][ql] = 0
    QLength_P[q][ql] += current_time - last_time[q]
    if type == 'arrive':
        wait_time[q].append(current_time)
    else:
        wait_time[q][servered[q]] = int(
            current_time - wait_time[q][servered[q]])
        tmp = wait_time[q][servered[q]]
        if not Wait_P[q].__contains__(tmp):
            Wait_P[q][tmp] = 0
        Wait_P[q][tmp] += 1


# 到达模拟
def arrive():
    global arr_time, depart_time, num, csq, serv_time
    q = random.randint(0, 2)
    statistics(arr_time, 'arrive', q)  # 数据统计
    QLength[q] += 1
    num[q] += 1
    if depart_time == INF and csq == q:
        if re_deficit[q] < serv_time:
            re_deficit[q] += deficit[q]
            csq = (csq + 1) % 3
        else:
            depart_time = arr_time + serv_time
            re_deficit[q] -= serv_time
            serv_time = random.expovariate(mu)
    last_time[q] = arr_time
    arr_time += random.expovariate(lambd)


# 离开模拟
def depart():
    global depart_time, serv_time, csq
    QLength[csq] -= 1
    statistics(depart_time, 'depart', csq)  # 数据统计
    servered[csq] += 1
    last_time[csq] = depart_time
    if np.sum(QLength) > 0:
        while QLength[csq] == 0 or re_deficit[csq] < serv_time:
            re_deficit[csq] += deficit[csq]
            csq = (csq + 1) % 3
        depart_time += serv_time
        re_deficit[csq] -= serv_time
        serv_time = random.expovariate(mu)
    else:
        depart_time = INF


# 画图
def paint():
    plt.figure(1)
    # 队列长度分布
    plt.subplot(1, 2, 1)
    plt.yscale('log')
    for i in range(3):
        QL_P = QLength_P[i]
        x = []
        y = []
        for key in QL_P:
            x.append(key)
            y.append(QL_P[key] / last_time[i])
        plt.plot(x, y, color=color[i])
    # 等待时间分布
    plt.subplot(1, 2, 2)
    plt.yscale('log')
    for i in range(3):
        # print(servered[i])
        WT_P = Wait_P[i]
        sum = 0
        x = []
        y = []
        for key in WT_P:
            x.append(key)
            y.append((servered[i] - sum) / servered[i])
            sum += WT_P[key]
        # print(x, y)
        plt.plot(x, y, color=color[i])
    plt.show()


def main():
    n = 1e6
    while np.sum(servered) < n:
        if arr_time < depart_time and np.sum(num) < n:
            arrive()
        else:
            depart()
    paint()


if __name__ == '__main__':
    main()
