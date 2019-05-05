package Common;

import java.io.File;
import java.util.Date;

/*
 * 功能：传递信息的数据包
 * 
 * 信息包类型
 * MesType	1		添加账号 包
 * MesType	1_2		重置密码验证手机号码
 * MesType	1_3		Updata密码
 * MesType	2		登录验证包
 * MesType	2_1   	查询账号是否被注册包
 * MesType	2_2   	同一账号重复登录包
 * MesType	2_3		返回好友在线信息
 * MesType	3		修改密码包
 * MesType	4		聊天发送信息的包
 * MesType  4_1 	发送文件包
 * MesType  4_2		窗口抖动
 * MesType  4_3		主动接收好友消息
 * MesType  4_4		有未读的好友消息
 * MesType	4_5		群聊消息(发送到服务器并储存在数据库)
 * MesType	4_6		主动接收群聊消息
 * MesType	4_7		聊天记录
 * MesType	4_8		画图接收请求
 * MesType	4_9		画图信息传输
 * MesType  5		客户端下线包
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