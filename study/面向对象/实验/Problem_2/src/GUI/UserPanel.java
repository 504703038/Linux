package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Backstage.User;
import Backstage.UserList;

public class UserPanel extends JPanel {

	JPanel title = new JPanel();
	JPanel scorePanel = new JPanel();
	JScrollPane scrollPane = new JScrollPane(scorePanel);
	JLabel userNameLabel = new JLabel("用户名", JLabel.CENTER);
	JLabel maxScoreLabel = new JLabel("分数", JLabel.CENTER);
	JLabel playTimesLabel = new JLabel("游戏次数", JLabel.CENTER);

	public UserPanel() {
		super();
		Color color = new Color(255, 255, 255);
		this.setBackground(color);
		this.setLayout(null);
		label_settings();
		this.add(title);
		this.add(scrollPane);
	}

	private void label_settings() {
		int x, y, w, h;
		Font font = new Font("宋体", Font.BOLD, 20);
		Color color = new Color(255, 0, 0);
		w = 500;
		h = 40;
//		title设置
		title.setOpaque(false);// 设置透明
//		title.setLayout(new FlowLayout(FlowLayout.CENTER));// 设置布局
//		title.setBackground(color);//设置背景颜色
//		title.setPreferredSize(new Dimension(w, h));//设置大小
		title.setBounds(0, 0, w, h);
//		title.setMinimumSize(new Dimension(500,100));

//		userNameLabel设置
//		userNameLabel.setOpaque(false);// 设置透明
		userNameLabel.setFont(font);// 设置字体
//		userNameLabel.setForeground(color);// 设置字体颜色
		userNameLabel.setPreferredSize(new Dimension(w / 3 - 5, h));// 设置大小

//		maxScoreLabel设置
//		maxScoreLabel.setOpaque(false);// 设置透明
		maxScoreLabel.setFont(font);// 设置字体
//		maxScoreLabel.setForeground(color);// 设置字体颜色
		maxScoreLabel.setPreferredSize(new Dimension(w / 3 - 5, h));// 设置大小

//		playTimesLabel设置
//		playTimesLabel.setOpaque(false);// 设置透明
		playTimesLabel.setFont(font);// 设置字体
//		playTimesLabel.setForeground(color);// 设置字体颜色
		playTimesLabel.setPreferredSize(new Dimension(w / 3 - 5, h));// 设置大小

//		添加组件
		title.add(userNameLabel);
		title.add(maxScoreLabel);
		title.add(playTimesLabel);
	}

	public void loadRank() {
		Font font = new Font("宋体", Font.BOLD, 18);
		ArrayList<User> userList = UserList.get_userList();
		int w = 501;
		int h = 455;

		scrollPane.setBounds(0, 45, w, h);
		scrollPane.setHorizontalScrollBar(null);
//		scrollPane.setBackground(new Color(255,0,0));
		
		scorePanel.removeAll();
		scorePanel.setLayout(new GridLayout(userList.size(),1));
		scorePanel.setBounds(0, 0, w, 10);
		scorePanel.setBackground(new Color(0, 0, 0));

		JLabel[] user_name_labels = new JLabel[userList.size()];
		JLabel[] max_score_labels = new JLabel[userList.size()];
		JLabel[] play_times_labels = new JLabel[userList.size()];
		JPanel[] List = new JPanel[userList.size()];
		int cnt = 0;
		for (User user : userList) {
//			System.out.println(user.get_name() + "\t" + "" + user.get_max_score() + "\t" + "" + user.get_play_times());
			List[cnt] = new JPanel(new GridLayout(1, 3));
			user_name_labels[cnt] = new JLabel(user.get_name(), JLabel.CENTER);
			max_score_labels[cnt] = new JLabel("" + user.get_max_score(), JLabel.CENTER);
			play_times_labels[cnt] = new JLabel("" + user.get_play_times(), JLabel.CENTER);

			user_name_labels[cnt].setFont(font);
			max_score_labels[cnt].setFont(font);
			play_times_labels[cnt].setFont(font);

			List[cnt].add(user_name_labels[cnt]);
			List[cnt].add(max_score_labels[cnt]);
			List[cnt].add(play_times_labels[cnt]);
			List[cnt].setPreferredSize(new Dimension(500, 40));
			List[cnt].setBackground(new Color(255, 255, 255));
			scorePanel.add(List[cnt]);
			cnt++;
		}
	}

}
