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
//			System.out.println(uib.getMesType()+"\n"+uib.getSender()+"  To  "+uib.getReceiver()+"\n"+uib.getMessage()+"\n");
			oos.writeObject(uib);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
}
