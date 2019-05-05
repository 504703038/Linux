package Common;

/*
 * ���ܣ�����User�����Ϣ
 */

public class User implements java.io.Serializable
{
	private String Id;							//�û��˺�		����10
	private String Password;  					//�û�����		����16
	private String ConfirmPassword;
	private String Nickname;					//�û��ǳ�		����20
	private String Sex;							//�û��Ա�		����4
	private String Birth;						//�û�����		
	private String Constellation;				//�û�����		
	private String Blood;						//�û�Ѫ��		
	private String Educational_background;		//�û�ѧ��		
	private String Phonenumber; 				//�û��绰		����11
	private String Email;						//�û�����		
	private String Home;						//�û����ڵ�	
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
