package gui;

import java.awt.*;
import javax.swing.*;

public class Main_interface {

	Main_Frame mainFrame = new Main_Frame();
	Main_Panel mainPanel = mainFrame.get_mainPanel();
	JButton newGaButton = new JButton("开始游戏");
	JButton randButton = new JButton("排行榜");
	JButton quitButton = new JButton("退出游戏");

	public Main_interface() {
		addButton();// 添加按钮
		setButtonListener();// 给按钮添加监听
		mainFrame.setVisible(true);// 设计界面是否可见
		mainFrame.setDefaultCloseOperation(2);
	}

	private void setButtonListener() {

	}

//	添加按钮组件
	private void addButton() {
		JPanel buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);
		buttonPanel.setLayout(null);
//		设置大小
		newGaButton.setBounds(0, 0, 130, 40);
		randButton.setBounds(0, 100, 130, 40);
		quitButton.setBounds(0, 200, 130, 40);
//		设置字体
		Font font = new Font("宋体", Font.BOLD, 24);
		newGaButton.setFont(font);
		randButton.setFont(font);
		quitButton.setFont(font);
//		设置透明
		Color color = new Color(255, 0, 0);
		newGaButton.setBackground(color);
		randButton.setBackground(color);
		quitButton.setBackground(color);
		newGaButton.setForeground(color);
		randButton.setForeground(color);
		quitButton.setForeground(color);
		newGaButton.setOpaque(false);
		randButton.setOpaque(false);
		quitButton.setOpaque(false);
//		设置边界
		newGaButton.setBorder(null);
		randButton.setBorder(null);
		quitButton.setBorder(null);
//		添加按钮
		buttonPanel.add(newGaButton);
		buttonPanel.add(randButton);
		buttonPanel.add(quitButton);

		buttonPanel.setBounds(70, 180, 130, 240);
		mainPanel.add(buttonPanel);
	}

//	设置界面是否可见
	public void setFrameViewable(boolean bool) {

		mainFrame.setVisible(bool);
	}

}
