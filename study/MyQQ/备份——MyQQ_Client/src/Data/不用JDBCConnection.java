package Data;
//package Data;
//
///*
// * 功能：用于连接数据库
// */
//
//import com.mysql.jdbc.Connection;
//import com.mysql.jdbc.PreparedStatement;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class JDBCConnection 
//{
//	
//	public static void main(String args[])
//	{
//		getConn();
//	}
//	
//	//连接数据库
//	public static Connection getConn() 
//	{
//	    String driver = "com.mysql.jdbc.Driver";
//	    String url = "jdbc:mysql://localhost:3306/myqq";
//	    String username = "root";
//	    String password = "123456";
//	    Connection conn = null;
//	    try 
//	    {
//	        Class.forName(driver); //classLoader,加载对应驱动
//	        conn = (Connection) DriverManager.getConnection(url, username, password);
//	    } 
//	    catch (ClassNotFoundException e) 
//	    {
//	        e.printStackTrace();
//	    } 
//	    catch (SQLException e) 
//	    {
//	        e.printStackTrace();
//	    }
//	    return conn;
//	}
//}
