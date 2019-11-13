import numpy
import math
import matplotlib.pyplot as plt
from mpl_toolkits.mplot3d import Axes3D

sample_1 = numpy.array([[0.28, 1.31, -6.2], [0.07, 0.58, -0.78], [1.54, 2.01, -1.63], [-0.44, 1.18, -4.32], [-0.81, 0.21, 5.73],
                        [1.52, 3.16, 2.77], [2.2, 2.42, -0.19], [0.91, 1.94, 6.21], [0.65, 1.93, 4.38], [-0.26, 0.82, -0.96]])

sample_2 = numpy.array([[0.42, -0.087, 0.58], [-0.2, -3.3, -3.4], [1.3, -0.32, 1.7], [0.39, 0.71, 0.23], [-1.6, -5.3, -0.15],
                        [-0.029, 0.89, -4.7], [-0.23, 1.9, 2.2], [0.27, -0.3, -0.87], [-1.9, 0.76, -2.1], [0.87, -1.0, -2.6]])

sample_3 = numpy.array([[-0.4, 0.58, 0.089], [-0.31, 0.27, -0.04], [0.38, 0.055, -0.035], [-0.15, 0.53, 0.011], [-0.35, 0.47, 0.034],
                        [0.17, 0.69, 0.1], [-0.011, 0.55, -0.18], [-0.27, 0.61, 0.12], [-0.065, 0.49, 0.0012], [-0.12, 0.054, -0.063]])

sample_4 = numpy.array([[0.83, 1.6, -0.014], [1.1, 1.6, 0.48], [-0.44, -0.41, 0.32], [0.047, -0.45, 1.4], [0.28, 0.35, 3.1],
                        [-0.39, -0.48, 0.11], [0.34, -0.079, 0.14], [-0.3, -0.22, 2.2], [1.1, 1.2, -0.46], [0.18, -0.11, -0.49]])
sample = [sample_1, sample_2, sample_3, sample_4]


# Parzen窗
def Parzen(sample, x, h):
    n = 10
    pi = 0
    h = h / math.sqrt(n)
    v = (4*math.pi*h**3)/3
    for i in range(n):
        tmp = sample[i] - x.T
        pi += math.exp(-(numpy.dot(tmp, tmp.T)[0])/(2*(h**2)))
    return pi/n/v


# Parzen窗分类
def Parzen_Classify(x):
    clas, value = 0, 0
    for i in range(4):
        tmp = Parzen(sample[i], x, 1)
        if clas == 0 or tmp > value:
            clas = i + 1
            value = tmp
    return clas


# x到各样本的距离
def get_distance(sample, x):
    n = 10
    dis = []
    for i in range(n):
        tmp = numpy.sqrt(numpy.sum(numpy.square(x-sample[i])))
        dis.append(tmp)
    return dis


# k_n邻近法
def K_n(k, x, sample):
    dis = []
    for i in range(4):
        tmp = get_distance(sample[i], x)
        for j in tmp:
            dis.append([j, i])
    dis.sort()
    cnt = [0, 0, 0, 0]
    for i in range(k):
        w = dis[i][1]
        cnt[w] += 1
    return cnt


def K_n_1(k, sample):
    num = 1000
    n = 10
    x = numpy.linspace(-3, 3, num)
    y = []
    for i in range(num):
        dis = get_distance(sample, x[i])
        dis.sort()
        v = 2 * dis[k]
        y.append(k / n / v)
    # 画图
    plt.subplot(1, 5, k)
    plt.plot(x, y)
    plt.xlabel("x")
    plt.ylabel("p(x)")
    plt.title("k="+str(k))


def K_n_2(k, sample):
    num = 100
    n = 10
    x = y = numpy.linspace(-3, 3, num)
    z = []
    for i in range(num):
        tmp = []
        for j in range(num):
            dis = get_distance(sample, [x[i], y[j]])
            dis.sort()
            v = math.pi * (dis[k]**2)
            tmp.append(k / n / v)
        z.append(tmp)
    x, y = numpy.meshgrid(x, y)
    z = numpy.array(z)
    # 画图
    fig = plt.figure()
    ax = Axes3D(fig)
    ax.plot_surface(x, y, z, rstride=1, cstride=1, cmap='rainbow')
    ax.set_xlabel("x1")
    ax.set_ylabel("x2")
    ax.set_zlabel("p(x)")
    plt.title("k="+str(k))


# K-n邻近法分类
def K_n_Classify(k, x):
    w = K_n(k, x, sample)
    clas = numpy.argmax(w)
    return clas


def problem():
    print("\n\n问题一：")
    data = [numpy.array([[0.5], [1.0], [0.0]]), numpy.array(
        [[0.41], [0.82], [0.88]]), numpy.array([[0.3], [0.44], [-0.1]])]
    for x in data:
        print(x.T, "属于第", Parzen_Classify(x), "类")


def problem_1():
    # print("\n\n1、")
    #     # print(sample_1[:, 0])
    for k in [1, 3, 5]:
        K_n_1(k, sample_1[:, 0])
    #     # plt.show()


def problem_2():
    # print("\n\n2、")
    data = numpy.empty([10, 2])
    data[:, 0] = sample_2[:, 0]
    data[:, 1] = sample_2[:, 1]
    for k in [1, 3, 5]:
        K_n_2(k, data)
    # plt.show()


def problem_3():
    print("\n\n3、")
    k = 5
    data = [numpy.array([[0.14], [0.72], [4.1]]), numpy.array(
        [[-0.81], [0.61], [-0.38]]), numpy.array([[0.31], [1.51], [-0.50]])]
    for x in data:
        print(x.T, "属于第", K_n_Classify(k, x), "类")


problem()
problem_1()
problem_2()
problem_3()
plt.show()
