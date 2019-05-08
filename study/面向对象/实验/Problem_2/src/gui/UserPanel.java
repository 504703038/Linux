package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UserPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JPanel title = new JPanel();
	JLabel userNameLabel = new JLabel("用户名",JLabel.CENTER);
	JLabel maxScoreLabel = new JLabel("分数",JLabel.CENTER);
	JLabel playTimesLabel = new JLabel("游戏次数",JLabel.CENTER);
	
	public UserPanel() {
		super();
		Color color = new Color(255, 255, 255, 150);
		this.setBackground(color);
		label_settings();
		super.add(title);
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
		title.setPreferredSize(new Dimension(w, h));//设置大小
//		title.setMinimumSize(new Dimension(500,100));

//		userNameLabel设置
//		userNameLabel.setOpaque(false);// 设置透明
		userNameLabel.setFont(font);// 设置字体
//		userNameLabel.setForeground(color);// 设置字体颜色
		userNameLabel.setPreferredSize(new Dimension(w/3-5, h));//设置大小

//		maxScoreLabel设置
//		maxScoreLabel.setOpaque(false);// 设置透明
		maxScoreLabel.setFont(font);// 设置字体
//		maxScoreLabel.setForeground(color);// 设置字体颜色
		maxScoreLabel.setPreferredSize(new Dimension(w/3-5, h));//设置大小

		
//		playTimesLabel设置
//		playTimesLabel.setOpaque(false);// 设置透明
		playTimesLabel.setFont(font);// 设置字体
//		playTimesLabel.setForeground(color);// 设置字体颜色
		playTimesLabel.setPreferredSize(new Dimension(w/3-5, h));//设置大小
		
//		添加组件
		title.add(userNameLabel);
		title.add(maxScoreLabel);
		title.add(playTimesLabel);
	}
	
	public void loadRank() {
		
	}

}
