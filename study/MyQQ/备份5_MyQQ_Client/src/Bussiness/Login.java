package Bussiness;

/*
 * ���ܣ���֤��¼����
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
			title = new JLabel("�˺�Ϊ������������");
			title.setFont(new Font("΢���ź�", Font.BOLD, 15));
			JOptionPane.showMessageDialog(null,title,"��ʾ", 2, new ImageIcon("E:\\ѧϰ\\��һ��\\JAVA\\����\\����ļ�\\Images\\False.png"));
			return null;
		}
		else if (user.getPassword().equals(""))
		{
			title = new JLabel("����Ϊ������������");
			title.setFont(new Font("΢���ź�", Font.BOLD, 15));
			JOptionPane.showMessageDialog(null,title,"��ʾ", 2, new ImageIcon("E:\\ѧϰ\\��һ��\\JAVA\\����\\����ļ�\\Images\\False.png"));
			return null;
		}
		else this.uib=cam.SendAccountIfoTOServer(uib);
		
		if (this.uib==null)
		{
			title = new JLabel("�˺Ż������������");
			title.setFont(new Font("΢���ź�", Font.BOLD, 15));
			JOptionPane.showMessageDialog(null,title,"��ʾ", 2, new ImageIcon("E:\\ѧϰ\\��һ��\\JAVA\\����\\����ļ�\\Images\\False.png"));
			return null;
		}
		else if (this.uib.getMesType().equals("2_2"))
		{
			title = new JLabel("���˺��ѵ�¼�������ظ���¼");
			title.setFont(new Font("΢���ź�", Font.BOLD, 15));
			JOptionPane.showMessageDialog(null,title,"��ʾ", 2,new ImageIcon("E:\\ѧϰ\\��һ��\\JAVA\\����\\����ļ�\\Images\\False.png"));
			return null;
		}
		
		return this.uib;
	}
}
