package Bussiness;

/*
 * 功能：向服务器发送信息
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
			
			//发送信息
			oos.writeObject(uib);
			
//			if (uib.getMesType().equals("4_1"))
//			{
//				//创建线程发送文件
//				SendFileToServer sfts = new SendFileToServer(socket,uib);
//			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
}
