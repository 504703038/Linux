package Bussiness;

/*
 * ���ܣ�ע���˺Ų��������ݿ�
 */

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import javax.swing.*;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import Common.*;
import Data.*;

public class RegisterAccount 
{
	private  UserInfoBean uib = new UserInfoBean();
	private SendLoginInfoToServer cam = new SendLoginInfoToServer();
	
//	private  ClientToServer cts = new ClientToServer();
//	SendMessagesToServer smts = new SendMessagesToServer(); 
	
	private ImageIcon ico = new ImageIcon("E:\\ѧϰ\\��һ��\\JAVA\\����\\����ļ�\\Images\\False.png");
	
	public  boolean RegisterAccount(User user)
	{
		boolean ok = false;
		JLabel title = null;
		if (user.getNickname().equals(""))
			title = new JLabel("�ǳƲ���Ϊ�գ���������д");
		else if (user.getPassword().equals(""))
				title = new JLabel("���벻��Ϊ�գ���������д");
		else if (!user.getPassword().equals(user.getConfirmPassword()))
				title = new JLabel("����������д��һ�£���������д");
		else if (user.getPhonenumber().equals(""))
				title = new JLabel("�ֻ����벻��Ϊ�գ���������д");
		else
		{
			Random random = new Random();
			int account=random.nextInt(90000)+10000;
//			int account=random.nextInt(90000)%10;
			user.setId(Integer.toString(account));
			uib.setUser(user);
			uib.setMesType("2_1");
			while (cam.SendAccountIfoTOServer(uib)!=null)
			{
				account=random.nextInt(90000)+10000;
//				account=random.nextInt(90000)%10;
				user.setId(Integer.toString(account));
				uib.setUser(user);
			}
			uib.setMesType("1");
			uib.setUser(user);
			cam.SendAccountIfoTOServer(uib);
			title = new JLabel("�����˺�Ϊ"+user.getId());
			ok=true;
			ico = new ImageIcon("E:\\ѧϰ\\��һ��\\JAVA\\����\\����ļ�\\Images\\True.png");
		}
		title.setFont(new Font("΢���ź�", Font.BOLD, 15));
		JOptionPane.showMessageDialog(null,title,"��ʾ", 2, ico);
		return ok;
	}
	
}
