import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main {
	static MyPanel panel1 = new MyPanel();
	static MyPanel panel2 = new MyPanel();
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		JPanel Panel = new JPanel();
		frame.setSize(new Dimension(300,300));
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		Color color  = new Color(255,255,255,100);
		
		
		panel1.setBackground(color);
		panel1.add(new JLabel("card 1",JLabel.CENTER));
//		color  = new Color(255,0,0,100);
		panel2.setBackground(color);
		panel2.add(new JLabel("card 22222222",JLabel.CENTER));
		
		CardLayout card = new CardLayout();
		
		Panel.setLayout(card);
		Panel.add(panel1,"1");
		Panel.add(panel2,"2");
		panel1.setVisible(true);
		panel2.setVisible(true);
		JButton button1 = new JButton("B1");
		JButton button2 = new JButton("B2");
		button1.setBounds(0, 0, 100, 100);
		button2.setBounds(100, 100, 100, 100);
		button1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				card.show(Panel, "1");
			}
		});
		
		button2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
//				panel1.setVisible(false);
//				panel2.setVisible(true);
				card.show(Panel, "2");
			}
		});
		
		
		frame.add(button1);
		frame.add(button2);
		frame.add(Panel);
//		frame.setBackground(new Color(255,0,0));
		frame.setVisible(true);
		frame.setDefaultCloseOperation(2);
	}

}
