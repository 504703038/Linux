package Common;

import java.io.File;

/*
 * ���ܣ�������Ϣ�����ݰ�
 * 
 * ��Ϣ������
 * MesType	1		����˺� ��
 * MesType	2		��¼��֤��
 * MesType	2_1   	��ѯ�˺��Ƿ�ע���
 * MesType	2_2   	��������������°�
 * MesType	3		�޸������
 * MesType	4		���췢����Ϣ�İ�
 * MesType  4_1 	�����ļ���
 * MesType  4_2		���ڶ���
 * MesType  5		�ͻ������߰�
 * 
 * 
 */

public class UserInfoBean implements java.io.Serializable
{
	private String MesType;
	private String Sender;
	private String SendTime;
	private String Receiver;
	private String Message;
	private File file;
	private int total_friend;
	private Friend[] friends = new Friend[200];
	
	private User user = null;
	
	public String getMesType() 
	{
		return MesType;
	}

	public void setMesType(String mesType) 
	{
		MesType = mesType;
	}

	public User getUser() 
	{
		return user;
	}

	public void setUser(User user) 
	{
		this.user = user;
	}

	public String getSender()
	{
		return Sender;
	}

	public void setSender(String sender) 
	{
		Sender = sender;
	}

	public String getSendTime() 
	{
		return SendTime;
	}

	public void setSendTime(String sendTime) 
	{
		SendTime = sendTime;
	}

	public String getReceiver()
	{
		return Receiver;
	}

	public void setReceiver(String receiver) 
	{
		Receiver = receiver;
	}

	public String getMessage() 
	{
		return Message;
	}

	public void setMessage(String message) 
	{
		Message = message;
	}

	public File getFile() 
	{
		return file;
	}

	public void setFile(File file) 
	{
		this.file = file;
	}

	public Friend[] getFriends() {
		return friends;
	}

	public void setFriends(Friend[] friends) 
	{
		this.friends = friends;
	}

	public int getTotal_friend() 
	{
		return total_friend;
	}

	public void setTotal_friend(int total_friend) 
	{
		this.total_friend = total_friend;
	}
	
	
	
}
