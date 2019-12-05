import numpy as np
import mat4py


# 从文件中读取数据
def getDate():
    data = mat4py.loadmat(
        '/home/shifa-yang/Desktop/work/study/大三上/机器学习/实验/实验4/lms.mat')
    X = data['X']
    y = data['y']
    X = np.array(X)
    y = np.array(y)
    return X, y


class NeuralNetwork:
    def __init__(self, layers):
        # 初始化权值
        self.V = np.random.random((layers[0] + 1, layers[1])) * 2 - 1
        self.W = np.random.random((layers[1], layers[2])) * 2 - 1

    def sigmoid(self, x):
        return 1 / (1 + np.exp(-x))

    def dsigmoid(self, x):
        return x*(1-x)

    def train(self, X, y, lr=0.1, epochs=10000):
        # 添加偏置
        tmp = np.ones([X.shape[0], X.shape[1] + 1])
        tmp[:, 0:-1] = X
        X = tmp

        # 进行权值训练更新
        for n in range(epochs):
            # L0-输入层,L1-隐藏层,L2-输出层
            L0 = X
            L1 = self.sigmoid(np.dot(L0, self.V))
            L2 = self.sigmoid(np.dot(L1, self.W))
            # 计算误差
            L2_error = self.CalculateError(L2)
            if n % 200 == 0:
                print('Error:' + str(np.mean(np.abs(L2_error))))
            L2_delta = L2_error * self.dsigmoid(L2)
            L1_error = L2_delta.dot(self.W.T)
            L1_delta = L1_error * self.dsigmoid(L1)
            self.V -= lr*L0.T.dot(L1_delta)
            self.W -= lr * L1.T.dot(L2_delta)

    def CalculateError(self, L2):
        tmp = np.zeros([L2.shape[0], L2.shape[1] + 1])
        tmp[:, 1:-1] = L2[:, 1:]
        tmp[:, -1] = L2[:, 0]
        L2 = np.argmax(L2, axis=1)
        L2 = np.array([L2]).T
        L2_error = (y - L2)
        return L2_error


if __name__ == "__main__":
    X, y = getDate()
    bp = NeuralNetwork([400, 25, 10])
    bp.train(X, y, epochs=20000)
