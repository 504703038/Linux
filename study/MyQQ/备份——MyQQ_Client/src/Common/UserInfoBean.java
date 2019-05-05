package Common;

import java.io.File;
import java.util.Date;

/*
 * ���ܣ�������Ϣ�����ݰ�
 * 
 * ��Ϣ������
 * MesType	1		����˺� ��
 * MesType	1_2		����������֤�ֻ�����
 * MesType	1_3		Updata����
 * MesType	2		��¼��֤��
 * MesType	2_1   	��ѯ�˺��Ƿ�ע���
 * MesType	2_2   	ͬһ�˺��ظ���¼��
 * MesType	2_3		���غ���������Ϣ
 * MesType	3		�޸������
 * MesType	4		���췢����Ϣ�İ�
 * MesType  4_1 	�����ļ���
 * MesType  4_2		���ڶ���
 * MesType  4_3		�������պ�����Ϣ
 * MesType  4_4		��δ���ĺ�����Ϣ
 * MesType	4_5		Ⱥ����Ϣ(���͵������������������ݿ�)
 * MesType	4_6		��������Ⱥ����Ϣ
 * MesType	4_7		�����¼
 * MesType	4_8		��ͼ��������
 * MesType	4_9		��ͼ��Ϣ����
 * MesType  5		�ͻ������߰�
 * 
 * 
 */

public class UserInfoBean implements java.io.Serializable
{
	private String MesType;
	private String Sender;
	private String Sender_nickname;
	private Date SendTime;
	private String Receiver;
	private String Message;
	private File file;
	private String Messages_Group;
	private String Chat_Frame_Key;
	private PaintMessages paintmessages;
	
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

	public Date getSendTime() 
	{
		return SendTime;
	}

	public void setSendTime(Date sendTime) 
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

	public String getMessages_Group() 
	{
		return Messages_Group;
	}

	public void setMessages_Group(String messages_Group) 
	{
		Messages_Group = messages_Group;
	}

	public String getChat_Frame_Key() 
	{
		return Chat_Frame_Key;
	}

	public void setChat_Frame_Key(String chat_Frame_Key) 
	{
		Chat_Frame_Key = chat_Frame_Key;
	}

	public PaintMessages getPaintmessages() 
	{
		return paintmessages;
	}

	public void setPaintmessages(PaintMessages paintmessages) 
	{
		this.paintmessages = paintmessages;
	}

	public String getSender_nickname() 
	{
		return Sender_nickname;
	}

	public void setSender_nickname(String sender_nickname) 
	{
		Sender_nickname = sender_nickname;
	}
	
	
	
}