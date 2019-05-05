package View;

/*
 * µÇÂ¼½çÃæ¡£
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
	private JButton login = new JButton("µÇ    Â¼");
	private JLabel laccount = new JLabel("ÕËºÅ£º");
	private JLabel lpassword = new JLabel("ÃÜÂë£º");
	private JLabel RegisteredAccount = new JLabel("×¢²áÕËºÅ");
	private JLabel ForgetPassword = new JLabel("Íü¼ÇÃÜÂë");
	private JTextField taccount = new JTextField(18);
	private JPasswordField tpassword = new JPasswordField(18);
	private JCheckBox AutomaticLogon = new JCheckBox("×Ô¶¯µÇÂ¼");
	private JCheckBox ResetPassword = new JCheckBox("¼Ç×¡ÃÜÂë");
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
		this.setIconImage(new ImageIcon("E:\\±à³Ì\\JAVA\\³ÌÐò\\MyQQ_Client\\src\\com.client.Images\\qq.png").getImage());
//¿ò¼Ü
		this.setBounds(x,y,w,h);
		this.setLayout(null);
		this.setResizable(false);
	//ÉÏ°ë²¿·Ö
		up.setBounds(0,0,w,120);
		JLabel ima = new JLabel(new ImageIcon("E:\\±à³Ì\\JAVA\\³ÌÐò\\MyQQ_Client\\src\\com.client.Images\\qqµÇÂ¼½çÃæ±³¾°.png"));
		up.add(ima);
//		up.setBackground(new Color(116,174,255));
		
	//ÏÂ°ë²¿·Ö
		down.setBounds(0,120,w,250);
				//down.setBackground(Color.RED);
		down.setLayout(null);
		//ÕËºÅºÍÃÜÂë¿ò
		down_1.setBounds(100,40,340,130);
				//down_1.setBackground(Color.BLACK);
		down_1.setLayout(new GridLayout(3,1));
		laccount.setFont(new Font("ËÎÌå", Font.BOLD, 20));
		lpassword.setFont(new Font("ËÎÌå", Font.BOLD, 20));
		taccount.setFont(new Font("ËÎÌå", Font.BOLD, 20));
		tpassword.setFont(new Font("ËÎÌå", Font.BOLD, 20));
		RegisteredAccount.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		RegisteredAccount.setForeground(Color.blue);
		ForgetPassword.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		ForgetPassword.setForeground(Color.blue);
		AutomaticLogon.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		ResetPassword.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		tpassword.setEchoChar('¡ñ');
//			Ìí¼Ó¼àÌýÆ÷
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
			//Ìí¼Ó¸´Ñ¡¿ò£¨¼Ç×¡ÃÜÂë,×Ô¶¯µÇÂ¼£©
		down_2.add(AutomaticLogon);
		down_2.add(ResetPassword);
		down_1.add(down_2);
		down.add(down_1);
		
		
		//µÇÂ¼°´Å¥
		login.setBounds(130,180,280,40);
		login.setFont(new Font("ºÚÌå", Font.PLAIN, 20));
		login.setForeground(Color.white);
		login.setBackground(new Color(4,186,251));
			//Ìí¼Ó¼àÌýÆ÷
		login.addActionListener(this);
		
		down.add(login);
		
		//¼ÓÈë¿ò¼Ü
		this.add(up);
		this.add(down);
		this.setVisible(true);
		this.setDefaultCloseOperation(3);
	}
	
//µÇÂ¼½çÃæµÇÂ¼°´Å¥µÄ¼àÌýÆ÷
	public void actionPerformed(ActionEvent e) 
	{
//		µÇÂ¼ÑéÖ¤
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
	
	
	
	
//Êó±êÊÂ¼þ¼àÌýÆ÷
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
			RegisteredAccount.setFont(new Font("Î¢ÈíÑÅºÚ", Font.ITALIC, 15));
		}
		
		if (e.getSource()==ForgetPassword)
		{
			ForgetPassword.setFont(new Font("Î¢ÈíÑÅºÚ", Font.ITALIC, 15));
		}
		
	}
	public void mouseExited(MouseEvent e) 
	{
		if (e.getSource()==RegisteredAccount)
		{
			RegisteredAccount.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		}
		
		if (e.getSource()==ForgetPassword)
		{
			ForgetPassword.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		}
	}
	public void mousePressed(MouseEvent e) 
	{
		
	}
	public void mouseReleased(MouseEvent e) 
	{
		
	}
}
