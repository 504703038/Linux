package Common;

public class Friend implements java.io.Serializable
{
	private String Id;
	private String Nickname;
	private String Group;
	private int total;
	private boolean OnLine;
	private int total_messages;
	
	public Friend() {}
	
	public String getId() 
	{
		return Id;
	}
	
	public void setId(String id) 
	{
		Id = id;
	}
	
	public String getNickname() 
	{
		return Nickname;
	}
	
	public void setNickname(String nickname) 
	{
		Nickname = nickname;
	}
	
	public String getGroup() 
	{
		return Group;
	}
	
	public void setGroup(String group) 
	{
		Group = group;
	}

	public boolean getOnLine() 
	{
		return OnLine;
	}

	public void setOnLine(boolean onLine) 
	{
		OnLine = onLine;
	}

	public int getTotal() 
	{
		return total;
	}

	public void setTotal(int total) 
	{
		this.total = total;
	}

	public int getTotal_messages() 
	{
		return total_messages;
	}

	public void setTotal_messages(int total_messages) 
	{
		this.total_messages = total_messages;
	}
	
}
