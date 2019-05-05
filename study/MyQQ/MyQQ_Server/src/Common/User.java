package Common;

/*
 * 功能：储存User相关信息
 */

public class User implements java.io.Serializable
{
	private String Id;							//用户账号		长度10
	private String Password;  					//用户密码		长度16
	private String ConfirmPassword;
	private String Nickname;					//用户昵称		长度20
	private String Sex;							//用户性别		长度4
	private String Birth;						//用户生日		
	private String Constellation;				//用户星座		
	private String Blood;						//用户血型		
	private String Educational_background;		//用户学历		
	private String Phonenumber; 				//用户电话		长度11
	private String Email;						//用户邮箱		
	private String Home;						//用户所在地	
	private String OnLine;
	private int total_friend;
	private int total_group;
	private Friend[] friends = new Friend[200];
	private Group[] groups = new Group[101];
	
	public String getId() 
	{
		return Id;
	}
	
	public void setId(String id) 
	{
		Id = id;
	}
	
	public String getPassword() 
	{
		return Password;
	}
	
	public void setPassword(String password) 
	{
		Password = password;
	}
	
	public String getConfirmPassword() 
	{
		return ConfirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) 
	{
		ConfirmPassword = confirmPassword;
	}
	
	public String getNickname() 
	{
		return Nickname;
	}
	
	public void setNickname(String nickname) 
	{
		Nickname = nickname;
	}
	
	public String getSex() 
	{
		return Sex;
	}
	public void setSex(String sex) 
	{
		Sex = sex;
	}
	
	public String getBirth() 
	{
		return Birth;
	}
	
	public void setBirth(String birth) 
	{
		Birth = birth;
	}
	
	public String getConstellation() 
	{
		return Constellation;
	}
	
	public void setConstellation(String constellation) 
	{
		Constellation = constellation;
	}
	
	public String getBlood() 
	{
		return Blood;
	}
	
	public void setBlood(String blood) 
	{
		Blood = blood;
	}
	
	public String getEducational_background() 
	{
		return Educational_background;
	}
	
	public void setEducational_background(String educational_background) 
	{
		Educational_background = educational_background;
	}
	
	public String getPhonenumber() 
	{
		return Phonenumber;
	}
	
	public void setPhonenumber(String phonenumber) 
	{
		Phonenumber = phonenumber;
	}
	
	public String getEmail() 
	{
		return Email;
	}
	
	public void setEmail(String email) 
	{
		Email = email;
	}
	
	public String getHome() 
	{
		return Home;
	}
	
	public void setHome(String home) 
	{
		Home = home;
	}

	public int getTotal_friend() 
	{
		return total_friend;
	}

	public void setTotal_friend(int total_friend) 
	{
		this.total_friend = total_friend;
	}

	public Friend[] getFriends() 
	{
		return friends;
	}

	public void setFriends(Friend[] friends) 
	{
		this.friends = friends;
	}

	public String getOnLine() 
	{
		return OnLine;
	}

	public void setOnLine(String onLine) 
	{
		OnLine = onLine;
	}

	public Group[] getGroups() 
	{
		return groups;
	}

	public void setGroups(Group[] groups) 
	{
		this.groups = groups;
	}

	public int getTotal_group() 
	{
		return total_group;
	}

	public void setTotal_group(int total_group) 
	{
		this.total_group = total_group;
	}
	
	
	
}
