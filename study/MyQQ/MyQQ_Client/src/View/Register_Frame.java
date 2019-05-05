package View;

/*
 * ×¢²á½çÃæ¡£
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import Bussiness.RegisterAccount;
import Common.*;

public class Register_Frame extends JFrame implements ActionListener
{
	private JPanel up = new JPanel();
	private JTextField nickname = new JTextField(15);
	private JPasswordField password = new JPasswordField(15);
	private JPasswordField confirmpassword = new JPasswordField(15);
	private JTextField phonenumber = new JTextField(15);
	private JLabel Lnickname = new JLabel("êÇ       ³Æ£º");
	private JLabel Lpassword = new JLabel("ÃÜ       Âë£º");
	private JLabel Lconfirmpassword = new JLabel("È·ÈÏÃÜÂë£º");
	private JLabel Lphonenumber = new JLabel("ÊÖ»úºÅÂë£º");
	private JPanel pnickname = new JPanel();
	private JPanel ppassword = new JPanel();
	private JPanel pconfirmpassword = new JPanel();
	private JPanel pphonenumber = new JPanel();
	private JPanel pregister = new JPanel();
	private JButton register = new JButton("×¢    ²á");
	private User user = new User();
	RegisterAccount ra = new RegisterAccount();
	
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	public Register_Frame()
	{
		super("×¢²áÕËºÅ");
		this.setIconImage(new ImageIcon("E:\\±à³Ì\\JAVA\\³ÌĞò\\MyQQ_Client\\src\\com.client.Images\\qq.png").getImage());
		//ÉèÖÃ½çÃæ²¼¾Ö
		this.setBounds(720,250,450,600);
		this.setResizable(true);
		this.setLayout(new GridLayout(7,1));
		
		//ÉèÖÃ×ÖÌå
		nickname.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 20));
		password.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 20));
		confirmpassword.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 20));
		phonenumber.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 20));
		Lnickname.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 20));
		Lpassword.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 20));
		Lconfirmpassword.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 20));
		Lphonenumber.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 20));
		password.setEchoChar('¡ñ');
		confirmpassword.setEchoChar('¡ñ');
		register.setSize(50,50);
		
		//ÏòÃæ°åÖĞÌí¼Ó×é¼ş
		pnickname.add(Lnickname);
		pnickname.add(nickname);
		ppassword.add(Lpassword);
		ppassword.add(password);
		pconfirmpassword.add(Lconfirmpassword);
		pconfirmpassword.add(confirmpassword);
		pphonenumber.add(Lphonenumber);
		pphonenumber.add(phonenumber);
		pregister.add(register);
		
		//Ìí¼Ó¼àÌıÆ÷
		nickname.addActionListener(this);
		password.addActionListener(this);
		confirmpassword.addActionListener(this);
		phonenumber.addActionListener(this);
		register.addActionListener(this);
		
		
		//±³¾°ÑÕÉ«
		pnickname.setBackground(Color.WHITE);
		ppassword.setBackground(Color.WHITE);
		pconfirmpassword.setBackground(Color.WHITE);
		pphonenumber.setBackground(Color.WHITE);
		pregister.setBackground(Color.WHITE);
		this.setBackground(Color.WHITE);
		panel1.setBackground(Color.WHITE);
		panel2.setBackground(Color.WHITE);
		
		//½«Ãæ°åÌí¼Óµ½¿ò¼Ü
		
		
		
		this.add(panel1);
		this.add(pnickname);
		this.add(ppassword);
		this.add(pconfirmpassword);
		this.add(pphonenumber);
		this.add(pregister);
		this.add(panel2);
		this.setVisible(true);
		this.setDefaultCloseOperation(2);
	}
	
	//×¢²á°´Å¥µÄ¼àÌıÆ÷
	public void actionPerformed(ActionEvent e) 
	{
	
		user.setNickname(nickname.getText());
		user.setPassword(String.valueOf(password.getPassword()));
		user.setPhonenumber(phonenumber.getText());
		user.setConfirmPassword(String.valueOf(confirmpassword.getPassword()));
		if (ra.RegisterAccount(user))
		{
			dispose();
		}
	}
	
}
