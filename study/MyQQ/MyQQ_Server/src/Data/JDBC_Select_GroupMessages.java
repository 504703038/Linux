package Data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import Bussiness.SendMessagesToClient;
import Common.UserInfoBean;

public class JDBC_Select_GroupMessages 
{
	SendMessagesToClient smtc = new SendMessagesToClient();
	JDBC_Updata updata= new JDBC_Updata();
	public  void Select_GroupMessages(UserInfoBean uib)
	{
		String GroupId=uib.getReceiver();
		uib.setMessages_Group(GroupId);
		Connection conn = JDBC_Connection.getConn();
		PreparedStatement pstmt;
		try 
		{
	        pstmt = (PreparedStatement) conn.prepareStatement("select * from group_messages where GroupId='"+GroupId+"'");
	        ResultSet rs = pstmt.executeQuery();
	        uib.setReceiver(uib.getSender());
	        while (rs.next())
	        {
	        	uib.setSender(rs.getString(1));
        		uib.setSendTime(new Date(rs.getTimestamp(3).getTime()));
        		uib.setMessage(rs.getString(4));
        		uib.setSender_nickname(rs.getString(6));
        		smtc.SendMessages(uib);
	        }
	        pstmt.close();
	        conn.close();
	    }
		catch (SQLException e) 
		{
	        e.printStackTrace();
	    }
	}
}
