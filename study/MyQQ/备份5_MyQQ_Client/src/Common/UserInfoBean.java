package Common;

/*
 * ���ܣ�������Ϣ�����ݰ�
 * 
 * ��Ϣ������
 * MesType	1		����˺� ��
 * MesType	2		��¼��֤��
 * MesType	2_1   	��ѯ�˺Ű�
 * MesType	3		�޸������
 * MesType	4		���췢����Ϣ�İ�
 * MesType  4_1 	�����ļ���
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
	
	
}
