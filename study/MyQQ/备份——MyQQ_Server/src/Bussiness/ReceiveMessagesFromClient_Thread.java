package Bussiness;

/*
 * ���ܣ��ӿͻ��˽�����Ϣ(��������ĳ���ͻ��˵�ͨ���߳�)
 */

import java.net.*;
import java.io.*;

import Common.*;

public class ReceiveMessagesFromClient_Thread extends Thread
{
//	private ServerSocket ss;
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
				//������������Ӧ
				
//				if (uib.getMesType().equals("4_1"))
//				{
//					//�ӿͻ��˽����ļ������ݰ���ֱ�ӷ��͸��ͻ���
//					ReceiveFileFromClient rffc = new ReceiveFileFromClient(uib);
//				}
//				else 
					
				serverlauch.Lauch(uib);
				
//				s.close();
			} 
		}
		catch (Exception e) 
		{
			
			e.printStackTrace();
		}
		
	}
	
}
