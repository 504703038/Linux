package Bussiness;

/*
 * ���ܣ���ͻ��˷�����Ϣ
 */

import java.net.*;
import java.io.*;
import Common.*;

public class SendMessagesToClient 
{
	public void SendMessages(UserInfoBean uib)
	{
		
		Socket s =Manag_ReceiveMessagesFromClient_Thread.getClientThread(uib.getReceiver()).s;
		
		try
		{
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			//������Ϣ
			oos.writeObject(uib);	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
