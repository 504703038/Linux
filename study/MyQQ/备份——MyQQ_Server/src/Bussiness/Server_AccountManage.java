package Bussiness;

/*
 * 功能：验证密码
 */

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import Common.UserInfoBean;

public class Server_AccountManage extends Thread
{
	Server_Lauch serverlauch = new Server_Lauch();
	
	public void run()
	{
		try
		{
			System.out.println("正在监听");
			ServerSocket ss = new ServerSocket(9999);
			while (true)
			{
				Socket s =ss.accept();
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
				//接收信息
				UserInfoBean uib = (UserInfoBean)ois.readObject();
				//服务器做出反应
				uib = serverlauch.Lauch(uib);
				//返回信息
				oos.writeObject(uib);
				
				if (uib!=null && uib.getMesType().equals("2"))
				{
					//开线程让该线程与该客户端保持通讯
					ReceiveMessagesFromClient_Thread stct = new ReceiveMessagesFromClient_Thread(s);
					//向HashMap中添加线程
					Manag_ReceiveMessagesFromClient_Thread.addClientThread(uib.getUser().getId(), stct);
					//启动与该客户端通讯的线程
					stct.start();
				}
				else s.close();
			}	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
