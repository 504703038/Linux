package Data;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import Common.Group_Mate;

public class JDBC_Select_Groupmates 
{
	//返回群组的Id
	public Group_Mate[] Select_Groupmates(String Id)
	{
		Group_Mate[] groupmates = new Group_Mate[100];
		int total=0;
		Connection conn = JDBC_Connection.getConn();
		PreparedStatement pstmt;
		groupmates[0]= new Group_Mate();
		try 
		{
	        pstmt = (PreparedStatement) conn.prepareStatement("select Groupmate_Id,Groupmate_Nickname from Group_Info where Group_Id='"+Id+"'");
	        ResultSet rs = pstmt.executeQuery();
	        while (rs.next())
	        {
	        	total++;
	        	groupmates[total] = new Group_Mate();
	        	groupmates[total].setId(rs.getString(1));
	        	groupmates[total].setNickname(rs.getString(2));
	        }
	        pstmt.close();
	        conn.close();
	    }
		catch (SQLException e) 
		{
	        e.printStackTrace();
	    }
		groupmates[0].setTotal_groupmates(total);
		return groupmates;
	}
	
}
