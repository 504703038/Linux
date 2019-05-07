package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

public class Score_interface {

	Main_Frame mainFrame = new Main_Frame();
	Main_Panel mainPanel = mainFrame.get_mainPanel();

	JLabel title = new JLabel("排行榜");

	JPanel container = new JPanel();
//	JPanel scorePanel = new JPanel();
//	JPanel userPanel = new JPanel();
	JPanel buttonPanel = new JPanel();

	JButton score_button = new JButton("成绩排名");
	JButton user_button = new JButton("用户排名");
	JButton close_button = new JButton("关闭");

	public Score_interface() {

		mainFrame.setFrameSize(3, 1.5);

		title_settings();
		button_settings();
		panel_settings();
//		load_ScoreByScore();//加载按成绩排名的排行榜
//		load_ScoreByUser();//加载按用户排名的排行榜
		addComponent();
		setFrameViewable(true);// 设计界面是否可见
		mainFrame.setDefaultCloseOperation(2);
	}

//	添加组件
	private void addComponent() {
		buttonPanel.add(score_button);
		buttonPanel.add(user_button);

		buttonPanel.setBounds(100, 100, 100, 100);
		title.setBounds(0, 0, 100, 100);

		mainPanel.add(buttonPanel);
		mainPanel.add(title);
	}

//	 标签设置
	private void title_settings() {
		Font font = new Font("宋体", Font.BOLD, 24);
		Color color = new Color(255, 0, 0);
		title.setFont(font);
		title.setForeground(color);
	}

//	按钮设置
	private void button_settings() {
		Font font = new Font("宋体", Font.BOLD, 20);
		Color color = new Color(255, 0, 0);
//		设置字
		score_button.setFont(font);
		user_button.setFont(font);

	}

//	panel设置
	private void panel_settings() {
		// TODO Auto-generated method stub

	}

//	加载按成绩排名的排行榜
	public void load_ScoreRank() {

	}

//	加载按用户排名的排行榜
	public void load_UserRank() {

	}

//	 设置界面是否可见
	public void setFrameViewable(boolean bool) {
		mainFrame.setVisible(bool);
	}

}
