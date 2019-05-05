package Data;

/*
 * 功能：数据库操作  添加
 */

import java.sql.SQLException;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import Common.User;
import Common.UserInfoBean;

public class JDBC_Add_Account 
{
	//向数据库中添加账号
		public int  Add_Account(UserInfoBean uib) 
		{
			User user=uib.getUser();
			int i=0;
		    Connection conn = JDBC_Connection.getConn();
		    String sql = "insert into myqq_user (Id,Password,Nickname,Phonenumber,OnLine) values(?,?,?,?,?)";
		    PreparedStatement pstmt;
		    try 
		    {
		        pstmt = (PreparedStatement) conn.prepareStatement(sql);
		        pstmt.setString(1, user.getId());
		        pstmt.setString(2, user.getPassword());
		        pstmt.setString(3, user.getNickname());
		        pstmt.setString(4, user.getPhonenumber());
		        pstmt.setString(5, "0");
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
