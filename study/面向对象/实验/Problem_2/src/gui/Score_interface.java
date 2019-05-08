package gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Score_interface {

	Main_Frame mainFrame = new Main_Frame();
	Main_Panel mainPanel = mainFrame.get_mainPanel();

	JLabel title = new JLabel("排行榜");

	JPanel container = new JPanel();
	static ScorePanel scorePanel = new ScorePanel();
	static UserPanel userPanel = new UserPanel();
	JPanel buttonPanel = new JPanel();

	JButton scoreButton = new JButton("成绩排名");
	JButton userButton = new JButton("用户排名");
	JButton closeButton = new JButton("关闭");

	CardLayout cardLayout = new CardLayout();

	final String scoreCardName = "ScoreCard";
	final String userCardName = "UserCard";

	public Score_interface() {
		mainFrame.setFrameSize(3, 1.5);// 设置窗口大小并居中

		addComponent();// 添加组件
		title_settings();// 标签设置
		button_settings();// 按钮设置
		panel_settings();// panel设置
		scorePanel.loadRank();
		userPanel.loadRank();

		show_scorePanel();
		show_userPanel();

//		load_ScoreByScore();//加载按成绩排名的排行榜
//		load_ScoreByUser();//加载按用户排名的排行榜

		setFrameViewable(true);// 设计界面是否可见
		mainFrame.setDefaultCloseOperation(2);
	}

	private void show_userPanel() {
		scorePanel.setVisible(false);
		userPanel.setVisible(true);
	}

	private static void show_scorePanel() {
		scorePanel.setVisible(true);
		userPanel.setVisible(false);
	}

//	添加组件
	private void addComponent() {

		buttonPanel.add(scoreButton);
		buttonPanel.add(userButton);
		mainPanel.add(buttonPanel);
		mainPanel.add(title);
		container.add(scoreCardName, scorePanel);
		container.add(userCardName, userPanel);
		mainPanel.add(container);
		mainPanel.add(closeButton);
	}

//	 标签设置
	private void title_settings() {
		int w, h, x, y;
		Font font = new Font("宋体", Font.BOLD, 24);
		Color color = new Color(255, 0, 0);
		title.setFont(font);// 设置字体
		title.setForeground(color);// 设置字体颜色
		w = 75;
		h = 50;
		x = (mainFrame.getWidth() - w) / 2;
		y = 0;
		title.setBounds(x, y, w, h);// 设置位置及大小
	}

//	按钮设置
	private void button_settings() {
		int w, h, x, y;
		Font font = new Font("宋体", Font.BOLD, 20);
//		Color color = new Color(255, 0, 0);
//		设置字
		scoreButton.setFont(font);
		userButton.setFont(font);

//		退出按钮设置
		closeButton.setFont(font);// 设置字体
		w = 80;
		h = 40;
		x = (mainFrame.getWidth() - w) / 2;
		y = 635;
		closeButton.setBounds(x, y, w, h);// 设置位置及大小

//		添加监听器
		scoreButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				System.out.println("scoreButton is pressed.");
				show_scorePanel();
			}
		});
		userButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				System.out.println("userButton is pressed.");
				show_userPanel();
			}
		});

	}

//	panel设置
	private void panel_settings() {
		int w, h, x, y;
//		buttonPanel设置
		buttonPanel.setOpaque(false);// 面板透明
		buttonPanel.setLayout(new FlowLayout());// 设置布局
		w = 300;
		h = 70;
		x = (mainFrame.getWidth() - w) / 2;
		y = 50;
		buttonPanel.setBounds(x, y, w, h);// 设置位置及大小

//		container设置
		Color color = new Color(255, 255, 255, 150);
		container.setBackground(color);// 设置颜色及透明度
		container.setLayout(cardLayout);// 设置布局
		w = 500;
		h = 500;
		x = (mainFrame.getWidth() - w) / 2;
		y = 120;
		container.setBounds(x, y, w, h);// 设置位置及大小

	}

//	 设置界面是否可见
	public void setFrameViewable(boolean bool) {
		mainFrame.setVisible(bool);
	}

}
