package gui;

import java.awt.*;
import javax.swing.*;




public class Main_interface {

	JFrame mainFrame = new JFrame("Game");
	Main_Panel mainPanel;
	JButton newGaButton = new JButton("开始游戏");
	JButton randButton = new JButton("排行榜");
	JButton quitButton = new JButton("退出游戏");
	public Main_interface() {

		setFrameSize();//设置界面大小
		setFramePosition();//设置界面位置
		setFramBackground("./img/background.jpg");//设置界面背景
		addButton();//添加按钮
		setFrameViewable(true);//设计界面是否可见
		mainFrame.setDefaultCloseOperation(2);
	}
	private void addButton() {
		
//		设置大小
//		newGaButton.setSize(new Dimension(100,40));
		newGaButton.setSize(100, 40);
		randButton.setSize(100, 40);
		quitButton.setSize(100, 40);
		
		
//		设置字体
		Font font = new Font("宋体",Font.BOLD,24);
		newGaButton.setFont(font);
		randButton.setFont(font);
		quitButton.setFont(font);
		
		
		
//		设置透明
		Color color = new Color(200,0,0);
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
		mainPanel.add(newGaButton);
		mainPanel.add(randButton);
		mainPanel.add(quitButton);
	}

	private void setFrameSize() {
//		获取屏幕大小
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int)screenSize.getWidth();
		int height = (int)screenSize.getHeight();
		mainFrame.setSize(width/3, height/2);
	}
	
	private void setFramePosition() {
//		设置居中
		mainFrame.setLocationRelativeTo(null); 
	}
	
	public void setFrameViewable(boolean bool) {
//		设置界面是否可见
		mainFrame.setVisible(bool);
	}
	
	private void setFramBackground(String imagPath) {
		mainPanel = new Main_Panel(imagPath);
		mainFrame.add(mainPanel);
	}
}
