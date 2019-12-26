# import mnist_loader
import network
import mat4py
import numpy as np
# 从文件中读取数据


def getData():
    data = mat4py.loadmat(
        '/home/shifa-yang/Desktop/work/study/大三上/机器学习/实验/实验5/lms.mat')
    x = data['X']
    y = data['y']
    x = pretreatData(x)  # 处理图像数据
    # x = np.array(x)
    # y = np.array(y)
    idx = np.random.permutation(5000)
    train_data = []
    train_label = []
    for i in range(4000):
        train_data.append(x[idx[i]])
        train_label.append(y[idx[i]])
    test_data = []
    test_label = []
    for i in range(4000, 5000):
        test_data.append(x[idx[i]])
        test_label.append(y[idx[i]])

    # print(type(train_data))
    print(len(train_data))
    print(len(train_data[0]))

    train_data = [np.reshape(x, (400, 1)) for x in train_data]
    train_label = [vectorized_result(y) for y in train_label]
    test_data = [np.reshape(x, (400, 1)) for x in test_data]
    test_label = [[y[0] % 10] for y in test_label]
    train_data = zip(train_data, train_label)
    test_data = zip(test_data, test_label)

    # train_data = np.array(train_data)
    # train_label = np.array(train_label)
    # test_data = np.array(test_data)
    # test_label = np.array(test_label)
    return train_data, test_data


def vectorized_result(label):
    label = label[0]
    e = np.zeros((10, 1))
    if label == 10:
        e[0] = 1.0
    else:
        e[label] = 1.0
    return e


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


def main():
    train_data, test_data = getData()
    net = network.Network([400, 25, 10])
    net.SGD(train_data, 30, 10, 3.0, test_data=test_data)


if __name__ == "__main__":
    main()
