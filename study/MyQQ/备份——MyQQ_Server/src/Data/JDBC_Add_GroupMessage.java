package Data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import Common.UserInfoBean;

public class JDBC_Add_GroupMessage 
{
	public int Add_GroupMessage(UserInfoBean uib)
	{
		int i=0;
	    Connection conn = JDBC_Connection.getConn();
	    String sql = "insert into group_messages (Sender,GroupId,SendTime,Message,Number,Sender_nickname) values(?,?,?,?,?,?)";
	    PreparedStatement pstmt;
	    int number=0;
	    try 
	    {
	    	pstmt = (PreparedStatement) conn.prepareStatement("select count(*) from group_messages");
	    	ResultSet rs = pstmt.executeQuery();
	    	if (rs.next())
	    	{
	    		number=rs.getInt(1);
	    	}
	    	rs.close();
	    	number++;
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        pstmt.setString(1, uib.getSender());
	        pstmt.setString(2, uib.getReceiver());
	        pstmt.setTimestamp(3, new Timestamp(uib.getSendTime().getTime()));
	        pstmt.setString(4, uib.getMessage());
	        pstmt.setInt(5, number);
	        pstmt.setString(6, uib.getSender_nickname());
	        i = pstmt.executeUpdate();
	        pstmt.close();
	        conn.close();
	    } 
	    catch (SQLException e) 
	    {
	        e.printStackTrace();
	    }
	    return i;
	}
}
