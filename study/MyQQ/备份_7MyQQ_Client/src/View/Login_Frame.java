package View;

/*
 * ��¼���档
 */

import Bussiness.*;
import Common.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login_Frame extends JFrame implements ActionListener,MouseListener
{
	private JPanel up = new JPanel();
	private JPanel down = new JPanel();
	private JPanel down_1 = new JPanel();
	private JPanel down_2 = new JPanel();
	private JPanel paccount = new JPanel();
	private JPanel ppassword = new JPanel();
	private JButton login = new JButton("��    ¼");
	private JLabel laccount = new JLabel("�˺ţ�");
	private JLabel lpassword = new JLabel("���룺");
	private JLabel RegisteredAccount = new JLabel("ע���˺�");
	private JLabel ForgetPassword = new JLabel("��������");
	private JTextField taccount = new JTextField(18);
	private JPasswordField tpassword = new JPasswordField(18);
	private JCheckBox AutomaticLogon = new JCheckBox("�Զ���¼");
	private JCheckBox ResetPassword = new JCheckBox("��ס����");
	private User user = new User();
	private UserInfoBean uib = new UserInfoBean();
	public static void main(String[] args) 
	{
		Login_Frame frame = new Login_Frame();
	}
	
	public Login_Frame()
	{
		super("QQ");
		int x=680,y=350,w=540,h=400;
		this.setIconImage(new ImageIcon("E:\\���\\JAVA\\����\\MyQQ_Client\\src\\com.client.Images\\qq.png").getImage());
//���
		this.setBounds(x,y,w,h);
		this.setLayout(null);
		this.setResizable(false);
	//�ϰ벿��
		up.setBounds(0,0,w,120);
		JLabel ima = new JLabel(new ImageIcon("E:\\���\\JAVA\\����\\MyQQ_Client\\src\\com.client.Images\\qq��¼���汳��.png"));
		up.add(ima);
//		up.setBackground(new Color(116,174,255));
		
	//�°벿��
		down.setBounds(0,120,w,250);
				//down.setBackground(Color.RED);
		down.setLayout(null);
		//�˺ź������
		down_1.setBounds(100,40,340,130);
				//down_1.setBackground(Color.BLACK);
		down_1.setLayout(new GridLayout(3,1));
		laccount.setFont(new Font("����", Font.BOLD, 20));
		lpassword.setFont(new Font("����", Font.BOLD, 20));
		taccount.setFont(new Font("����", Font.BOLD, 20));
		tpassword.setFont(new Font("����", Font.BOLD, 20));
		RegisteredAccount.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		RegisteredAccount.setForeground(Color.blue);
		ForgetPassword.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		ForgetPassword.setForeground(Color.blue);
		AutomaticLogon.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		ResetPassword.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		tpassword.setEchoChar('��');
//			��Ӽ�����
		taccount.addActionListener(this);
		tpassword.addActionListener(this);
		RegisteredAccount.addMouseListener(this);
		ForgetPassword.addMouseListener(this);
		paccount.add(laccount);
		paccount.add(taccount);
		paccount.add(RegisteredAccount);
		ppassword.add(lpassword);
		ppassword.add(tpassword);
		ppassword.add(ForgetPassword);
		down_1.add(paccount);
		down_1.add(ppassword);
			//��Ӹ�ѡ�򣨼�ס����,�Զ���¼��
		down_2.add(AutomaticLogon);
		down_2.add(ResetPassword);
		down_1.add(down_2);
		down.add(down_1);
		
		
		//��¼��ť
		login.setBounds(130,180,280,40);
		login.setFont(new Font("����", Font.PLAIN, 20));
		login.setForeground(Color.white);
		login.setBackground(new Color(4,186,251));
			//��Ӽ�����
		login.addActionListener(this);
		
		down.add(login);
		
		//������
		this.add(up);
		this.add(down);
		this.setVisible(true);
		this.setDefaultCloseOperation(3);
	}
	
//��¼�����¼��ť�ļ�����
	public void actionPerformed(ActionEvent e) 
	{
//		��¼��֤
		Login login = new Login();
		
		user.setId(taccount.getText());
		user.setPassword(String.valueOf(tpassword.getPassword()));
		uib.setUser(user);
		uib.setMesType("2");
		uib = login.check(uib);
		if (uib!=null)
		{
			new Main_Frame(uib.getUser());
			dispose();
		}
		else uib = new UserInfoBean();
	}
	
	
	
	
//����¼�������
	public void mouseClicked(MouseEvent e)
	{
		if (e.getSource()==RegisteredAccount)
		{
			new Register_Frame();
		}
		
		if (e.getSource()==ForgetPassword)
		{
			new ResetPassword_Frame();
		}
	}
	public void mouseEntered(MouseEvent e) 
	{
		if (e.getSource()==RegisteredAccount)
		{
			RegisteredAccount.setFont(new Font("΢���ź�", Font.ITALIC, 15));
		}
		
		if (e.getSource()==ForgetPassword)
		{
			ForgetPassword.setFont(new Font("΢���ź�", Font.ITALIC, 15));
		}
		
	}
	public void mouseExited(MouseEvent e) 
	{
		if (e.getSource()==RegisteredAccount)
		{
			RegisteredAccount.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		}
		
		if (e.getSource()==ForgetPassword)
		{
			ForgetPassword.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		}
	}
	public void mousePressed(MouseEvent e) 
	{
		
	}
	public void mouseReleased(MouseEvent e) 
	{
		
	}
}
