package Common;

public class Group_Mate implements java.io.Serializable
{
	String Id;
	String Nickname;
	int total_groupmates;
	
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
	public int getTotal_groupmates() 
	{
		return total_groupmates;
	}
	public void setTotal_groupmates(int total_groupmates) 
	{
		this.total_groupmates = total_groupmates;
	}
	
	
	
}
