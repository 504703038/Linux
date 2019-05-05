package Data;

/*
 * 功能：数据库操作  修改
 */

import Common.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class JDBC_Updata 
{	
	public static void Login(String Id)
	{
		Connection conn = JDBC_Connection.getConn();
		PreparedStatement pstmt;
		try 
		{
	        pstmt = (PreparedStatement) conn.prepareStatement("update myqq_user set OnLine=1 where Id="+Id);
	        int i = pstmt.executeUpdate();
	        pstmt.close();
	        conn.close();
	    }
		catch (SQLException e) 
		{
	        e.printStackTrace();
	    }
	}
	
	public void Logout(String Id)
	{
		Connection conn = JDBC_Connection.getConn();
		PreparedStatement pstmt;
		try 
		{
	        pstmt = (PreparedStatement) conn.prepareStatement("update myqq_user set OnLine=0 where Id="+Id);
	        int i = pstmt.executeUpdate();
	        pstmt.close();
	        conn.close();
	    }
		catch (SQLException e) 
		{
	        e.printStackTrace();
	    }
	}
	
	public void ReadMessage(int number)
	{
		Connection conn = JDBC_Connection.getConn();
		PreparedStatement pstmt;
		try 
		{
	        pstmt = (PreparedStatement) conn.prepareStatement("update messages set Unread=0 where Number="+number);
	        int i = pstmt.executeUpdate();
	        pstmt.close();
	        conn.close();
	    }
		catch (SQLException e) 
		{
	        e.printStackTrace();
	    }
	}
	
	public void ResetPassword(User user)
	{
		Connection conn = JDBC_Connection.getConn();
		PreparedStatement pstmt;
		try 
		{
	        pstmt = (PreparedStatement) conn.prepareStatement("update myqq_user set Password="+user.getPassword()+" where Id="+user.getId());
	        int i = pstmt.executeUpdate();
	        pstmt.close();
	        conn.close();
	    }
		catch (SQLException e) 
		{
	        e.printStackTrace();
	    }
	}
	
}
