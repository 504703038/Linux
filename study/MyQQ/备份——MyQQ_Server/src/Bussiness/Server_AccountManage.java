package Bussiness;

/*
 * ���ܣ���֤����
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
			System.out.println("���ڼ���");
			ServerSocket ss = new ServerSocket(9999);
			while (true)
			{
				Socket s =ss.accept();
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
				//������Ϣ
				UserInfoBean uib = (UserInfoBean)ois.readObject();
				//������������Ӧ
				uib = serverlauch.Lauch(uib);
				//������Ϣ
				oos.writeObject(uib);
				
				if (uib!=null && uib.getMesType().equals("2"))
				{
					//���߳��ø��߳���ÿͻ��˱���ͨѶ
					ReceiveMessagesFromClient_Thread stct = new ReceiveMessagesFromClient_Thread(s);
					//��HashMap������߳�
					Manag_ReceiveMessagesFromClient_Thread.addClientThread(uib.getUser().getId(), stct);
					//������ÿͻ���ͨѶ���߳�
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
