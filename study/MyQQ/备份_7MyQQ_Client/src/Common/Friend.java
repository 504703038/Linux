package Common;

public class Friend implements java.io.Serializable
{
	private String Id;
	private String Nickname;
	private String Group;
	private boolean OnLine;
	
	public Friend()
	{
		
	}
	
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

}
