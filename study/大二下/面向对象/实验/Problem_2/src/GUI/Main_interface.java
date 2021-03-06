package GUI;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.*;

import Backstage.ScoreList;
import Backstage.UserList;

public class Main_interface implements MouseListener, Runnable {

	private Main_Frame mainFrame = new Main_Frame();
	private Main_Panel mainPanel = mainFrame.get_mainPanel();
	private JButton newGameButton = new JButton("开始游戏");
	private JButton randButton = new JButton("排行榜");
	private JButton quitButton = new JButton("退出游戏");
	private Input_userName_interface dialog = null;
	private boolean loged;
	private String userName;

	public Main_interface() {

	}

//	添加监听器
	private void setButtonListener() {
		newGameButton.addMouseListener(this);
		randButton.addMouseListener(this);
		quitButton.addMouseListener(this);
	}

//	添加按钮组件
	private void addButton() {
		JPanel buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);
		buttonPanel.setLayout(null);
//		设置大小
		newGameButton.setBounds(0, 0, 130, 40);
		randButton.setBounds(0, 100, 130, 40);
		quitButton.setBounds(0, 200, 130, 40);
//		设置字体
		Font font = new Font("宋体", Font.BOLD, 24);
		newGameButton.setFont(font);
		randButton.setFont(font);
		quitButton.setFont(font);
//		设置透明
		Color color = new Color(255, 0, 0);
		newGameButton.setBackground(new Color(0, 0, 0, 0));
		randButton.setBackground(new Color(0, 0, 0, 0));
		quitButton.setBackground(new Color(0, 0, 0, 0));
		newGameButton.setForeground(color);
		randButton.setForeground(color);
		quitButton.setForeground(color);
		newGameButton.setOpaque(false);
		randButton.setOpaque(false);
		quitButton.setOpaque(false);
//		设置边界
		newGameButton.setBorder(null);
		randButton.setBorder(null);
		quitButton.setBorder(null);
//		去掉聚焦框
		newGameButton.setFocusPainted(false);
		randButton.setFocusPainted(false);
		quitButton.setFocusPainted(false);
//		添加按钮
		buttonPanel.add(newGameButton);
		buttonPanel.add(randButton);
		buttonPanel.add(quitButton);

		buttonPanel.setBounds(70, 180, 130, 240);
		mainPanel.add(buttonPanel);
	}

//	设置界面是否可见
	public void setFrameViewable(boolean bool) {
		mainFrame.setVisible(bool);
		if (bool)
			loged = false;
	}

//	是否登录了
	public boolean isLoged() {
		return loged;
	}

	public String get_userName() {
		return userName;
	}

//	鼠标监听器
	public void mouseClicked(MouseEvent event) {
		if (event.getSource() == newGameButton) {
			dialog = new Input_userName_interface(mainFrame);
			userName = dialog.get_userName();
			if (userName!=null) {
				if (!userName.equals("")){
					loged = true;
//					//System.out.println("loged");
				}
				else {
					JLabel content = new JLabel("用户名不能为空!");
					content.setFont(new Font("宋体", Font.BOLD, 18));
					JOptionPane.showMessageDialog(null,content,"提示", 2, new ImageIcon("./img/False.png"));
				}
			}
			
//			//user = userList.get_user(dialog.get_userName());
//			//mainFrame.setVisible(false);
//			//gameInterface = new Game_interface(this, user);
		}
		if (event.getSource() == randButton) {
			new Score_interface(this);
			mainFrame.setVisible(false);
//			//scoreInterface.setFrameViewable(true);
		}
		if (event.getSource() == quitButton) {
//			//mainFrame.dispose();
			System.exit(0);
		}
	}

	public void mouseEntered(MouseEvent event) {
		Color color = new Color(255, 255, 255);
		JButton button = (JButton) event.getSource();
		button.setForeground(color);
	}

	public void mouseExited(MouseEvent event) {
		Color color = new Color(255, 0, 0);
		JButton button = (JButton) event.getSource();
		button.setForeground(color);
	}

	public void mousePressed(MouseEvent event) {
	}

	public void mouseReleased(MouseEvent event) {
		Color color = new Color(255, 0, 0);
		JButton button = (JButton) event.getSource();
		button.setForeground(color);
	}

	@Override
	public void run() {
		addButton();// 添加按钮
		setButtonListener();// 给按钮添加监听
		mainFrame.setVisible(true);// 设计界面是否可见
		mainFrame.setDefaultCloseOperation(3);
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
				try {
					UserList.ended();
					ScoreList.ended();
				} catch (IOException e) {
					e.printStackTrace();
				}
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
}
