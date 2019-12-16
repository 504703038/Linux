import random
import matplotlib.pyplot as plt
INF = 1e9
TOT = 10
color = ['r', 'g', 'b']
lis = []
q = 0
currentTime = 0.0


class Queue:
    lambd = 0.8
    mu = 1.0
    queue = 0
    nextArriveTime = 0.0
    nextServerTime = INF
    nextDepartTime = INF
    deficit = 0.0
    re_deficit = 0.0
    lastTime = 0.0
    num = 0
    servered = 0
    serving = False

    QLength = {}
    arriveTime = []
    waitTime = []

    def __init__(self, lambd, mu, deficit):
        self.lambd = lambd
        self.mu = mu
        self.deficit = deficit
        self.re_deficit = deficit

    def static_1(self, queue, nextArriveTime):
        if not (queue in self.QLength):
            self.QLength[queue] = 0
        self.QLength[queue] += (nextArriveTime - self.lastTime)
        self.lastTime = nextArriveTime
        self.arriveTime.append(nextArriveTime)

    def static_2(self, queue, nextDepartTime):
        if not (queue in self.QLength):
            self.QLength[queue] = 0
        self.QLength[queue] += (nextDepartTime - self.lastTime)
        self.lastTime = nextDepartTime

    def arrive(self):
        global q
        if self.num >= TOT:
            self.nextArriveTime = INF
            return
        self.queue += 1
        self.num += 1
        self.static_1(self.queue-1, self.nextArriveTime)
        if self.nextServerTime == INF:
            self.nextServerTime = random.expovariate(self.mu)
        # if self.serving:
        #     if self.nextServer():
        #         self.nextDepartTime = currentTime + self.nextServerTime
        #     else:
        #         q = (q + 1) % 3
        #         self.re_deficit += self.deficit
        #         self.serving = False
        #         self.nextDepartTime = INF
        self.nextArriveTime += random.expovariate(self.lambd)

    def server(self):
        global q, currentTime
        self.queue -= 1
        self.servered += 1
        self.re_deficit -= self.nextServerTime
        self.static_2(self.queue+1, self.nextDepartTime)
        if self.queue > 0:
            if self.nextServerTime == INF:
                self.nextServerTime = random.expovariate(self.mu)
            # if self.nextServer():
            #     self.nextDepartTime = currentTime + self.nextServerTime
            # else:
            #     q = (q + 1) % 3
            #     self.re_deficit += self.deficit
            #     self.serving = False
            #     self.nextDepartTime = INF
        else:
            self.nextServerTime = INF
        self.nextDepartTime = INF

    def nextServer(self):
        return self.nextServerTime <= self.re_deficit

    def notOver(self):
        return (self.num < TOT or self.queue > 0)

    def arriveTimeAppend(self, data):
        self.arriveTime.append(data)

    def waitTimeAppend(self, data):
        self.waitTime.append(data)


def paint():
    plt.figure(1)
    # 队列长度分布
    QLength_distribution = plt.subplot(1, 2, 1)
    plt.sca(QLength_distribution)
    for i in range(3):
        x = []
        y = []
        sum = 0.0
        for key in lis[i].QLength:
            x.append(key)
        x.sort()
        for key in x:
            y.append((lis[i].lastTime - sum) / lis[i].lastTime)
            sum += lis[i].QLength[key]
        plt.plot(x, y, color=color[i])
        plt.yscale('log')
        # plt.legend(loc='upper left')
    # 等待时间分布
    WaitTime_distribution = plt.subplot(1, 2, 2)
    plt.sca(WaitTime_distribution)
    for i in range(3):
        data = {}
        for time in lis[i].waitTime:
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
            y.append((lis[i].num - sum) / lis[i].num)
            sum += data[int(key*10)]
        plt.plot(x, y, color=color[i])
        plt.yscale('log')
        # plt.legend(loc='upper left')
    plt.show()


def main():
    global q, currentTime
    lis.append(Queue(0.8, 1, 200))
    lis.append(Queue(0.8, 1, 300))
    lis.append(Queue(0.8, 1, 500))
    while lis[0].notOver() or lis[1].notOver() or lis[2].notOver():
        lis[q].serving = True
        # print(lis[0].servered, lis[1].servered, lis[2].servered)
        arriveTime = min(lis[0].nextArriveTime,
                         lis[1].nextArriveTime, lis[2].nextArriveTime)
        if arriveTime < INF and arriveTime < lis[q].nextDepartTime:
            currentTime = arriveTime
            if lis[0].nextArriveTime == arriveTime:
                lis[0].arrive()
                # print("Q", 0, "arrive.")
            elif lis[1].nextArriveTime == arriveTime:
                lis[1].arrive()
                # print("Q", 1, "arrive.")
            elif lis[2].nextArriveTime == arriveTime:
                lis[2].arrive()
                # print("Q", 2, "arrive.")
        elif lis[q].nextDepartTime < INF:
            currentTime = lis[q].nextDepartTime
            lis[q].server()
            # print("Q", q, "depart.")
        for i in range(3):
            if lis[q].nextDepartTime == INF:
                if lis[q].nextServer():
                    lis[q].nextDepartTime = currentTime + lis[q].nextServerTime
                    lis[q].waitTimeAppend(currentTime -
                                          lis[q].arriveTime[lis[q].servered])
                else:
                    lis[q].re_deficit += lis[q].deficit
                    lis[q].serving = False
                    q = (q + 1) % 3

    paint()


if __name__ == "__main__":
    main()
