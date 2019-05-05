package Bussiness;

/*
 * 功能：验证登录密码
 */

import java.awt.*;
import javax.swing.*;

import java.net.Socket;
import java.io.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import Common.*;

public class Login 
{
	private User user = null;
	private UserInfoBean uib = null;
	
//	private ClientToServer cts = new ClientToServer();
	
	private Socket socket = null;
	
	private Client_AccountManage cam = new Client_AccountManage();
	
	public UserInfoBean check(UserInfoBean uib) 
	{
		JLabel title = null;
		user=uib.getUser();
		
		if (user.getId().equals(""))
		{
			title = new JLabel("账号为空请重新输入");
			title.setFont(new Font("微软雅黑", Font.BOLD, 15));
			JOptionPane.showMessageDialog(null,title,"提示", 2, new ImageIcon("E:\\学习\\大一下\\JAVA\\课设\\相关文件\\Images\\False.png"));
			return null;
		}
		else if (user.getPassword().equals(""))
		{
			title = new JLabel("密码为空请重新输入");
			title.setFont(new Font("微软雅黑", Font.BOLD, 15));
			JOptionPane.showMessageDialog(null,title,"提示", 2, new ImageIcon("E:\\学习\\大一下\\JAVA\\课设\\相关文件\\Images\\False.png"));
			return null;
		}
		else this.uib=cam.SendAccountIfoTOServer(uib);
		
		if (this.uib==null)
		{
			title = new JLabel("账号或密码输入错误");
			title.setFont(new Font("微软雅黑", Font.BOLD, 15));
			JOptionPane.showMessageDialog(null,title,"提示", 2, new ImageIcon("E:\\学习\\大一下\\JAVA\\课设\\相关文件\\Images\\False.png"));
			return null;
		}
		else if (this.uib.getMesType().equals("2_2"))
		{
			title = new JLabel("该账号已登录，请勿重复登录");
			title.setFont(new Font("微软雅黑", Font.BOLD, 15));
			JOptionPane.showMessageDialog(null,title,"提示", 2,new ImageIcon("E:\\学习\\大一下\\JAVA\\课设\\相关文件\\Images\\False.png"));
			return null;
		}
		
		return this.uib;
	}
}
