package View;

/*
 * 登录界面。
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
	private JButton login = new JButton();
	private JLabel laccount = new JLabel("账号：");
	private JLabel lpassword = new JLabel("密码：");
	private JLabel RegisteredAccount = new JLabel("注册账号");
	private JLabel ForgetPassword = new JLabel("忘记密码");
	private JTextField taccount = new JTextField(18);
	private JPasswordField tpassword = new JPasswordField(18);
	private JCheckBox AutomaticLogon = new JCheckBox("自动登录");
	private JCheckBox ResetPassword = new JCheckBox("记住密码");
	private User user = new User();
	private UserInfoBean uib = new UserInfoBean();
	public static void main(String[] args) 
	{
		java.awt.EventQueue.invokeLater(new Runnable() 
		   {
		        public void run() 
		        {
		            //写在要运行代码的最前端
		            try
		            {
		            	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());  
		                Login_Frame frame = new Login_Frame();
		            } 
		            catch (Exception e) {e.printStackTrace();}
		        }
		    });
		
//		Login_Frame frame = new Login_Frame();
		
	}
	
	public Login_Frame()
	{
		super("QQ");
		int x=680,y=350,w=540,h=400;
		this.setIconImage(new ImageIcon("E:\\编程\\JAVA\\程序\\MyQQ_Client\\src\\com.client.Images\\qq.png").getImage());
//框架
		this.setBounds(x,y,w,h);
		this.setLayout(null);
		this.setResizable(false);
		down.setBackground(Color.white);
		down_1.setBackground(Color.white);
		down_2.setBackground(Color.white);
		paccount.setBackground(Color.white);
		ppassword.setBackground(Color.white);
		AutomaticLogon.setBackground(Color.white);
		ResetPassword.setBackground(Color.white);
	//上半部分
		up.setBounds(0,0,w,120);
		JLabel ima = new JLabel(new ImageIcon("E:\\编程\\JAVA\\程序\\MyQQ_Client\\src\\com.client.Images\\qq登录界面背景.png"));
		up.add(ima);
//		up.setBackground(new Color(116,174,255));
		
	//下半部分
		down.setBounds(0,120,w,250);
				//down.setBackground(Color.RED);
		down.setLayout(null);
		//账号和密码框
		down_1.setBounds(100,40,340,130);
				//down_1.setBackground(Color.BLACK);
		down_1.setLayout(new GridLayout(3,1));
		laccount.setFont(new Font("宋体", Font.BOLD, 18));
		lpassword.setFont(new Font("宋体", Font.BOLD, 18));
		taccount.setFont(new Font("宋体", Font.BOLD, 18));
		tpassword.setFont(new Font("宋体", Font.BOLD, 18));
		RegisteredAccount.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		RegisteredAccount.setForeground(Color.BLUE);
		ForgetPassword.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		ForgetPassword.setForeground(Color.BLUE);
		AutomaticLogon.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		ResetPassword.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		tpassword.setEchoChar('●');
//			添加监听器
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
			//添加复选框（记住密码,自动登录）
		down_2.add(AutomaticLogon);
		down_2.add(ResetPassword);
		down_1.add(down_2);
		down.add(down_1);
		
		
		//登录按钮
		login.setIcon(new ImageIcon("E:\\学习\\大一下\\JAVA\\课设\\相关文件\\Images\\登录按钮.png"));
		login.setBounds(130,180,280,40);
		//添加监听器
		login.addActionListener(this);
		
		down.add(login);
		
		//加入框架
		this.add(up);
		this.add(down);
		this.setVisible(true);
		this.setDefaultCloseOperation(3);
	}
	
//登录界面登录按钮的监听器
	public void actionPerformed(ActionEvent e) 
	{
//		登录验证
		LoginCheck login = new LoginCheck();
		
		user.setId(taccount.getText());
		user.setPassword(String.valueOf(tpassword.getPassword()));
		uib.setUser(user);
		uib.setMesType("2");
		uib = login.check(uib);
		if (uib!=null)
		{
			uib.setSender(user.getId());
			Main_Frame main_frame = new Main_Frame(uib.getUser());
			
			Manage_MainFrame.addMainFrame(uib.getSender(), main_frame);
			dispose();
		}
		else uib = new UserInfoBean();
	}
	
	
	
	
//鼠标事件监听器
	public void mouseClicked(MouseEvent e)
	{
		if (e.getSource()==RegisteredAccount) {new Register_Frame();}
		if (e.getSource()==ForgetPassword) {new ResetPassword_Frame();}
	}
	public void mouseEntered(MouseEvent e) 
	{
		if (e.getSource()==RegisteredAccount) {RegisteredAccount.setFont(new Font("微软雅黑", Font.ITALIC, 15));}
		if (e.getSource()==ForgetPassword) {ForgetPassword.setFont(new Font("微软雅黑", Font.ITALIC, 15));}
	}
	public void mouseExited(MouseEvent e) 
	{
		if (e.getSource()==RegisteredAccount) {RegisteredAccount.setFont(new Font("微软雅黑", Font.PLAIN, 15));}
		if (e.getSource()==ForgetPassword) {ForgetPassword.setFont(new Font("微软雅黑", Font.PLAIN, 15));}
	}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
}
