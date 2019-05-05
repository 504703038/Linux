
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

public class DrawingBoard extends JWindow implements ActionListener, MouseListener, MouseMotionListener 
{
	private static final long serialVersionUID = 1L;
	String shape = "直线";
	Color color;
	Graphics painter;
	int x1, y1, x2, y2;
	JColorChooser JCC=new JColorChooser();
	JButton colorchooser = new JButton();
	JComboBox chooser_painter_size;
	JPanel paint_panel = new JPanel();
	JToolBar tools = new JToolBar(JToolBar.HORIZONTAL);
	
//	public static void main(String[] args) {
//		new DrawBoard();
//	}

	public DrawingBoard() 
	{
		String[] button_name = new String[] { "直线", "矩形", "曲线", "清空", "橡皮擦" };
		
		this.setSize(827, 647);
//		this.setTitle("画板");
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBackground(Color.white);
		this.setLocationRelativeTo(null);
//		this.setResizable(false);
		this.setLayout(new BorderLayout());
		
		//添加组件
		this.add(tools, BorderLayout.NORTH);
		this.add(paint_panel, BorderLayout.CENTER);
		
		tools.setPreferredSize(new Dimension(this.getWidth(), 30));
		tools.setBackground(Color.white);
		tools.setFloatable(false);
		
		for (int i = 0; i < button_name.length; i++) 
		{
			JButton button = new JButton(button_name[i]);
			button.setBorderPainted(false);
			button.setFont(new Font("微软雅黑",Font.PLAIN,15));
//			button.setPreferredSize(new Dimension(130, 40));
//			button.setBackground(Color.orange);
			tools.add(button);
			button.addActionListener(this);
			JLabel label = new JLabel("        ");
			label.setBackground(Color.white);
			tools.add(label);
		}
		
		//颜色选择器
		JLabel label = new JLabel("      画笔颜色：");
		label.setFont(new Font("微软雅黑",Font.PLAIN,15));
		label.setBackground(Color.white);
		tools.add(label);
		colorchooser.setBackground(Color.black);
		colorchooser.setPreferredSize(new Dimension(130, 40));
		colorchooser.addActionListener(this);
		colorchooser.setPreferredSize(new Dimension(30, 40));
		tools.add(colorchooser);
		//画笔大小
		String painter_size [] = new String[100];
		for(int i = 0;i<painter_size.length;i++) 
		{
			painter_size[i] = String.valueOf(i+1);
		}
		chooser_painter_size = new JComboBox(painter_size);
		
		chooser_painter_size.setPreferredSize(new Dimension(10,30));
		
		label = new JLabel("           画笔大小：");
		label.setFont(new Font("微软雅黑",Font.PLAIN,15));
		label.setBackground(Color.white);
		tools.add(label);
		tools.add(chooser_painter_size);
		label = new JLabel("                                                           ");
		label.setBackground(Color.white);
		tools.add(label);
		//画板
		paint_panel.setBackground(Color.WHITE);
		//设置可见
		this.setVisible(true);
		//画笔
		painter = paint_panel.getGraphics();
		paint_panel.addMouseListener(this);
		paint_panel.addMouseMotionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getActionCommand().equals("")) 
		{
			JButton button = (JButton) e.getSource();
			color=JColorChooser.showDialog(null, "颜色选择", button.getBackground());
			this.colorchooser.setBackground(color);
			System.out.println("color = " + color);
		}
		else 
		{
			JButton button = (JButton) e.getSource();
			shape = button.getActionCommand();
			System.out.println("String = " + shape);
		}
		if (shape.equals("清空")) 
		{
			this.repaint();
			shape = "直线";
			color=colorchooser.getBackground();
		} 
	}

	public void mouseClicked(MouseEvent e) {}
	public void mousePressed(MouseEvent e) 
	{
		x1 = e.getX();
		y1 = e.getY();
		painter.setColor(color);
		System.out.println(x1 + " " + y2);

	}

	public void mouseReleased(MouseEvent e) 
	{
		((Graphics2D) painter).setStroke(new BasicStroke((float) Integer.parseUnsignedInt((String) this.chooser_painter_size.getSelectedItem())));
		x2 = e.getX();
		y2 = e.getY();
		if (shape.equals("直线")) {
			painter.drawLine(x1, y1, x2, y2);
		}
		if (shape.equals("矩形")) {
			painter.drawRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
		}
		if (shape.equals("三角形")) {
			painter.drawLine(x1, y1, x2, y2);
			painter.drawLine(x2, y2, x2 - Math.abs(x2 - x1) * 2, y2);
			painter.drawLine(x2 - Math.abs(x2 - x1) * 2, y2, x1, y1);
		}
	}

	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	
	public void mouseDragged(MouseEvent e) 
	{
		((Graphics2D) painter).setStroke(new BasicStroke((float) Integer.parseUnsignedInt((String) this.chooser_painter_size.getSelectedItem())));
		x2 = e.getX();
		y2 = e.getY();
		if (shape.equals("曲线")) 
		{
			painter.drawLine(x1, y1, x2, y2);
			x1 = x2;
			y1 = y2;
		}
		if (shape.equals("橡皮擦")) 
		{
			((Graphics2D) painter).setStroke(new BasicStroke(40));
			((Graphics2D) painter).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			painter.setColor(Color.white);
			painter.drawLine(x1, y1, x2, y2);
			x1 = x2;
			y1 = y2;
		}
	}
	
	public void mouseMoved(MouseEvent e) {}
}
