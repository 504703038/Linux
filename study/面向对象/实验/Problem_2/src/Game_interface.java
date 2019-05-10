
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;

public class Game_interface implements Runnable {

	private Main_Frame mainFrame = new Main_Frame();
	private Main_Panel mainPanel = mainFrame.get_mainPanel();
	private JLabel title = new JLabel("请进行第i次尝试", JLabel.CENTER);
	private JTextField input = new JTextField();
	private JButton submitButton = new JButton("确定");

	private boolean inputed;

	public Game_interface() {
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
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				inputed = true;
			}
		});
	}

//	添加组件
	private void addComponent() {

//		设置组件位置及大小
		int w = mainFrame.getWidth();
		title.setBounds(w / 2 - 150, 100, 300, 40);
		input.setBounds(w / 2 - 100, 230, 200, 45);
		submitButton.setBounds(w / 2 - 50, 400, 100, 40);

		mainPanel.add(title);
		mainPanel.add(input);
		mainPanel.add(submitButton);
	}

	// 设置标签内容
	public void setGameLabel(String text) {
		title.setText(text);
		input.setText("");
		inputed = false;
	}

//	设置界面是否可见
	public void setFrameViewable(boolean bool) {
		mainFrame.setVisible(bool);
	}

	@Override
	public void run() {
		inputed = false;
		title_settings();// 标签设置
		input_settings();// 输入框设置
		submit_settings();// 按钮设置
		addComponent();// 添加组件
		setFrameViewable(true);// 设计界面是否可见
		mainFrame.setDefaultCloseOperation(2);
	}

	public boolean isInputed() {
		return inputed;
	}

	public int getInput() {
		return Integer.parseInt(input.getText());
	}

}
