package Common;

/*
 * 功能：传递信息的数据包
 * 
 * 信息包类型
 * MesType	1		添加账号 包
 * MesType	2		登录验证包
 * MesType	2_1   	查询账号包
 * MesType	3		修改密码包
 * MesType	4		聊天发送信息的包
 * MesType  4_1 	发送文件包
 * MesType  5		客户端下线包
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
