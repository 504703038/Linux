package Data;

/*
 * ���ܣ������������ݿ�
 */

import Common.User;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBC_Connection 
{
	
	public static void main(String args[])
	{
		getConn();
	}
	
	//�������ݿ�
	public static Connection getConn() 
	{
	    String driver = "com.mysql.jdbc.Driver";
	    String url = "jdbc:mysql://localhost:3306/myqq";
	    String username = "root";
	    String password = "";
	    Connection conn = null;
	    try 
	    {
	        Class.forName(driver); //classLoader,���ض�Ӧ����
	        conn = (Connection) DriverManager.getConnection(url, username, password);
//	        System.out.println("Done");
	    } 
	    catch (ClassNotFoundException e) 
	    {
	        e.printStackTrace();
	    } 
	    catch (SQLException e) 
	    {
	        e.printStackTrace();
	    }
	    return conn;
	}
}
