package Bussiness;

/*
 * ���ܣ��ӿͻ��˽�����Ϣ(��������ĳ���ͻ��˵�ͨ���߳�)
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
				//������Ϣ
				UserInfoBean uib = (UserInfoBean)ois.readObject();
				//�б���Ϣ���Ͳ�������Ӧ	
				serverlauch.Lauch(uib);
			} 
		}
		catch (Exception e) 
		{
			
			e.printStackTrace();
		}
		
	}
	
}
