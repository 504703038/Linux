package Bussiness;

/*
 * 功能：从客户端接收信息(服务器与某个客户端的通信线程)
 */

import java.net.*;
import java.io.*;

import Common.*;

public class ReceiveMessagesFromClient_Thread extends Thread
{
	public Socket s;
	public volatile boolean exit = false; 
	Server_Lauch serverlauch = new Server_Lauch();
	
	public ReceiveMessagesFromClient_Thread(Socket s)
	{
		this.s=s;
	}
	
	public void run()
	{
		
		try 
		{
			while (true)
			{
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				//接收信息
				UserInfoBean uib = (UserInfoBean)ois.readObject();
				//判别消息类型并做出反应	
				serverlauch.Lauch(uib);
			} 
		}
		catch (Exception e) 
		{
			
			e.printStackTrace();
		}
		
	}
	
}
