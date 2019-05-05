package Data;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import Common.User;
import Common.UserInfoBean;

public class JDBC_Select_User 
{
	public User SelectUser(String Id)
	{
		User user = null;
		Connection conn = JDBC_Connection.getConn();
		PreparedStatement pstmt;
		try 
		{
	        pstmt = (PreparedStatement) conn.prepareStatement("select * from myqq_user where Id="+Id);
	        ResultSet rs = pstmt.executeQuery();
	        if (rs.next())
	        {
	        	user = new User();
	        	user.setId(rs.getString(1));
	        	user.setPassword(rs.getString(2));
	        	user.setNickname(rs.getString(3));
	        	user.setPhonenumber(rs.getString(4));
	        	user.setOnLine(rs.getString(5));
	        }
	        pstmt.close();
	        conn.close();
	    }
		catch (SQLException e) 
		{
	        e.printStackTrace();
	    }
		return user;
	}
}
