

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Input_userName_interface extends JDialog implements ActionListener {
	JLabel label = new JLabel("请输入用户名：", JLabel.LEFT);
	JTextField input = new JTextField();
	JButton confirm = new JButton("确定");
	JFrame parentFrame;
	String userName;

	public Input_userName_interface(JFrame owner) {
		super(owner, true);
		parentFrame = owner;
		this.setTitle("用户名输入");
		this.setSize(new Dimension(300, 250));
		this.setLayout(null);
		this.setLocationRelativeTo(null);

		title_settings();
		input_settings();
		button_settings();

		this.add(label);
		this.add(input);
		this.add(confirm);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	private void button_settings() {
		int x, y, w, h;
		Font font = new Font("宋体", Font.BOLD, 20);
		confirm.setFont(font);
		w = 100;
		h = 30;
		x = (this.getWidth() - w) / 2;
		y = 150;
		confirm.setBounds(x, y, w, h);
		
		confirm.addActionListener(this);
	}

	private void input_settings() {
		int x, y, w, h;
		Font font = new Font("宋体", Font.BOLD, 20);
		input.setFont(font);
		w = 150;
		h = 30;
		x = (this.getWidth() - w) / 2;
		y = 90;
		input.setBounds(x, y, w, h);
	}

	private void title_settings() {
		int x, y, w, h;
		Font font = new Font("宋体", Font.BOLD, 20);
		label.setFont(font);
		w = 150;
		h = 50;
		x = (this.getWidth() - w) / 2;
		y = 20;
		label.setBounds(x, y, w, h);
	}
	
	public String get_userName() {
		return userName;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
//		this.setVisible(false);
		userName = input.getText();
		this.dispose();
	}

}
