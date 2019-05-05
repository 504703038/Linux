package View;

/*
 * �һ�������档
 */

import javax.swing.*;
import javax.swing.text.*;
import Bussiness.*;
import Common.*;
import java.awt.*;
import java.awt.event.*;

public class ResetPassword_Frame extends JFrame implements ActionListener
{
	private JTextField account = new JTextField(15);
	private JTextField phonenumber = new JTextField(16);
	private JPasswordField newpassword = new JPasswordField(15);
	private JPasswordField confirmnewpassword = new JPasswordField(15);
	private JButton reset = new JButton("ȷ  ��");
	private JLabel laccount = new JLabel("       �˺ţ�");
	private JLabel lphonenumber = new JLabel("  �ֻ�����:");
	private JLabel lnewpassword = new JLabel("   �����룺   ");
	private JLabel lconfirmnewpassword = new JLabel("ȷ�������룺");
	private JPanel pnewpassword = new JPanel();
	private JPanel pconfirmnewpassword = new JPanel();
	private JPanel paccount = new JPanel();
	private JPanel pphonenumber = new JPanel();
	private JPanel preset = new JPanel();
	private User user = new User();
	
	public ResetPassword_Frame()
	{
		super("�һ�����");
		this.setResizable(true);
		this.setBounds(670,250,600,600);
		this.setLayout(new GridLayout(7,1));
		this.setIconImage(new ImageIcon("E:\\���\\JAVA\\����\\MyQQ_Client\\src\\com.client.Images\\qq.png").getImage());
		//��������
		reset.setFont(new Font("΢���ź�",Font.PLAIN,20));
		laccount.setFont(new Font("΢���ź�",Font.PLAIN,20));
		lphonenumber.setFont(new Font("΢���ź�",Font.PLAIN,20));
		lnewpassword.setFont(new Font("΢���ź�",Font.PLAIN,20));
		lconfirmnewpassword.setFont(new Font("΢���ź�",Font.PLAIN,20));
		phonenumber.setFont(new Font("΢���ź�",Font.PLAIN,20));
		account.setFont(new Font("΢���ź�",Font.BOLD,20));
		newpassword.setFont(new Font("΢���ź�",Font.BOLD,20));
		confirmnewpassword.setFont(new Font("΢���ź�",Font.BOLD,20));
		newpassword.setEchoChar('��');
		confirmnewpassword.setEchoChar('��');
		reset.setSize(50,50);
		
		//������
		pnewpassword.add(lnewpassword);
		pnewpassword.add(newpassword);
		pconfirmnewpassword.add(lconfirmnewpassword);
		pconfirmnewpassword.add(confirmnewpassword);
		paccount.add(laccount);
		paccount.add(account);
		pphonenumber.add(lphonenumber);
		pphonenumber.add(phonenumber);
		preset.add(reset);
		reset.addActionListener(this);
		this.add(new JPanel());
		this.add(paccount);
		this.add(pphonenumber);
		this.add(pnewpassword);
		this.add(pconfirmnewpassword);
		this.add(preset);
		this.add(new JPanel());
		this.setVisible(true);
		this.setDefaultCloseOperation(2);
	}
	public void actionPerformed(ActionEvent e) 
	{
		new ResetPassword(user);
		dispose();
	}
}
