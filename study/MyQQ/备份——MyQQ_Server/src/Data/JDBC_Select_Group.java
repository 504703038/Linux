package Data;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class JDBC_Select_Group 
{
	//返回群组的Id
	public String[] Select_Group(String Id)
	{
		String[] groups = new String[100];
		groups[0]="";
		
		Connection conn = JDBC_Connection.getConn();
		PreparedStatement pstmt;
		try 
		{
	        pstmt = (PreparedStatement) conn.prepareStatement("select Group_Id,Group_Name from Group_Info where Groupmate_Id="+Id);
	        ResultSet rs = pstmt.executeQuery();
	        if (rs.next())
	        {
	        	groups[0]+=" ";
	        	groups[groups[0].length()]=rs.getString(1);
	        	groups[groups[0].length()]+="%)$&)#)#*";
	        	groups[groups[0].length()]+=rs.getString(2);
	        }
	        pstmt.close();
	        conn.close();
	    }
		catch (SQLException e) 
		{
	        e.printStackTrace();
	    }
		return groups;
	}
}
