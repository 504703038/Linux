//package test;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

public class asd extends JFrame implements ActionListener, MouseListener, MouseMotionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String shape = "直线";
	Color color;
	Graphics g;
	int x1, y1, x2, y2, x3, y3;
	JColorChooser JCC=new JColorChooser();
	JButton colorchooser = new JButton();
	JComboBox jcb;

	public static void main(String[] args) {
		new asd();
	}

	public asd() {
		// MouseAction m = new MouseAction();
		String[] kind = new String[] { "直线", "矩形", "三角形", "曲线", "清空", "橡皮擦" };
//		Color[] color = { Color.black, Color.BLUE, Color.GREEN, Color.YELLOW };
		this.setSize(827, 647);
		this.setTitle("画板");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBackground(Color.white);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(new BorderLayout());

//		ImageIcon im = new ImageIcon("image/bg.jpg");
//		JLabel jla = new JLabel(im);
//		this.getLayeredPane().add(jla, new Integer(Integer.MIN_VALUE));
//		jla.setBounds(0, 0, 827, 627);
		JPanel jpanel = (JPanel) this.getContentPane();
		jpanel.setOpaque(false);

		JPanel west = new JPanel();
		west.setPreferredSize(new Dimension(189, 0));
//		JPanel tmp1 = new JPanel();
//		tmp1.setPreferredSize(new Dimension(189, 120));
//		tmp1.setOpaque(false);
//		JPanel tmp2 = new JPanel();
//		tmp2.setPreferredSize(new Dimension(18, 0));
//		tmp2.setOpaque(false);
		JPanel westcenter = new JPanel();
		westcenter.setOpaque(false);
		west.setLayout(new BorderLayout());
		west.setBackground(Color.CYAN);
//		west.add(tmp1, BorderLayout.NORTH);
//		west.add(tmp2, BorderLayout.WEST);
		west.add(westcenter, BorderLayout.CENTER);
		for (int i = 0; i < kind.length; i++) {
			JButton jbu = new JButton(kind[i]);
			jbu.setBorderPainted(false);
			jbu.setFont(new Font("楷体", 15, 15));
			jbu.setPreferredSize(new Dimension(130, 40));
			jbu.setBackground(Color.orange);
			westcenter.add(jbu, BorderLayout.CENTER);
			jbu.addActionListener(this);
		}
//		for (int i = 0; i < color.length; i++) {
//			JButton jbu = new JButton();
//			jbu.setBorderPainted(false);
//			jbu.setBackground(color[i]);
//			jbu.setPreferredSize(new Dimension(130, 40));
//			westcenter.add(jbu, BorderLayout.CENTER);
//			jbu.addActionListener(this);
//		}

		colorchooser.setBackground(Color.black);
		colorchooser.setPreferredSize(new Dimension(130, 40));
		westcenter.add(colorchooser, BorderLayout.CENTER);
		colorchooser.addActionListener(this);
		
		String chuxi [] = new String[100];
		for(int i = 0;i<chuxi.length;i++) {
			chuxi[i] = String.valueOf(i+1);
		}
		jcb = new JComboBox(chuxi);
		jcb.setPreferredSize(new Dimension(130,40));
		westcenter.add(jcb, BorderLayout.CENTER);

		west.setOpaque(false);
		this.add(west, BorderLayout.WEST);

		JPanel east = new JPanel();
		east.setPreferredSize(new Dimension(630, 0));
		east.setBackground(Color.CYAN);
		this.add(east, BorderLayout.EAST);
		east.setOpaque(false);
		east.setLayout(null);

		JPanel board = new JPanel();
		board.setBounds(0, 0, 630, 615);
		board.setBorder(new EtchedBorder(EtchedBorder.RAISED));
//		board.setBackground(Color.black);
		board.setOpaque(false);
		east.add(board);

//		this.setVisible(true);

		g = board.getGraphics();
		board.addMouseListener(this);
		board.addMouseMotionListener(this);

		// m.setGraphics(g);
		// m.setJFrame(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		if (e.getActionCommand().equals("")) {
			JButton button = (JButton) e.getSource();
			color=JColorChooser.showDialog(null, "颜色选择", button.getBackground());
			this.colorchooser.setBackground(color);
			System.out.println("color = " + color);
		}else {
			JButton button = (JButton) e.getSource();
			shape = button.getActionCommand();
			System.out.println("String = " + shape);
		}
		if (shape.equals("清空")) {
			this.repaint();
			shape = "直线";
		} 
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO 自动生成的方法存根

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO 自动生成的方法存根
		// g=(Graphics2D) this.getGraphics();
		// g.setColor(color);
		x1 = e.getX();
		y1 = e.getY();
		g.setColor(color);
		System.out.println(x1 + " " + y2);

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO 自动生成的方法存根
		((Graphics2D) g).setStroke(new BasicStroke((float) Integer.parseUnsignedInt((String) this.jcb.getSelectedItem())));
		x2 = e.getX();
		y2 = e.getY();
		if (shape.equals("直线")) {
			g.drawLine(x1, y1, x2, y2);
		}
		if (shape.equals("矩形")) {
			g.drawRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
		}
		if (shape.equals("三角形")) {
			g.drawLine(x1, y1, x2, y2);
			g.drawLine(x2, y2, x2 - Math.abs(x2 - x1) * 2, y2);
			g.drawLine(x2 - Math.abs(x2 - x1) * 2, y2, x1, y1);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO 自动生成的方法存根

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO 自动生成的方法存根

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO 自动生成的方法存根
		((Graphics2D) g).setStroke(new BasicStroke((float) Integer.parseUnsignedInt((String) this.jcb.getSelectedItem())));
		x2 = e.getX();
		y2 = e.getY();
		if (shape.equals("曲线")) {
			g.drawLine(x1, y1, x2, y2);
			x1 = x2;
			y1 = y2;
		}
		if (shape.equals("橡皮擦")) {
			((Graphics2D) g).setStroke(new BasicStroke(40));
			((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g.setColor(Color.white);
			g.drawLine(x1, y1, x2, y2);
			x1 = x2;
			y1 = y2;
		}
		// if (shape.equals("直线")) {
		// g.setColor(Color.WHITE);
		// g.drawLine(x1, y1, x3, y3);
		// x3 = e.getX();
		// y3 = e.getY();
		// g.setColor(color);
		// g.drawLine(x1, y1, x3, y3);
		//
		// }
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO 自动生成的方法存根

	}
}
