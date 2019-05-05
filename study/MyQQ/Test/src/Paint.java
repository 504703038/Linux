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
		} // 加载系统界面风格
		catch (Exception e) {
		}
		new MyPaint();
	}
}

class MyPaint extends JFrame {
	
	private JButton choices[]; // 按钮数组
	private String names[] = { 
			"New", // 新建
			"Open", // 打开
			"Save", // 保存
			"Pencil", // 自由画笔
			"Line", // 直线
			"Rect", // 空心矩形
			"fRect", // 实心矩形
			"Oval", // 空心椭圆
			"fOval", // 实心椭圆
			"Circle", // 圆形
			"fCircle", // 实心圆形
			"RoundRect", // 圆角矩形
			"frRect", // 实心圆角矩形
			"3DRect", // 3D 矩形
			"f3DRect", // 实心 3D矩形
			"Cube", // 立方体
			"Rubber", // 橡皮擦
			"bgColor", // 背景色
			"Color", // 画笔颜色
			"Stroke", // 画笔大小
			"Word" // 文本输入
	};
	GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	String styleNames[] = ge.getAvailableFontFamilyNames(); // 加载系统字体
	private Icon items[];
	private String tipText[] = { " 新建 ", " 打开 ", " 保存 ", " 自由画笔 ", " 直线 ", " 空心矩形 ", " 实心矩形 ", " 空心椭圆 ", " 实心椭圆 ", " 圆"," 实心圆 ", " 空心圆角矩形 ", " 实心圆角矩形 ", "3D 矩形 ", "3D 矩形 ", "3D 长方体 ", " 橡皮 ", " 设置背景色 ", " 画笔颜色 ", " 画笔粗细 "," 添加文字 " }; // 按钮提示说明
	JToolBar buttonPanel; // 定义按钮面板
	private JLabel statusBar; // 显示鼠标状态的提示条
	private DrawPanel drawingArea; // 定义画图区域
	private int width = 850, height = 550; // 画图区域初始大小
	drawings[] itemList = new drawings[5000]; // 用来存放基本图形的数组
	private int currentChoice = 3; // 设置初始画笔为自由笔画
	int index = 0; // 已绘制图形数目
	private Color color = Color.black; // 画笔颜色
	int R, G, B; // 颜色值
	int f1, f2; // 存放当前字体风格
	String style1; // 存放当前字体
	private float stroke = 1.0f; // 设置画笔粗细
	static int thickness = 10; // 立方体宽度
	JCheckBox bold, italic; // 定义字体风格选择框
//	MyUndoManager myUndo;
	JComboBox styles; // 字体选择框
	Toolkit kit = Toolkit.getDefaultToolkit(); // 获取 Toolkit 实例

