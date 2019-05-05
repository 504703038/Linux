package Data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import Bussiness.SendMessagesToClient;
import Common.UserInfoBean;

public class JDBC_Select_HistoryMessages 
{
	SendMessagesToClient smtc = new SendMessagesToClient();
	JDBC_Updata updata= new JDBC_Updata();
	public void Select_HistoryMessages (String sender,String receiver,String Type)
	{
		UserInfoBean uib =new UserInfoBean();
		Connection conn = JDBC_Connection.getConn();
		PreparedStatement pstmt;
		try 
		{
	        pstmt = (PreparedStatement) conn.prepareStatement("select * from messages where Sender="+sender+" or Sender="+receiver);
	        ResultSet rs = pstmt.executeQuery();
	        uib.setReceiver(sender);
	        uib.setChat_Frame_Key(sender+"&"+receiver);
	        while (rs.next())
	        {	
	        	String s=rs.getString(1);
	        	String r=rs.getString(2);
	        	if ((s.equals(sender) && r.equals(receiver))  || (s.equals(receiver)&& r.equals(sender)))
	        	{
	        		uib.setSender(s);
	        		uib.setSendTime(new Date(rs.getTimestamp(3).getTime()));
	        		uib.setMessage(rs.getString(4));
	        		uib.setMesType("4_7");
	        		uib.setSender_nickname(rs.getString(7));
	        		smtc.SendMessages(uib);
	        	}
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
