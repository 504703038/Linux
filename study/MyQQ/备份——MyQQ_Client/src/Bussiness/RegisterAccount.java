package Bussiness;

/*
 * 功能：注册账号并加入数据库
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
	
	private ImageIcon ico = new ImageIcon("E:\\学习\\大一下\\JAVA\\课设\\相关文件\\Images\\False.png");
	
	public  boolean RegisterAccount(User user)
	{
		boolean ok = false;
		JLabel title = null;
		if (user.getNickname().equals(""))
			title = new JLabel("昵称不能为空，请重新填写");
		else if (user.getPassword().equals(""))
				title = new JLabel("密码不能为空，请重新填写");
		else if (!user.getPassword().equals(user.getConfirmPassword()))
				title = new JLabel("两次密码填写不一致，请重新填写");
		else if (user.getPhonenumber().equals(""))
				title = new JLabel("手机号码不能为空，请重新填写");
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
			title = new JLabel("您的账号为"+user.getId());
			ok=true;
			ico = new ImageIcon("E:\\学习\\大一下\\JAVA\\课设\\相关文件\\Images\\True.png");
		}
		title.setFont(new Font("微软雅黑", Font.BOLD, 15));
		JOptionPane.showMessageDialog(null,title,"提示", 2, ico);
		return ok;
	}
	
}
