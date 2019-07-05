# 习题9.4
import numpy as np
import matplotlib.pyplot as plt
import matplotlib
matplotlib.rcParams['font.family'] = 'SimHei'
matplotlib.rcParams['font.sans-serif'] = ['SimHei']


def Draw(pcolor, nt_point, nt_text, nt_size):
    plt.plot(
        x, y, 'k', label="物体$a$", color=pcolor, linewidth=3, linestyle="-")
    plt.plot(x, z, "b--", label="物体$b$", linewidth=1)
    plt.xlabel('时间(s)')
    plt.ylabel('位移(m)')
    plt.title("物体位移曲线绘制")


def XY_Axis(x_start, x_end, y_start, y_end):
    plt.xlim(x_start, x_end)
    plt.ylim(y_start, y_end)
    plt.xticks([1, 2, 3, 4, 5], ['$1$', '$2$', '$3$', '$4$', '$5$'])


x1, v1, a1 = 0, 1, 2
x2, v2, a2 = 2, 3, 0

x = np.linspace(0.0, 8.0, 100)
y = x1 + v1 * x + 0.5 * a1 * x**2
z = x2 + v2 * x + 0.5 * a2 * x**2
note_point, note_text, note_size = (1, np.cos(2 * np.pi) * np.exp(-1) + 0.8), (
    1, 1.4), 14
fig = plt.figure(figsize=(8, 6), facecolor="white")
plt.subplot(111)
Draw("red", note_point, note_text, note_size)
XY_Axis(0, 5, 0, 16)
plt.legend()
plt.savefig('./实验/实验9/习题9.4.JPG')
plt.show()
