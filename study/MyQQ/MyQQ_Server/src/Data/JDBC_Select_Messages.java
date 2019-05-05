package Data;

import Common.User;
import Common.UserInfoBean;
import Bussiness.SendMessagesToClient;

import java.util.Date;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class JDBC_Select_Messages 
{
	SendMessagesToClient smtc = new SendMessagesToClient();
	JDBC_Updata updata= new JDBC_Updata();
	private int total;
	public  int Select_Messages(String sender,String receiver,String Type)
	{
		UserInfoBean uib =new UserInfoBean();
		Connection conn = JDBC_Connection.getConn();
		PreparedStatement pstmt;
		try 
		{
			total=0;
	        pstmt = (PreparedStatement) conn.prepareStatement("select * from messages where Sender="+sender+" and Receiver="+receiver+" and Unread=1");
	        ResultSet rs = pstmt.executeQuery();
	        while (rs.next())
	        {	
	        	total++;
	        	if (Type.equals("4_3"))
	        	{
	        		uib.setSender(rs.getString(1));
	        		uib.setReceiver(rs.getString(2));
	        		uib.setSendTime(new Date(rs.getTimestamp(3).getTime()));
	        		uib.setMessage(rs.getString(4));
	        		uib.setSender_nickname(rs.getString(7));
	        		uib.setMesType("4");
	        		smtc.SendMessages(uib);
	        		updata.ReadMessage(rs.getInt(5));
	        	}	
	        }
	        pstmt.close();
	        conn.close();
	    }
		catch (SQLException e) 
		{
	        e.printStackTrace();
	    }
		return total;
	}
}