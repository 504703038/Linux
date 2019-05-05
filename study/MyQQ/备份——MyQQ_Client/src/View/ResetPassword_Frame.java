package View;

/*
 * 找回密码界面。
 */

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Bussiness.ResetPassword;
import Common.User;
import Common.UserInfoBean;

public class ResetPassword_Frame extends JFrame 
{
	JPanel card1 = new JPanel();
	JPanel card2 = new JPanel();
	JPanel card = new JPanel();
	CardLayout layout = new CardLayout();
	
	JPanel account_panel = new JPanel();
	JPanel phonenumber_panel = new JPanel();
	JPanel next_panel = new JPanel();
	JPanel newpassword_panel = new JPanel();
	JPanel confirm_newpassword_panel = new JPanel();
	JPanel achieve_panel = new JPanel();
	
	JLabel account_label = new JLabel("    账号：      ");
	JLabel phonenumber_label = new JLabel("验证手机号码:");
	JLabel newpassword_label = new JLabel("   新密码：   ");
	JLabel confirm_newpassword_label = new JLabel("确认新密码：");
	
	JTextField account_text = new JTextField(15);
	JTextField phonenumber_text = new JTextField(16);
	JPasswordField newpassword_text = new JPasswordField(15);
	JPasswordField confirm_newpassword_text = new JPasswordField(15);
	
	JButton next_button = new JButton("下一步");
	JButton achieve_button = new JButton("确  认");
	
	ResetPassword rp = new ResetPassword();
	
	public ResetPassword_Frame()
	{
		super("重置密码");
		this.setResizable(true);
		this.setBounds(670,250,600,600);
		this.setIconImage(new ImageIcon("E:\\编程\\JAVA\\程序\\MyQQ_Client\\src\\com.client.Images\\qq.png").getImage());
		
		card.setLayout(layout);
		seting();
		card1_seting();
		card2_seting();
		
		this.add(card);
		this.setVisible(true);
		this.setDefaultCloseOperation(2);
	}
	public void card1_seting()
	{
		card1.setLayout(new GridLayout(7,1));
		
		account_panel.add(account_label);
		account_panel.add(account_text);
		phonenumber_panel.add(phonenumber_label);
		phonenumber_panel.add(phonenumber_text);
		next_panel.add(next_button);
		card1.add(new JPanel());
		card1.add(new JPanel());
		card1.add(account_panel);
		card1.add(phonenumber_panel);
		card1.add(new JPanel());
		card1.add(next_panel);
		
		card.add(card1,"card1");
	}
	public void card2_seting()
	{
		
		card2.setLayout(new GridLayout(7,1));
		
		newpassword_panel.add(newpassword_label);
		newpassword_panel.add(newpassword_text);
		confirm_newpassword_panel.add(confirm_newpassword_label);
		confirm_newpassword_panel.add(confirm_newpassword_text);
		achieve_panel.add(achieve_button);
		card2.add(new JPanel());
		card2.add(new JPanel());
		card2.add(newpassword_panel);
		card2.add(confirm_newpassword_panel);
		card2.add(new JPanel());
		card2.add(achieve_panel);
		
		card.add(card2, "card2");
	}
	public void seting()
	{	
//card1组件设置
		account_label.setFont(new Font("微软雅黑",Font.PLAIN,20));
		account_text.setFont(new Font("微软雅黑",Font.BOLD,20));
		phonenumber_text.setFont(new Font("微软雅黑",Font.PLAIN,20));
		phonenumber_label.setFont(new Font("微软雅黑",Font.PLAIN,20));
		next_button.setFont(new Font("微软雅黑",Font.PLAIN,20));
		next_button.setPreferredSize(new Dimension(100,40));
		next_button.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
//					System.out.println("向服务器验证手机号码\n");
					User user = new User();
					user.setId(account_text.getText());
					user.setPhonenumber(phonenumber_text.getText());
					UserInfoBean uib = new UserInfoBean();
					uib.setSender(user.getId());
					uib.setUser(user);
					uib.setMesType("1_2");
					uib=rp.ResetPassword(uib);
					if (uib!=null) 
					{
						layout.show(card, "card2");
					}
					else
					{
						JLabel title = new JLabel("手机号码与注册时所用手机号不匹配，请重新输入。");
						title.setFont(new Font("微软雅黑",Font.PLAIN,16));
						JOptionPane.showMessageDialog(null,title,"提示", 2, new ImageIcon("E:\\学习\\大一下\\JAVA\\课设\\相关文件\\Images\\False.png"));
						return;
					}
				}
			});
//card2组件设置
		newpassword_label.setFont(new Font("微软雅黑",Font.PLAIN,20));
		newpassword_text.setFont(new Font("微软雅黑",Font.PLAIN,20));
		confirm_newpassword_label.setFont(new Font("微软雅黑",Font.PLAIN,20));
		confirm_newpassword_text.setFont(new Font("微软雅黑",Font.PLAIN,20));
		achieve_button.setFont(new Font("微软雅黑",Font.PLAIN,20));
		newpassword_text.setEchoChar('●');
		confirm_newpassword_text.setEchoChar('●');
		achieve_button.setPreferredSize(new Dimension(100,40));
		achieve_button.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
//					System.out.println("向服务器发送新密码\n");
				
					String newpassword = String.valueOf(newpassword_text.getPassword());
					String confirm_newpassword = String.valueOf(confirm_newpassword_text.getPassword());
					
					if (!newpassword.equals(confirm_newpassword))
					{
						JLabel title = new JLabel("两次密码输入不一致，请重新输入");
						title.setFont(new Font("微软雅黑",Font.PLAIN,16));
						JOptionPane.showMessageDialog(null,title,"提示", 2, new ImageIcon("E:\\学习\\大一下\\JAVA\\课设\\相关文件\\Images\\False.png"));
						return;
					}
					User user = new User();
					user.setId(account_text.getText());
					user.setPassword(String.valueOf(confirm_newpassword_text.getPassword()));
					UserInfoBean uib = new UserInfoBean();
					uib.setSender(user.getId());
					uib.setUser(user);
					uib.setMesType("1_3");
					
					uib=rp.ResetPassword(uib);
					
					JLabel title = new JLabel("重置密码成功。");
					title.setFont(new Font("微软雅黑",Font.PLAIN,16));
					JOptionPane.showMessageDialog(null,title,"提示", 2, new ImageIcon("E:\\学习\\大一下\\JAVA\\课设\\相关文件\\Images\\True.png"));
					
					dispose();
				}
			});
	}
	
//	public static void main(String[] args)
//	{
//		new ResetPassword_Frame();
//	}
	
}
