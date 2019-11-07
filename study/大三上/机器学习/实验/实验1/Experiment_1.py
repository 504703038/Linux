import numpy as np
import math
data1 = np.array([[-5.01, -8.12, -3.68], [-5.43, -3.48, -3.54], [1.08, -5.52, 1.66], [0.86, -3.78, -4.11], [-2.67, 0.63, 7.39],
                  [4.94, 3.29, 2.08], [-2.51, 2.09, -2.59], [-2.25, -2.13, -6.94], [5.56, 2.86, -2.26], [1.03, -3.33, 4.33]])
data2 = np.array([[-0.91, -0.18, -0.05], [1.30, -2.06, -3.53], [-7.75, -4.54, -0.95], [-5.47, 0.50, 3.92], [6.14, 5.72, -4.85],
                  [3.60, 1.26, 4.36], [5.37, -4.63, -3.65], [7.18, 1.46, -6.66], [-7.39, 1.17, 6.3], [-7.50, -6.32, -0.31]])
data3 = np.array([[5.35, 2.26, 8.13], [5.12, 3.22, -2.66], [-1.34, -5.31, -9.87], [4.48, 3.42, 5.19], [7.11, 2.39, 9.21],
                  [7.17, 4.33, -0.98], [5.75, 3.97, 6.65], [0.77, 0.27, 2.41], [0.9, -0.43, -8.71], [3.52, -0.36, 6.43]])

point = [np.array([[1], [2], [1]]), np.array([[5], [3], [2]]),
         np.array([[0], [0], [0]]), np.array([[1], [0], [0]])]


# 计算均值
def average(data):
    r, c = data.shape
    avg = []
    for i in range(c):
        sum = 0
        for j in range(r):
            sum += data[j][i]
        sum /= r
        avg.append(sum)
    avg = np.array(avg).reshape(1, 3)
    return np.array(avg).T


# 计算Mahalanobis距离
def Mahalanobis_dis(x, data):
    sigma = np.cov(data.T)
    u = average(data)
    # print(sigma)
    # print(np.linalg.inv(sigma))
    tmp = np.dot((x - u).T, np.linalg.inv(sigma))
    tmp = np.dot(tmp, x - u)
    return np.sqrt(tmp)[0][0]


# 计算判别函数的值
def Discriminant(x, data, Pw):
    sigma = np.cov(data.T)
    u = average(data)
    tmp = -(1/2)*np.dot((x - u).T, np.linalg.inv(sigma))
    tmp = np.dot(tmp, x - u)
    tmp -= sigma.ndim/2*math.log(2*math.pi)
    tmp -= (1/2)*math.log(np.linalg.det(sigma))
    tmp += math.log(Pw)
    return tmp


# 分类
def Classify(x, Pw):
    data = [data1, data2, data3]
    clas, value = 0, 0
    for i in range(3):
        tmp = Discriminant(x, data[i], Pw[i])
        print(x.T,tmp)
        if tmp > value or clas == 0:
            clas, value = i+1, tmp
    return clas


def problem_a():
    print("problem_a:")
    for p in point:
        print(p.T, "到data1的Mahalanobis距离为", Mahalanobis_dis(p, data1))
        print(p.T, "到data2的Mahalanobis距离为", Mahalanobis_dis(p, data2))
        print(p.T, "到data3的Mahalanobis距离为", Mahalanobis_dis(p, data3))


def problem_b():
    print("problem_b:")
    for p in point:
        print(p.T, "属于第", Classify(p, [1/3, 1/3, 1/3]), "类")


def problem_c():
    print("problem_c:")
    for p in point:
        print(p.T, "属于第", Classify(p, [0.8, 0.1, 0.1]), "类")


def debug():
    p=point[0]
    print(p.T, "到data1的Mahalanobis距离为", Mahalanobis_dis(p, data1))
    print(p.T, "到data2的Mahalanobis距离为", Mahalanobis_dis(p, data2))
    print(p.T, "到data3的Mahalanobis距离为", Mahalanobis_dis(p, data3))


problem_a()
problem_b()
problem_c()
# debug()