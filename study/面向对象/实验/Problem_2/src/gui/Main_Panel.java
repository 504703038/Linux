package gui;

import java.awt.*;

import javax.swing.*;

public class Main_Panel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ImageIcon icon;
	Image img;

	public Main_Panel(String imgPath) {
		icon = new ImageIcon(imgPath);
		img = icon.getImage();
	}

	protected void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	}
}
