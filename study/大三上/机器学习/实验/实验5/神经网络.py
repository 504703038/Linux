import numpy as np
import random
import mat4py


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


class NeuralNetwork:
    def init_network(self, train_data, train_label, test_data, test_label):
        self.K = 10
        self.N = 4000
        self.M = 1000
        self.BATCHSIZE = 100
        self.reg_factor = 1e-2
        self.stepsize = 1e-3
        self.loss_list = []

        self.W1 = 0.01 * np.random.randn(20 * 20, 25)
        self.b1 = 0.01 * np.random.randn(1, 25)

        self.W2 = 0.01 * np.random.randn(25, 10)
        self.b2 = 0.01 * np.random.randn(1, 10)

        self.W3 = 0.01 * np.random.randn(10, 1)
        self.b3 = 0.01 * np.random.randn(1, 1)

        self.train_data = np.append(train_data, train_label, axis=1)
        self.test_img_list = test_data
        self.test_label_list = test_label

    def predict(self):
        hidden_layer1 = np.maximum(0, np.matmul(
            self.test_img_list, self.W1) + self.b1)

        hidden_layer2 = np.maximum(0, np.matmul(
            hidden_layer1, self.W2) + self.b2)

        scores = np.maximum(0, np.matmul(hidden_layer2, self.W3) + self.b3)

        prediction = np.argmax(scores, axis=1)
        prediction = np.reshape(prediction, (1000, 1))
        # print(prediction.shape)
        # print(self.test_label_list.shape)
        accuracy = np.mean(prediction == self.test_label_list)
        print('The accuracy is:  ', accuracy)
        return

    def train(self):

        for i in range(10000):
            np.random.shuffle(self.train_data)
            img_list = self.train_data[:self.BATCHSIZE, :-1]
            label_list = self.train_data[:self.BATCHSIZE, -1:]
            print("Train Time: ", i)
            self.train_network(img_list, label_list)

    def train_network(self, img_batch_list, label_batch_list):

        # calculate softmax
        train_example_num = img_batch_list.shape[0]
        hidden_layer1 = np.maximum(0, np.matmul(
            img_batch_list, self.W1) + self.b1)

        hidden_layer2 = np.maximum(0, np.matmul(
            hidden_layer1, self.W2) + self.b2)

        scores = np.maximum(0, np.matmul(hidden_layer2, self.W3) + self.b3)

        scores_e = np.exp(scores)
        scores_e_sum = np.sum(scores_e, axis=1, keepdims=True)

        probs = scores_e / scores_e_sum

        loss_list_tmp = np.zeros((train_example_num, 1))
        for i in range(train_example_num):
            loss_list_tmp[i] = scores_e[i][int(
                label_batch_list[i])] / scores_e_sum[i]
        loss_list = -np.log(loss_list_tmp)

        loss = np.mean(loss_list, axis=0)[0] + \
            0.5 * self.reg_factor * np.sum(self.W1 * self.W1) + \
            0.5 * self.reg_factor * np.sum(self.W2 * self.W2) + \
            0.5 * self.reg_factor * np.sum(self.W3 * self.W3)

        self.loss_list.append(loss)
        print(loss, " ", len(self.loss_list))
        # backpropagation

        dscore = np.zeros((train_example_num, self.K))
        for i in range(train_example_num):
            dscore[i][:] = probs[i][:]
            dscore[i][int(label_batch_list[i])] -= 1

        dscore /= train_example_num

        dW3 = np.dot(hidden_layer2.T, dscore)
        db3 = np.sum(dscore, axis=0, keepdims=True)

        dh2 = np.dot(dscore, self.W3.T)
        dh2[hidden_layer2 <= 0] = 0

        dW2 = np.dot(hidden_layer1.T, dh2)
        db2 = np.sum(dh2, axis=0, keepdims=True)

        dh1 = np.dot(dh2, self.W2.T)
        dh1[hidden_layer1 <= 0] = 0

        dW1 = np.dot(img_batch_list.T, dh1)
        db1 = np.sum(dh1, axis=0, keepdims=True)

        dW3 += self.reg_factor * self.W3
        dW2 += self.reg_factor * self.W2
        dW1 += self.reg_factor * self.W1

        self.W3 += -self.stepsize * dW3
        self.W2 += -self.stepsize * dW2
        self.W1 += -self.stepsize * dW1

        self.b3 += -self.stepsize * db3
        self.b2 += -self.stepsize * db2
        self.b1 += -self.stepsize * db1

        return


def main():
    train_data, train_label, test_data, test_label = getData()
    bp = NeuralNetwork()
    bp.init_network(train_data, train_label, test_data, test_label)
    bp.predict()


if __name__ == "__main__":
    main()