	public MyPaint() 
	{
		setTitle(" 画图板 1.0 开发者预览版 ");
		setLocation(200, 100); // 画图板窗口起始位置
		setSize(850, 550); // 画图板大小
		setVisible(true);
		
		setCursor(new Cursor(Cursor.HAND_CURSOR)); // 设置画图板鼠标样式
		drawingArea = new DrawPanel();
		
		setDefaultCloseOperation(2);
		

		
		items = new ImageIcon[names.length];
		// 创建各种基本图形的按钮
		choices = new JButton[names.length];
		
		buttonPanel = new JToolBar(JToolBar.HORIZONTAL);
		
		ButtonHandler handler = new ButtonHandler();
		
		ButtonHandler1 handler1 = new ButtonHandler1();
		
		buttonPanel.setBackground(Color.BLUE); // 工具栏背景色设置
		
		// 导入图形图标，图标存放在项目文件夹下的 Icons 目录内
		for (int i = 0; i < choices.length; i++) {
			items[i] = new ImageIcon("E:\\学习\\大一下\\JAVA\\课设\\相关文件\\Images\\聊天界面\\工具栏\\表情\\1\\表情"+i+".png");
			choices[i] = new JButton(items[i]);
			choices[i].setToolTipText(tipText[i]);
			choices[i].setBackground(Color.BLUE); // 按钮背景色设置
			buttonPanel.add(choices[i]);
		}
		
		// 将动作侦听器加入按钮里面
		for (int i = 3; i < choices.length - 4; i++) {
			choices[i].addActionListener(handler);
		}
		
		choices[choices.length - 4].addActionListener(handler1);
		choices[choices.length - 3].addActionListener(handler1);
		choices[choices.length - 2].addActionListener(handler1);
		choices[choices.length - 1].addActionListener(handler1);
		
		// 字体风格选择
		styles = new JComboBox(styleNames);
		styles.setMaximumRowCount(10);
		styles.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				style1 = styleNames[styles.getSelectedIndex()];
			}
		});
		
		// 字体选择
		bold = new JCheckBox(" 加粗 ");
		italic = new JCheckBox(" 倾斜 ");
		checkBoxHandler cHandler = new checkBoxHandler();
		bold.addItemListener(cHandler);
		italic.addItemListener(cHandler);
		bold.setBackground(new Color(0, 255, 0));
		italic.setBackground(new Color(0, 255, 0));
		buttonPanel.add(bold);
		buttonPanel.add(italic);
		buttonPanel.addSeparator();
		buttonPanel.add(new JLabel(" 字体 :"));
		buttonPanel.add(styles);
		buttonPanel.setFloatable(false);
		styles.setMinimumSize(new Dimension(100, 20)); // 字体选框大小设置
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

	// 按钮侦听器 ButtonHanler 类，内部类，用来侦听基本按钮的操作
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

	// 按钮侦听器 ButtonHanler1 类，用来侦听颜色选择、画笔粗细设置、文字输入按钮的操作
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
				JOptionPane.showMessageDialog(null, " 在鼠标点击处添加文本 ", " 添加文本 ", JOptionPane.INFORMATION_MESSAGE);
				currentChoice = 17;
				createNewItem();
				repaint();
			}
		}
	}

	// 鼠标事件 mouseA 类，继承了 MouseAdapter ，用来完成鼠标相应事件操作
	class mouseA extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			statusBar.setText(" 鼠标点击 :[" + e.getX() + "," + e.getY() + "]"); // 设置状态提示
			itemList[index].x1 = itemList[index].x2 = e.getX();
			itemList[index].y1 = itemList[index].y2 = e.getY();
			// 如果当前选择的图形是随笔画或者橡皮擦，则进行下面的操作
			if (currentChoice == 3 || currentChoice == 16) {
				itemList[index].x1 = itemList[index].x2 = e.getX();
				itemList[index].y1 = itemList[index].y2 = e.getY();
				index++;
				createNewItem();
			}
			// 如果当前选择的图形式文字输入，则进行下面操作
			if (currentChoice == 17) {
				itemList[index].x1 = e.getX();
				itemList[index].y1 = e.getY();
				String input;
				input = JOptionPane.showInputDialog(" 输入要添加的文本内容 ");
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
			statusBar.setText(" 鼠标松开 :[" + e.getX() + "," + e.getY() + "]");
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
			statusBar.setText(" 鼠标进入 :[" + e.getX() + "," + e.getY() + "]");
		}

		public void mouseExited(MouseEvent e) {
			statusBar.setText(" 鼠标移出 :[" + e.getX() + "," + e.getY() + "]");
		}
	}

	// 鼠标事件 mouseB 类继承了 MouseMotionAdapter ，用来完成鼠标拖动和鼠标移动时的相应操作
	class mouseB extends MouseMotionAdapter {
		public void mouseDragged(MouseEvent e) {
			statusBar.setText(" 画图 :[" + e.getX() + "," + e.getY() + "]");
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
			statusBar.setText(" 鼠标位置 :[" + e.getX() + "," + e.getY() + "]");
		}
	}

	// 选择字体风格时候用到的事件侦听器类，加入到字体风格的选择框中
	private class checkBoxHandler implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			if (e.getSource() == bold) // 设置字体为加粗
				if (e.getStateChange() == ItemEvent.SELECTED)
					f1 = Font.BOLD;
				else
					f1 = Font.PLAIN;
			if (e.getSource() == italic) // 设置字体为倾斜
				if (e.getStateChange() == ItemEvent.SELECTED)
					f2 = Font.ITALIC;
				else
					f2 = Font.PLAIN;
		}
	}

	// 画图面板类，用来画图
	class DrawPanel extends JPanel {
		public DrawPanel() {
			setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
			setBackground(Color.white); // 设置画图面板初始颜色为白色
			addMouseListener(new mouseA());
			addMouseMotionListener(new mouseB());
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g;
			// 定义画笔
			int j = 0;
			while (j <= index) {
				draw(g2d, itemList[j]);
				j++;
			}
		}

		void draw(Graphics2D g2d, drawings i) {
			i.draw(g2d); // 将画笔传入到各个子类中，用来完成各自的绘图
		}
	}

	// 新建一个画图基本单元对象的程序段
	void createNewItem() {
		if (currentChoice == 17) // 选择文本时鼠标为文本输入形
			drawingArea.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		else // 其他情况十字形
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

	// 选择当前颜色程序段
	public void chooseColor() {
		color = JColorChooser.showDialog(MyPaint.this, " 选择画笔颜色 ", color);
		R = color.getRed();
		G = color.getGreen();
		B = color.getBlue();
		itemList[index].R = R;
		itemList[index].G = G;
		itemList[index].B = B;
	}



	// 选择当前线条粗细程序段
	public void setStroke() {
		String input;
		input = JOptionPane.showInputDialog(" 请输入画笔粗细值： ");
		stroke = Float.parseFloat(input);
		itemList[index].stroke = stroke;
	}



}
