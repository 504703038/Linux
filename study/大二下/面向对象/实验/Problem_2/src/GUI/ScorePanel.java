package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.*;

import Backstage.Score;
import Backstage.ScoreList;

public class ScorePanel extends JPanel {

	JPanel title = new JPanel();
	JPanel scorePanel = new JPanel();
	JScrollPane scrollPane = new JScrollPane(scorePanel);
	JLabel userNameLabel = new JLabel("用户名", JLabel.CENTER);
	JLabel scoreLabel = new JLabel("分数", JLabel.CENTER);

	public ScorePanel() {
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
//		title.setMinimumSize(new Dimension(500,100));
		title.setBounds(0, 0, w, h);

//		userNameLabel设置
//		userNameLabel.setOpaque(false);// 设置透明
		userNameLabel.setFont(font);// 设置字体
//		userNameLabel.setForeground(color);// 设置字体颜色
		userNameLabel.setPreferredSize(new Dimension(w / 2 - 5, h));// 设置大小

//		scoreLabel设置
//		scoreLabel.setOpaque(false);// 设置透明
		scoreLabel.setFont(font);// 设置字体
//		scoreLabel.setForeground(color);// 设置字体颜色
		scoreLabel.setPreferredSize(new Dimension(w / 2 - 5, h));// 设置大小

//		添加组件
		title.add(userNameLabel);
		title.add(scoreLabel);
	}

	public void loadRank() {
		Font font = new Font("宋体", Font.BOLD, 18);
		ArrayList<Score> scoreList = ScoreList.get_scoArrayList();
		int w = 501;
		int h = 455;

		scrollPane.setBounds(0, 45, w, h);
		scrollPane.setHorizontalScrollBar(null);

		scorePanel.removeAll();
		scorePanel.setLayout(new GridLayout(scoreList.size(), 1));
		scorePanel.setBounds(0, 0, w, 10);
		scorePanel.setBackground(new Color(255, 255, 255));

		JLabel[] user_name_labels = new JLabel[scoreList.size()];
		JLabel[] score_labels = new JLabel[scoreList.size()];
		JPanel[] List = new JPanel[scoreList.size()];
		int cnt = 0;
		for (Score score : scoreList) {
//			System.out.println(score.get_username() + "\t" + "" + score.get_score());
			List[cnt] = new JPanel(new GridLayout(1, 2));
			user_name_labels[cnt] = new JLabel(score.get_username(), JLabel.CENTER);
			score_labels[cnt] = new JLabel("" + score.get_score(), JLabel.CENTER);

			user_name_labels[cnt].setFont(font);
			score_labels[cnt].setFont(font);

			List[cnt].add(user_name_labels[cnt]);
			List[cnt].add(score_labels[cnt]);
			List[cnt].setPreferredSize(new Dimension(500, 40));
			List[cnt].setBackground(new Color(255, 255, 255));
			scorePanel.add(List[cnt]);
			cnt++;
		}
	}
}
