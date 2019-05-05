package Bussiness;

/*
 * 功能：向客户端发送信息
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
			//发送信息
			oos.writeObject(uib);	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
