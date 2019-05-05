package View;

/*
 * ע����档
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
	private JLabel Lnickname = new JLabel("��       �ƣ�");
	private JLabel Lpassword = new JLabel("��       �룺");
	private JLabel Lconfirmpassword = new JLabel("ȷ�����룺");
	private JLabel Lphonenumber = new JLabel("�ֻ����룺");
	private JPanel pnickname = new JPanel();
	private JPanel ppassword = new JPanel();
	private JPanel pconfirmpassword = new JPanel();
	private JPanel pphonenumber = new JPanel();
	private JPanel pregister = new JPanel();
	private JButton register = new JButton("ע    ��");
	private User user = new User();
	RegisterAccount ra = new RegisterAccount();
	
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	public Register_Frame()
	{
		super("ע���˺�");
		this.setIconImage(new ImageIcon("E:\\���\\JAVA\\����\\MyQQ_Client\\src\\com.client.Images\\qq.png").getImage());
		//���ý��沼��
		this.setBounds(720,250,450,600);
		this.setResizable(true);
		this.setLayout(new GridLayout(7,1));
		
		//��������
		nickname.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		password.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		confirmpassword.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		phonenumber.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		Lnickname.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		Lpassword.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		Lconfirmpassword.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		Lphonenumber.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		password.setEchoChar('��');
		confirmpassword.setEchoChar('��');
		register.setSize(50,50);
		
		//�������������
		pnickname.add(Lnickname);
		pnickname.add(nickname);
		ppassword.add(Lpassword);
		ppassword.add(password);
		pconfirmpassword.add(Lconfirmpassword);
		pconfirmpassword.add(confirmpassword);
		pphonenumber.add(Lphonenumber);
		pphonenumber.add(phonenumber);
		pregister.add(register);
		
		//��Ӽ�����
		nickname.addActionListener(this);
		password.addActionListener(this);
		confirmpassword.addActionListener(this);
		phonenumber.addActionListener(this);
		register.addActionListener(this);
		
		
		//������ɫ
		pnickname.setBackground(Color.WHITE);
		ppassword.setBackground(Color.WHITE);
		pconfirmpassword.setBackground(Color.WHITE);
		pphonenumber.setBackground(Color.WHITE);
		pregister.setBackground(Color.WHITE);
		this.setBackground(Color.WHITE);
		panel1.setBackground(Color.WHITE);
		panel2.setBackground(Color.WHITE);
		
		//�������ӵ����
		
		
		
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
	
	//ע�ᰴť�ļ�����
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
