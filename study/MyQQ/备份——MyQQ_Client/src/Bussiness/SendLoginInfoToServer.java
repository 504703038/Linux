package Bussiness;

/*
 * ���ܣ�����������͵�¼��֤��ע���˺š��һ��������Ϣ
 */

import Common.*;

import java.io.*;
import java.net.*;

public class SendLoginInfoToServer 
{
	private User user = null;
	private UserInfoBean uib = null;
	private Socket socket = null;
	
	public UserInfoBean  SendAccountIfoTOServer (UserInfoBean uib)
	{
		try
		{
			socket = new Socket("127.0.0.1",9999);
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			//������Ϣ
			oos.writeObject(uib);
			//������Ϣ
			this.uib = (UserInfoBean)ois.readObject();
			if (this.uib!=null && this.uib.getMesType().equals("2"))
			{
				ReceiveMessagesFromServer_Thread rmfs = new ReceiveMessagesFromServer_Thread(socket);
				rmfs.start();
				Manage_ReceiveMessagesFromServerThread.addReceiveMessagesFromServer(uib.getUser().getId(), rmfs);
			}
			else socket.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return this.uib;
	}
}
