package Bussiness;

/*
 * ���ܣ��������������Ϣ
 */

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import Common.UserInfoBean;

public class SendMessagesToServer 
{
	public Socket socket = null;
	
	public void SendMessageTOServer(UserInfoBean uib) 
	{
		socket = Manage_ReceiveMessagesFromServerThread.getReceiveMessagesFromServer(uib.getSender()).s;
		try
		{
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			
			//������Ϣ
			oos.writeObject(uib);
			
//			if (uib.getMesType().equals("4_1"))
//			{
//				//�����̷߳����ļ�
//				SendFileToServer sfts = new SendFileToServer(socket,uib);
//			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
}
