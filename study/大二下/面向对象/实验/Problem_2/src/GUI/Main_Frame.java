package GUI;


import java.awt.*;

import javax.swing.*;

public class Main_Frame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Main_Panel mainPanel;

	public Main_Frame() {
		super("Game");
		setFrameSize(3, 2);// 设置界面大小并居中
		setFramBackground("./img/background.jpg");// 设置界面背景
//		super.setVisible(true);
	}

//	设置窗口大小
	public void setFrameSize(double x, double y) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int w = (int) (screenSize.getWidth() / x);
		int h = (int) (screenSize.getHeight() / y);
		super.setSize(w, h);
		super.setResizable(false);
		super.setLocationRelativeTo(null);
	}

//	设置背景
	private void setFramBackground(String imagPath) {
		mainPanel = new Main_Panel(imagPath);
		super.add(mainPanel);
		mainPanel.setLayout(null);
	}

//	获取mainPanel
	public Main_Panel get_mainPanel() {
		return mainPanel;
	}

}
