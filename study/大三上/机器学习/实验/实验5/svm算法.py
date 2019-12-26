from sklearn.svm import SVC
import random
import numpy as np


class SVM:
    def __init__(self, train_data, train_label, test_data, test_label):
        self.train_data = np.array(train_data)
        self.train_label = np.array(train_label)
        self.test_data = np.array(test_data)
        self.test_label = np.array(test_label)

    def classify(self):
        clf = SVC(kernel='poly')
        clf.fit(self.train_data, self.train_label)
        test_result = list(clf.predict(self.test_data))
        return test_result
