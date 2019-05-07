package gui;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class Game_interface {

	Main_Frame mainFrame = new Main_Frame();
	Main_Panel mainPanel = mainFrame.get_mainPanel();
	JLabel title = new JLabel("请进行第i次尝试");
	JTextField input = new JTextField();
	JButton submitButton = new JButton("确定");

	public Game_interface() {

		title_settings();// 标签设置
		input_settings();// 输入框设置
		submit_settings();// 按钮设置
		addComponent();// 添加组件
		setFrameViewable(true);// 设计界面是否可见
		mainFrame.setDefaultCloseOperation(2);
	}

//	标签设置
	private void title_settings() {
		Font font = new Font("宋体", Font.BOLD, 26);
		Color color = new Color(0, 0, 255);
		title.setFont(font);
		title.setForeground(color);
	}

//	输入框设置
	private void input_settings() {
		Font font = new Font("宋体", Font.BOLD, 26);
		input.setFont(font);
		input.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				int tmp = e.getKeyChar();
				if (tmp != 8 && (tmp > 57 || tmp < 48 || input.getText().length() > 3)) {
					e.consume();
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

	}

//	按钮设置
	private void submit_settings() {
		Font font = new Font("宋体", Font.BOLD, 20);
//		Color color = new Color(255,255,255);
		submitButton.setFont(font);
	}

//	添加组件
	private void addComponent() {

//		设置组件位置及大小
		int w = mainFrame.getWidth();
		title.setBounds(w / 2 - 105, 100, 210, 40);
		input.setBounds(w / 2 - 100, 230, 200, 45);
		submitButton.setBounds(w / 2 - 50, 400, 100, 40);

		mainPanel.add(title);
		mainPanel.add(input);
		mainPanel.add(submitButton);
	}

	// 设置标签内容
	public void set_title(String s) {
		title.setText(s);
	}

//	设置界面是否可见
	public void setFrameViewable(boolean bool) {
		mainFrame.setVisible(bool);
	}

}
