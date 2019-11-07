import numpy
import math
data1 = numpy.array([[0.011, 1.03, -0.21], [1.27, 1.28, 0.08], [0.13, 3.12, 0.16], [-0.21, 1.23, -0.11], [-2.18, 1.39, -0.19],
                     [0.34, 1.96, -0.16], [-1.38, 0.94, 0.45], [-1.02, 0.82, 0.17], [-1.44, 2.31, 0.14], [0.26, 1.94, 0.08]])

data2 = numpy.array([[1.36, 2.17, 0.14], [1.41, 1.45, -0.38], [1.22, 0.99, 0.69], [2.46, 2.19, 1.31], [0.68, 0.79, 0.87],
                     [2.51, 3.22, 1.35], [0.60, 2.44, 0.92], [0.64, 0.13, 0.97], [0.85, 0.58, 0.99], [0.66, 0.51, 0.88]])


def problem_1():
    print("\n\nproblem_1")
    # 类1
    for i in range(1, 4):
        mu = numpy.mean(data1[:, i-1])
        sigma = (10-1)/10*numpy.cov(data1[:, i-1])
        print("类1的特征x", i, "的最大似然估计均值为", mu, "方差为", sigma)
    # 类2
    for i in range(1, 4):
        mu = numpy.mean(data2[:, i-1])
        sigma = (10-1)/10*numpy.cov(data2[:, i-1])
        print("类2的特征x", i, "的最大似然估计均值为", mu, "方差为", sigma)


def problem_2():
    print("\n\nproblem_2")
    # 类1
    for i in range(1, 3):
        for j in range(i+1, 4):
            data = numpy.empty([10, 2])
            data[:, 0] = data1[:, i-1]
            data[:, 1] = data1[:, j - 1]
            mu = numpy.mean(data, axis=0)
            sigma = (10-1)/10*numpy.cov(data.T)
            print("类1的特征x", i, "和特征x", j, "的最大似然估计均值为", mu)
            print("方差为")
            print(sigma)
    # 类2
    for i in range(1, 3):
        for j in range(i+1, 4):
            data = numpy.empty([10, 2])
            data[:, 0] = data2[:, i-1]
            data[:, 1] = data2[:, j - 1]
            mu = numpy.mean(data, axis=0)
            sigma = (10-1)/10*numpy.cov(data.T)
            print("类1的特征x", i, "和特征x", j, "的最大似然估计均值为", mu)
            print("方差为")
            print(sigma)


def problem_3():
    print("\n\nproblem_3")
    # 类1
    mu = numpy.mean(data1, axis=0)
    sigma = (10-1)/10*numpy.cov(data1.T)
    print("类1的特征x1,x2,x3的最大似然估计均值为", mu)
    print("方差为")
    print(sigma)
    # 类2
    mu = numpy.mean(data2, axis=0)
    sigma = (10-1)/10*numpy.cov(data2.T)
    print("类2的特征x1,x2,x3的最大似然估计均值为", mu)
    print("方差为")
    print(sigma)


def problem_4():
    print("\n\nproblem_4")
    # 类1
    mu = numpy.mean(data1, axis=0)
    sigma = numpy.zeros([3, 3], dtype=float)
    for i in range(0, 3):
        sigma[i][i] = (10-1)/10*numpy.cov(data1[:, i])
    print("类1的均值为", mu)
    print("方差为")
    print(sigma)
    # 类2
    mu = numpy.mean(data2, axis=0)
    sigma = numpy.zeros([3, 3], dtype=float)
    for i in range(0, 3):
        sigma[i][i] = (10-1)/10*numpy.cov(data2[:, i])
    print("类2的均值为", mu)
    print("方差为")
    print(sigma)


problem_1()
problem_2()
problem_3()
problem_4()
