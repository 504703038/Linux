import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.undo.*;

public class Paint {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} // ����ϵͳ������
		catch (Exception e) {
		}
		new MyPaint();
	}
}

class MyPaint extends JFrame {
	
	private JButton choices[]; // ��ť����
	private String names[] = { 
			"New", // �½�
			"Open", // ��
			"Save", // ����
			"Pencil", // ���ɻ���
			"Line", // ֱ��
			"Rect", // ���ľ���
			"fRect", // ʵ�ľ���
			"Oval", // ������Բ
			"fOval", // ʵ����Բ
			"Circle", // Բ��
			"fCircle", // ʵ��Բ��
			"RoundRect", // Բ�Ǿ���
			"frRect", // ʵ��Բ�Ǿ���
			"3DRect", // 3D ����
			"f3DRect", // ʵ�� 3D����
			"Cube", // ������
			"Rubber", // ��Ƥ��
			"bgColor", // ����ɫ
			"Color", // ������ɫ
			"Stroke", // ���ʴ�С
			"Word" // �ı�����
	};
	GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	String styleNames[] = ge.getAvailableFontFamilyNames(); // ����ϵͳ����
	private Icon items[];
	private String tipText[] = { " �½� ", " �� ", " ���� ", " ���ɻ��� ", " ֱ�� ", " ���ľ��� ", " ʵ�ľ��� ", " ������Բ ", " ʵ����Բ ", " Բ"," ʵ��Բ ", " ����Բ�Ǿ��� ", " ʵ��Բ�Ǿ��� ", "3D ���� ", "3D ���� ", "3D ������ ", " ��Ƥ ", " ���ñ���ɫ ", " ������ɫ ", " ���ʴ�ϸ "," ������� " }; // ��ť��ʾ˵��
	JToolBar buttonPanel; // ���尴ť���
	private JLabel statusBar; // ��ʾ���״̬����ʾ��
	private DrawPanel drawingArea; // ���廭ͼ����
	private int width = 850, height = 550; // ��ͼ�����ʼ��С
	drawings[] itemList = new drawings[5000]; // ������Ż���ͼ�ε�����
	private int currentChoice = 3; // ���ó�ʼ����Ϊ���ɱʻ�
	int index = 0; // �ѻ���ͼ����Ŀ
	private Color color = Color.black; // ������ɫ
	int R, G, B; // ��ɫֵ
	int f1, f2; // ��ŵ�ǰ������
	String style1; // ��ŵ�ǰ����
	private float stroke = 1.0f; // ���û��ʴ�ϸ
	static int thickness = 10; // ��������
	JCheckBox bold, italic; // ����������ѡ���
//	MyUndoManager myUndo;
	JComboBox styles; // ����ѡ���
	Toolkit kit = Toolkit.getDefaultToolkit(); // ��ȡ Toolkit ʵ��

