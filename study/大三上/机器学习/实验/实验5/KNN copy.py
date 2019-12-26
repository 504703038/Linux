import mat4py
import numpy as np


def output(data):
    print("")
    for i in range(20):
        lis = []
        for j in range(20):
            lis.append(data[i * 20 + j])
        print(lis)


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
    train_data = np.array(train_data)
    train_label = np.array(train_label)
    test_data = np.array(test_data)
    test_label = np.array(test_label)
    return train_data, train_label, test_data, test_label


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


def KNN(test_data, train_data, train_label, k):
    train_data_size = train_data.shape[0]
    dif_mat = np.tile(test_data, (train_data_size, 1)) - train_data
    sqr_dif_mat = dif_mat ** 2
    sqr_dis = sqr_dif_mat.sum(axis=1)
    sorted_idx = sqr_dis.argsort()
    class_cnt = {}
    best_class = 0
    class_cnt[best_class] = 0
    for i in range(k):
        tmp_class = train_label[sorted_idx[i]][0]
        # output(train_data[sorted_idx[i]])
        # print(tmp_class)
        if not (tmp_class in class_cnt):
            class_cnt[tmp_class] = 0
        class_cnt[tmp_class] += 1
        if class_cnt[tmp_class] > class_cnt[best_class]:
            best_class = tmp_class
    return best_class


def main():
    train_data, train_label, test_data, test_label = getData()
    num = test_data.shape[0]
    err = 0
    for i in range(num):
        tmp_class = KNN(test_data[i], train_data, train_label, 7)
        if tmp_class != test_label[i]:
            err += 1
    print(err)
    print('accuracy:', 1 - 1.0 * err / num)
    # 调试
    # test_case = 3
    # output(test_data[test_case])
    # print(test_label[test_case])
    # tmp_class = KNN(test_data[test_case], train_data, train_label, 7)
    # print(tmp_class)


if __name__ == "__main__":
    main()
