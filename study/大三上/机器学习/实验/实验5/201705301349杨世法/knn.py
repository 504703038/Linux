import mat4py
import numpy as np


class KNN(object):
    def __init__(self, train_data, train_label, k):
        self.k = k
        self.train_data = np.array(train_data)
        self.train_label = np.array(train_label)

    def classify(self, test_data):
        test_data = np.array(test_data)
        train_data_size = self.train_data.shape[0]
        dif_mat = np.tile(test_data, (train_data_size, 1)) - self.train_data
        sqr_dif_mat = dif_mat ** 2
        sqr_dis = sqr_dif_mat.sum(axis=1)
        sorted_idx = sqr_dis.argsort()
        class_cnt = {}
        best_class = 0
        class_cnt[best_class] = 0
        for i in range(self.k):
            tmp_class = self.train_label[sorted_idx[i]][0]
            if not (tmp_class in class_cnt):
                class_cnt[tmp_class] = 0
            class_cnt[tmp_class] += 1
            if class_cnt[tmp_class] > class_cnt[best_class]:
                best_class = tmp_class
        return best_class

    def work(self, test_data):
        test_result = []
        num = len(test_data)
        for i in range(num):
            test_result.append(self.classify(test_data[i]))
        return test_result
