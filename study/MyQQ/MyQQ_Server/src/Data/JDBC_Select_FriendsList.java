package Data;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import Bussiness.SendMessagesToClient;
import Common.Friend;
import Common.User;
import Common.UserInfoBean;

public class JDBC_Select_FriendsList 
{
	JDBC_Select_User select_user = new JDBC_Select_User();
	JDBC_Select_Messages select_messages = new JDBC_Select_Messages();
	public Friend[] SelectFriends(String Id)
	{
		Connection conn = JDBC_Connection.getConn();
		Friend[] friends = new Friend[200];
		try
		{
			PreparedStatement pstmt = (PreparedStatement)conn.prepareStatement("select * from myqq_friends where My_Id="+Id);
	        ResultSet rs = pstmt.executeQuery();
	        
	        int t=0;
	        User user = new User();
	        while (rs.next())
	        {
	        	t++;
	        	friends[t]=new Friend();
	        	friends[t].setId(rs.getString(2));
	        	friends[t].setGroup(rs.getString(3));
	        	user=select_user.SelectUser(friends[t].getId());
	        	friends[t].setNickname(user.getNickname());
	        	friends[t].setOnLine(user.getOnLine().equals("1"));
	        	if (friends[t].getOnLine())
	        	{
	        		SendMessagesToClient smtc = new SendMessagesToClient();
	        		UserInfoBean uib = new UserInfoBean();
	        		uib.setSender(Id);
	        		uib.setReceiver(friends[t].getId());
	        		uib.setMesType("2_3");
	        		smtc.SendMessages(uib);
	        	}
	        	friends[t].setTotal_messages(select_messages.Select_Messages(friends[t].getId(), Id, ""));
	        }
	        friends[0]= new Friend();
	        friends[0].setTotal(t);
	        pstmt.close();
	        rs.close();
	        conn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return friends;
	}
}
