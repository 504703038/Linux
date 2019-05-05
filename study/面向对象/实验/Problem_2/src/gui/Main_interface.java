package gui;

import java.awt.*;
import javax.swing.*;



public class Main_interface {

	JFrame mainFrame = new JFrame("Game");
	JPanel panel;
	public Main_interface() {

		setFrameSize();//设置界面大小
		setFramePosition();//设置界面位置
		setFramBackground();//设置界面背景
		
		setFrameViewable(true);//设计界面是否可见
		mainFrame.setDefaultCloseOperation(2);
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
	
	private void setFramBackground() {
		ImageIcon background = new ImageIcon("background.jpg");
		JLabel backLabel = new JLabel(background);
		backLabel.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
		panel = (JPanel)mainFrame.getContentPane();
//		panel.setOpaque(false);
		panel.setLayout(new FlowLayout());
		mainFrame.getContentPane().setLayout(null);
		panel.add(new JButton("测试按钮"));
		backLabel.setOpaque(false);
		mainFrame.getLayeredPane().setLayout(null);
		mainFrame.getContentPane().add(backLabel,new Integer(Integer.MIN_VALUE));
//		mainFrame.
	}
}
