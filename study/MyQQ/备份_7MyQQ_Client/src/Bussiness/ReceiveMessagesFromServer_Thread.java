package Bussiness;

/*
 * ���ܣ��ӷ�������ȡ��Ϣ���߳�
 */

import Common.*;
import View.*;

import java.net.*;
import java.io.*;

public class ReceiveMessagesFromServer_Thread extends Thread
{
		public Socket s;
		private UserInfoBean uib = new UserInfoBean();
		public  boolean exit = false; 
		
		public ReceiveMessagesFromServer_Thread(Socket s)
		{
			this.s=s;
		}
		
		public void run()
		{
			while (!exit)
			{
				//�ӷ�������ͣ�Ķ�ȡ��Ϣ��
				try
				{
					ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
					uib = (UserInfoBean)ois.readObject();
//					System.out.println(uib.getMesType()+"\n"+uib.getSender()+"  To  "+uib.getReceiver()+"\n"+uib.getMessage()+"\n");
					
					if (uib.getMesType().equals("4"))
					{
						Chat_Frame chat = (Chat_Frame)Manage_ChatFrame.getChatFrame(uib.getReceiver()+"&"+uib.getSender());
						chat.showMessages(uib);
					}
					
					if (uib.getMesType().equals("4_1"))
					{
						Chat_Frame chat = (Chat_Frame)Manage_ChatFrame.getChatFrame(uib.getReceiver()+"&"+uib.getSender());
						String address = chat.receiveFile();
						//�����ļ�
						ReceiveFileFromServer rffs = new ReceiveFileFromServer(address);
					}
					
					if (uib.getMesType().equals("4_2"))
					{
						for(int i=1;i<=100000000;i++) {}
						Chat_Frame chat = (Chat_Frame)Manage_ChatFrame.getChatFrame(uib.getReceiver()+"&"+uib.getSender());
						uib.setMessage(uib.getSender()+"����������һ�����ڶ�����\n");
						chat.showMessages(uib);
						chat.shake();
					}
					
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}
}
