# 习题9.5
import numpy as np
import matplotlib.pyplot as plt
import matplotlib
matplotlib.rcParams['font.family'] = 'SimHei'
matplotlib.rcParams['font.sans-serif'] = ['SimHei']
labels = np.array(['速度', '力量', '发球', '经验', '防守', '技术'])
nAttr = 6
data = np.array([8, 8, 7, 5, 4, 6])  # 数据值
angles = np.linspace(0, 2 * np.pi, nAttr, endpoint=False)
data = np.concatenate((data, [data[0]]))
angles = np.concatenate((angles, [angles[0]]))
fig = plt.figure(facecolor="white")
plt.subplot(111, polar=True)
plt.plot(angles, data, 'bo-', color='g', linewidth=2)
plt.fill(angles, data, facecolor='g', alpha=0.25)
plt.thetagrids(angles * 180 / np.pi, labels)
plt.figtext(0.52, 0.95, '乒乓选手能力值雷达图', ha='center')
plt.grid(True)
plt.show()