	public MyPaint() 
	{
		setTitle(" ��ͼ�� 1.0 ������Ԥ���� ");
		setLocation(200, 100); // ��ͼ�崰����ʼλ��
		setSize(850, 550); // ��ͼ���С
		setVisible(true);
		
		setCursor(new Cursor(Cursor.HAND_CURSOR)); // ���û�ͼ�������ʽ
		drawingArea = new DrawPanel();
		
		setDefaultCloseOperation(2);
		

		
		items = new ImageIcon[names.length];
		// �������ֻ���ͼ�εİ�ť
		choices = new JButton[names.length];
		
		buttonPanel = new JToolBar(JToolBar.HORIZONTAL);
		
		ButtonHandler handler = new ButtonHandler();
		
		ButtonHandler1 handler1 = new ButtonHandler1();
		
		buttonPanel.setBackground(Color.BLUE); // ����������ɫ����
		
		// ����ͼ��ͼ�꣬ͼ��������Ŀ�ļ����µ� Icons Ŀ¼��
		for (int i = 0; i < choices.length; i++) {
			items[i] = new ImageIcon("E:\\ѧϰ\\��һ��\\JAVA\\����\\����ļ�\\Images\\�������\\������\\����\\1\\����"+i+".png");
			choices[i] = new JButton(items[i]);
			choices[i].setToolTipText(tipText[i]);
			choices[i].setBackground(Color.BLUE); // ��ť����ɫ����
			buttonPanel.add(choices[i]);
		}
		
		// ���������������밴ť����
		for (int i = 3; i < choices.length - 4; i++) {
			choices[i].addActionListener(handler);
		}
		
		choices[choices.length - 4].addActionListener(handler1);
		choices[choices.length - 3].addActionListener(handler1);
		choices[choices.length - 2].addActionListener(handler1);
		choices[choices.length - 1].addActionListener(handler1);
		
		// ������ѡ��
		styles = new JComboBox(styleNames);
		styles.setMaximumRowCount(10);
		styles.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				style1 = styleNames[styles.getSelectedIndex()];
			}
		});
		
		// ����ѡ��
		bold = new JCheckBox(" �Ӵ� ");
		italic = new JCheckBox(" ��б ");
		checkBoxHandler cHandler = new checkBoxHandler();
		bold.addItemListener(cHandler);
		italic.addItemListener(cHandler);
		bold.setBackground(new Color(0, 255, 0));
		italic.setBackground(new Color(0, 255, 0));
		buttonPanel.add(bold);
		buttonPanel.add(italic);
		buttonPanel.addSeparator();
		buttonPanel.add(new JLabel(" ���� :"));
		buttonPanel.add(styles);
		buttonPanel.setFloatable(false);
		styles.setMinimumSize(new Dimension(100, 20)); // ����ѡ���С����
		styles.setMaximumSize(new Dimension(120, 20));
		Container c = getContentPane();
		c.add(buttonPanel, BorderLayout.NORTH);
		c.add(drawingArea, BorderLayout.CENTER);
		statusBar = new JLabel();
		c.add(statusBar, BorderLayout.SOUTH);
		createNewItem();
		setSize(width, height);
		show();
	}

	// ��ť������ ButtonHanler �࣬�ڲ��࣬��������������ť�Ĳ���
	public class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			for (int j = 3; j < choices.length - 4; j++) {
				if (e.getSource() == choices[j]) {
					currentChoice = j;
					createNewItem();
					repaint();
				}
			}
		}
	}

	// ��ť������ ButtonHanler1 �࣬����������ɫѡ�񡢻��ʴ�ϸ���á��������밴ť�Ĳ���
	public class ButtonHandler1 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
