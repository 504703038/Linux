
public class User implements java.io.Serializable
{
	private String Id="";							//�û��˺�		����10
	private String Password="";  					//�û�����		����16
	private String ConfirmPassword="";
	private String Nickname="";					//�û��ǳ�		����20
	private String Sex="";							//�û��Ա�		����4
	private String Birth="";						//�û�����		
	private String Constellation="";				//�û�����		
	private String Blood="";						//�û�Ѫ��		
	private String Educational_background="";		//�û�ѧ��		
	private String Phonenumber=""; 				//�û��绰		����11
	private String Email="";						//�û�����		
	private String Home="";						//�û����ڵ�		
	
	
	
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
	
}

