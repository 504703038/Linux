package Bussiness;

/*
 * ���ܣ���������
 */

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import Common.UserInfoBean;

public class ResetPassword 
{
	Socket socket = null;
	UserInfoBean uib = null;
	public UserInfoBean ResetPassword(UserInfoBean uib) 
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
			socket.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return this.uib;
	}
}
