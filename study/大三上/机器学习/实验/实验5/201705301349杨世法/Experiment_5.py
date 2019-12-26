import mat4py
import numpy as np
import knn
import network
import svm算法 as svm


# 处理图像数据
def pretreatData(data):
    c = len(data)
    for i in range(c):
        r = len(data[i])
        for j in range(r):
            if (data[i][j] >= 0.5):
                data[i][j] = 1
            else:
                data[i][j] = 0
    return data


# 从文件中读取数据
def getData():
    data = mat4py.loadmat(
        '/home/shifa-yang/Desktop/work/study/大三上/机器学习/实验/实验5/lms.mat')
    x = data['X']
    y = data['y']
    x = pretreatData(x)  # 处理图像数据
    idx = np.random.permutation(5000)
    train_data = []
    train_label = []
    for i in range(4000):
        train_data.append(x[idx[i]])
        train_label.append(y[idx[i]])
    train_label = [[x[0] % 10] for x in train_label]
    test_data = []
    test_label = []
    for i in range(4000, 5000):
        test_data.append(x[idx[i]])
        test_label.append(y[idx[i]])
    test_label = [[x[0] % 10] for x in test_label]
    return train_data, train_label, test_data, test_label


def integrate(train_data, train_label, test_data, test_label):
    # SVM
    Svm = svm.SVM(train_data, train_label, test_data, test_label)
    test_result_svm = Svm.classify()
    # print(test_result_svm)
    print("SVM over.")

    # KNN邻近法
    kn = knn.KNN(train_data, train_label, 7)
    test_result_kn = kn.work(test_data)
    # print(test_result_kn)
    print("KNN over.")

    # 神经网络
    net = network.Network([400, 25, 10], train_data,
                          train_label, test_data, test_label)
    test_result_bp = net.SGD(30, 10, 3.0)
    # print(test_result_bp)
    print("BP over.")

    num = len(test_data)
    err = 0
    for i in range(num):
        e = np.zeros((10, 1))
        e[test_result_svm[i]] += 1
        e[test_result_kn[i]] += 1
        e[test_result_bp[i]] += 1
        tmp_class = np.argmax(e)
        if tmp_class != test_label[i][0]:
            err += 1
    # print(err)
    print('accuracy:', 1 - 1.0 * err / num)


def main():
    train_data, train_label, test_data, test_label = getData()
    integrate(train_data, train_label, test_data, test_label)


if __name__ == "__main__":
    main()
