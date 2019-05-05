package Bussiness;

import Common.Friend;
import Common.Group;
import Common.User;
import Common.UserInfoBean;
import Data.JDBC_Select_FriendsList;
import Data.JDBC_Select_Group;
import Data.JDBC_Select_Groupmates;
import Data.JDBC_Select_User;
import Data.JDBC_Updata;

public class Server_Login 
{
	JDBC_Select_User select_user = new JDBC_Select_User();
	JDBC_Select_FriendsList select_friendsList = new JDBC_Select_FriendsList();
	JDBC_Select_Group select_group = new JDBC_Select_Group();
	JDBC_Select_Groupmates select_groupmates = new JDBC_Select_Groupmates();
	
	public UserInfoBean Server_Login(UserInfoBean uib)
	{
		String Id=uib.getUser().getId();
		User user = select_user.SelectUser(Id);
		if (user==null) return null;
		if (!uib.getUser().getPassword().equals(user.getPassword())) return null;
		
		//修改在线信息
		JDBC_Updata.Login(Id);
		//获取好友列表
		Friend[] friends= new Friend[200];
		friends=select_friendsList .SelectFriends(Id);
		user.setTotal_friend(friends[0].getTotal());
		user.setFriends(friends);
		
		//查询所在群组
		Group[] groups = new Group[110];
		String[] user_group = new String[110];
		user_group=select_group.Select_Group(Id);
		//查询群组成员
		user.setTotal_group(user_group[0].length());
		for(int i=1;i<=user.getTotal_group();i++)
		{
			groups[i]= new Group();
			int j=user_group[i].indexOf("%)$&)#)#*");
			groups[i].setGroup_Id(user_group[i].substring(0, j));
			groups[i].setGropu_Name(user_group[i].substring(j+9));
			groups[i].setGroupmates(select_groupmates.Select_Groupmates(groups[i].getGroup_Id()));
			groups[i].setTotal_Groupmate((groups[i].getGroupmates())[0].getTotal_groupmates());
		}
		
		
		user.setGroups(groups);
		uib.setUser(user);
		return uib;
	}
}
