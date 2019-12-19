import random
import matplotlib.pyplot as plt
import scipy.io as sio
INF = 1e9
TOT = 1e6
lambd = 0.8
mu = 1.0
queue = 0
nextArriveTime = 0.0
nextDepartTime = INF
lastTime = 0.0
num = 0
servered = 0
QLength = {}
arriveTime = []
waitTime = []


def static_1(queue, nextArriveTime, num):
    global lastTime
    if not (queue in QLength):
        QLength[queue] = 0
    QLength[queue] += (nextArriveTime - lastTime)
    lastTime = nextArriveTime
    arriveTime.append(nextArriveTime)


def static_2(queue, nextDepartTime):
    global lastTime
    if not (queue in QLength):
        QLength[queue] = 0
    QLength[queue] += (nextDepartTime - lastTime)
    lastTime = nextDepartTime


def arrive():
    global queue, nextArriveTime, nextDepartTime, lastTime, num
    if num >= TOT:
        nextArriveTime = INF
        return
    queue += 1
    num += 1
    static_1(queue-1, nextArriveTime, num)
    if nextDepartTime == INF:
        waitTime.append(nextArriveTime - arriveTime[servered])
        nextDepartTime = nextArriveTime+random.expovariate(mu)
    nextArriveTime += random.expovariate(lambd)


def depart():
    global queue, nextArriveTime, nextDepartTime, lastTime, servered
    queue -= 1
    servered += 1
    static_2(queue+1, nextDepartTime)
    if queue > 0:
        waitTime.append(nextArriveTime - arriveTime[servered])
        nextDepartTime += random.expovariate(mu)
    else:
        nextDepartTime = INF


def paint():
    plt.figure(1)
    # 队列长度分布
    QLength_distribution = plt.subplot(1, 2, 1)
    plt.sca(QLength_distribution)
    x = []
    y = []
    sum = 0.0
    for key in QLength:
        x.append(key)
    x.sort()
    for key in x:
        y.append((lastTime - sum) / lastTime)
        sum += QLength[key]
    data = {}
    data['x'] = x
    data['y'] = y
    sio.savemat('单队列队列长度分布.mat', data)
    plt.plot(x, y, label="Queue length distribution")
    plt.yscale('log')
    plt.legend(loc='upper left')
    # 等待时间分布
    WaitTime_distribution = plt.subplot(1, 2, 2)
    plt.sca(WaitTime_distribution)
    data = {}
    for time in waitTime:
        tmp = int(time*10)
        if not (tmp in data):
            data[tmp] = 0
        data[tmp] += 1
    x = []
    y = []
    sum = 0
    for key in data:
        x.append(key/10.0)
    x.sort()
    for key in x:
        y.append((num - sum) / num)
        sum += data[int(key * 10)]
    data = {}
    data['x'] = x
    data['y'] = y
    sio.savemat('单队列等待时间分布.mat', data)
    plt.plot(x, y, label="Wait time distribution")
    plt.yscale('log')
    plt.legend(loc='upper left')

    plt.show()


def main():
    while num < TOT or queue > 0:
        if nextArriveTime < nextDepartTime:
            arrive()
        else:
            depart()
    print("Over.")
    paint()


if __name__ == "__main__":
    main()