//			if (e.getSource() == choices[choices.length - 4]) {
//				SetbgColor();
//			}
			if (e.getSource() == choices[choices.length - 3]) {
				chooseColor();
			}
			if (e.getSource() == choices[choices.length - 2]) {
				setStroke();
			}
			if (e.getSource() == choices[choices.length - 1]) {
				JOptionPane.showMessageDialog(null, " �������������ı� ", " ����ı� ", JOptionPane.INFORMATION_MESSAGE);
				currentChoice = 17;
				createNewItem();
				repaint();
			}
		}
	}

	// ����¼� mouseA �࣬�̳��� MouseAdapter ��������������Ӧ�¼�����
	class mouseA extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			statusBar.setText(" ����� :[" + e.getX() + "," + e.getY() + "]"); // ����״̬��ʾ
			itemList[index].x1 = itemList[index].x2 = e.getX();
			itemList[index].y1 = itemList[index].y2 = e.getY();
			// �����ǰѡ���ͼ������ʻ�������Ƥ�������������Ĳ���
			if (currentChoice == 3 || currentChoice == 16) {
				itemList[index].x1 = itemList[index].x2 = e.getX();
				itemList[index].y1 = itemList[index].y2 = e.getY();
				index++;
				createNewItem();
			}
			// �����ǰѡ���ͼ��ʽ�������룬������������
			if (currentChoice == 17) {
				itemList[index].x1 = e.getX();
				itemList[index].y1 = e.getY();
				String input;
				input = JOptionPane.showInputDialog(" ����Ҫ��ӵ��ı����� ");
				itemList[index].s1 = input;
				itemList[index].x2 = f1;
				itemList[index].y2 = f2;
				itemList[index].s2 = style1;
				index++;
				currentChoice = 17;
				createNewItem();
				drawingArea.repaint();
			}
		}

		public void mouseReleased(MouseEvent e) {
			statusBar.setText(" ����ɿ� :[" + e.getX() + "," + e.getY() + "]");
			if (currentChoice == 3 || currentChoice == 16) {
				itemList[index].x1 = e.getX();
				itemList[index].y1 = e.getY();
			}
			itemList[index].x2 = e.getX();
			itemList[index].y2 = e.getY();
			repaint();
			index++;
			createNewItem();
		}

		public void mouseEntered(MouseEvent e) {
			statusBar.setText(" ������ :[" + e.getX() + "," + e.getY() + "]");
		}

		public void mouseExited(MouseEvent e) {
			statusBar.setText(" ����Ƴ� :[" + e.getX() + "," + e.getY() + "]");
		}
	}

	// ����¼� mouseB ��̳��� MouseMotionAdapter �������������϶�������ƶ�ʱ����Ӧ����
	class mouseB extends MouseMotionAdapter {
		public void mouseDragged(MouseEvent e) {
			statusBar.setText(" ��ͼ :[" + e.getX() + "," + e.getY() + "]");
			if (currentChoice == 3 || currentChoice == 16) {
				itemList[index - 1].x1 = itemList[index].x2 = itemList[index].x1 = e.getX();
				itemList[index - 1].y1 = itemList[index].y2 = itemList[index].y1 = e.getY();
				index++;
				createNewItem();
			} else {
				itemList[index].x2 = e.getX();
				itemList[index].y2 = e.getY();
			}
			repaint();
		}

		public void mouseMoved(MouseEvent e) {
			statusBar.setText(" ���λ�� :[" + e.getX() + "," + e.getY() + "]");
		}
	}

	// ѡ��������ʱ���õ����¼��������࣬���뵽�������ѡ�����
	private class checkBoxHandler implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			if (e.getSource() == bold) // ��������Ϊ�Ӵ�
				if (e.getStateChange() == ItemEvent.SELECTED)
					f1 = Font.BOLD;
				else
					f1 = Font.PLAIN;
			if (e.getSource() == italic) // ��������Ϊ��б
				if (e.getStateChange() == ItemEvent.SELECTED)
					f2 = Font.ITALIC;
				else
					f2 = Font.PLAIN;
		}
	}

	// ��ͼ����࣬������ͼ
	class DrawPanel extends JPanel {
		public DrawPanel() {
			setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
			setBackground(Color.white); // ���û�ͼ����ʼ��ɫΪ��ɫ
			addMouseListener(new mouseA());
			addMouseMotionListener(new mouseB());
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g;
			// ���廭��
			int j = 0;
			while (j <= index) {
				draw(g2d, itemList[j]);
				j++;
			}
		}

		void draw(Graphics2D g2d, drawings i) {
			i.draw(g2d); // �����ʴ��뵽���������У�������ɸ��ԵĻ�ͼ
		}
	}

	// �½�һ����ͼ������Ԫ����ĳ����
	void createNewItem() {
		if (currentChoice == 17) // ѡ���ı�ʱ���Ϊ�ı�������
			drawingArea.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		else // �������ʮ����
			drawingArea.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		switch (currentChoice) {
		case 3:
			itemList[index] = new Pencil();
			break;
		case 4:
			itemList[index] = new Line();
			break;
		case 5:
			itemList[index] = new Rect();
			break;
		case 6:
			itemList[index] = new fillRect();
			break;
		case 7:
			itemList[index] = new Oval();
			break;
		case 8:
			itemList[index] = new fillOval();
			break;
		case 9:
			itemList[index] = new Circle();
			break;
		case 10:
			itemList[index] = new fillCircle();
			break;
		case 11:
			itemList[index] = new RoundRect();
			break;
		case 12:
			itemList[index] = new fillRoundRect();
			break;
		case 13:
			itemList[index] = new Rect3D();
			break;
		case 14:
			itemList[index] = new fillRect3D();
			break;
		case 15:
			itemList[index] = new Cube();
			break;
		case 16:
			itemList[index] = new Rubber();
			break;
		case 17:
			itemList[index] = new Word();
			break;
		}
		itemList[index].type = currentChoice;
		itemList[index].R = R;
		itemList[index].G = G;
		itemList[index].B = B;
		itemList[index].stroke = stroke;
		itemList[index].thickness = thickness;
	}

	// ѡ��ǰ��ɫ�����
	public void chooseColor() {
		color = JColorChooser.showDialog(MyPaint.this, " ѡ�񻭱���ɫ ", color);
		R = color.getRed();
		G = color.getGreen();
		B = color.getBlue();
		itemList[index].R = R;
		itemList[index].G = G;
		itemList[index].B = B;
	}



	// ѡ��ǰ������ϸ�����
	public void setStroke() {
		String input;
		input = JOptionPane.showInputDialog(" �����뻭�ʴ�ϸֵ�� ");
		stroke = Float.parseFloat(input);
		itemList[index].stroke = stroke;
	}



}
