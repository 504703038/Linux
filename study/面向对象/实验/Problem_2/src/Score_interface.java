
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;

public class Score_interface implements ActionListener {

	Main_Frame mainFrame = new Main_Frame();
	Main_Panel mainPanel = mainFrame.get_mainPanel();
	JLabel title = new JLabel("排行榜");
	JPanel container = new JPanel();
	ScorePanel scorePanel;
	UserPanel userPanel;
	JPanel buttonPanel = new JPanel();
	JButton scoreButton = new JButton("成绩排名");
	JButton userButton = new JButton("用户排名");
	JButton closeButton = new JButton("关闭");
	CardLayout cardLayout = new CardLayout();

	Main_interface parentFrame;

	final String scoreCardName = "ScoreCard";
	final String userCardName = "UserCard";

	public Score_interface(Main_interface parentFrame) {
		this.parentFrame = parentFrame;
		scorePanel  = new ScorePanel();
		userPanel = new UserPanel();
		
		mainFrame.setFrameSize(3, 1.5);// 设置窗口大小并居中
		title_settings();// 标签设置
		button_settings();// 按钮设置
		panel_settings();// panel设置
		scorePanel.loadRank();
		userPanel.loadRank();
		addComponent();// 添加组件
		setFrameViewable(true);// 设计界面是否可见
		mainFrame.setDefaultCloseOperation(2);
		mainFrame.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				parentFrame.setFrameViewable(true);
			}

			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
	
	}

	// 添加组件
	private void addComponent() {
		mainPanel.add(container);
		mainPanel.add(closeButton);
		mainPanel.add(buttonPanel);
		mainPanel.add(title);
		buttonPanel.add(scoreButton);
		buttonPanel.add(userButton);
		container.add(scoreCardName, scorePanel);
		container.add(userCardName, userPanel);
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
		scoreButton.addActionListener(this);
		userButton.addActionListener(this);
		closeButton.addActionListener(this);
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
		Color color = new Color(255, 255, 255);
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

//	切换面板
	private void showPanel(String panelName) {
		cardLayout.show(container, panelName);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
//		System.out.println("123456789");
		if (event.getSource() == scoreButton) {
			showPanel(scoreCardName);
		}
		if (event.getSource() == userButton) {
			showPanel(userCardName);
		}
		if (event.getSource() == closeButton) {
			mainFrame.dispose();
			parentFrame.setFrameViewable(true);
		}
	}
}
