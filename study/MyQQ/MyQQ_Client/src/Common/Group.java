package Common;

public class Group implements java.io.Serializable
{
	String Group_Id;
	String Gropu_Name;
	int total_Groupmate;
	Group_Mate[] groupmates = new Group_Mate[110];
	
	public String getGroup_Id() 
	{
		return Group_Id;
	}
	public void setGroup_Id(String group_Id) 
	{
		Group_Id = group_Id;
	}
	public String getGropu_Name() 
	{
		return Gropu_Name;
	}
	public void setGropu_Name(String gropu_Name) 
	{
		Gropu_Name = gropu_Name;
	}
	public int getTotal_Groupmate() 
	{
		return total_Groupmate;
	}
	public void setTotal_Groupmate(int total_Groupmate) 
	{
		this.total_Groupmate = total_Groupmate;
	}
	public Group_Mate[] getGroupmates() 
	{
		return groupmates;
	}
	public void setGroupmates(Group_Mate[] groupmates) 
	{
		this.groupmates = groupmates;
	}
	
}


