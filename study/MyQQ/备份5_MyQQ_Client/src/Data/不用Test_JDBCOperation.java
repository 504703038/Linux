package Data;
//package Data;
//
//import com.mysql.jdbc.Connection;
//import com.mysql.jdbc.PreparedStatement;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class Test_JDBCOperation 
//{
//	public static void  main(String args[])
//	{
//		Test_JDBCOperation.getAll();
//		Test_JDBCOperation.insert(new Student("Achilles", "Male", "14"));
//		Test_JDBCOperation.getAll();
//		Test_JDBCOperation.update(new Student("Bean", "", "7"));
//		Test_JDBCOperation.delete("Achilles");
//		Test_JDBCOperation.getAll();
//	}
//	
//	private static Connection getConn()
//	{
//		String driver = "com.mysql.jdbc.Driver";
//		String url = "jdbc:mysql://localhost:3306/samp_db";
//		String username = "root";
//		String password = "yangSHIFA12345";
//		Connection coon = null;
//		try
//		{
//			Class.forName(driver);
//			coon = (Connection) DriverManager.getConnection(url,username,password);
//		}
//		catch (Exception e)
//		{
//			e.printStackTrace();
//		}
//		return coon;
//	}
//
//	private static int insert(Student student) {
//	    Connection conn = getConn();
//	    int i = 0;
//	    String sql = "insert into students (Name,Sex,Age) values(?,?,?)";
//	    PreparedStatement pstmt;
//	    try {
//	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
//	        pstmt.setString(1, student.getName());
//	        pstmt.setString(2, student.getSex());
//	        pstmt.setString(3, student.getAge());
//	        i = pstmt.executeUpdate();
//	        pstmt.close();
//	        conn.close();
//	    } catch (SQLException e) {
//	        e.printStackTrace();
//	    }
//	    return i;
//	}
//	
//	private static int update(Student student) {
//	    Connection conn = getConn();
//	    int i = 0;
//	    String sql = "update students set Age='" + student.getAge() + "' where Name='" + student.getName() + "'";
//	    PreparedStatement pstmt;
//	    try {
//	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
//	        i = pstmt.executeUpdate();
//	        System.out.println("resutl: " + i);
//	        pstmt.close();
//	        conn.close();
//	    } catch (SQLException e) {
//	        e.printStackTrace();
//	    }
//	    return i;
//	}
//	
//	
//	private static Integer getAll() {
//	    Connection conn = getConn();
//	    String sql = "select * from students";
//	    PreparedStatement pstmt;
//	    try {
//	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
//	        ResultSet rs = pstmt.executeQuery();
//	        int col = rs.getMetaData().getColumnCount();
//	        System.out.println("============================");
//	        while (rs.next()) {
//	            for (int i = 1; i <= col; i++) {
//	                System.out.print(rs.getString(i) + "\t");
//	                if ((i == 2) && (rs.getString(i).length() < 8)) {
//	                    System.out.print("\t");
//	                }
//	             }
//	            System.out.println("");
//	        }
//	            System.out.println("============================");
//	    } catch (SQLException e) {
//	        e.printStackTrace();
//	    }
//	    return null;
//	}
//	
//	private static int delete(String name) {
//	    Connection conn = getConn();
//	    int i = 0;
//	    String sql = "delete from students where Name='" + name + "'";
//	    PreparedStatement pstmt;
//	    try {
//	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
//	        i = pstmt.executeUpdate();
//	        System.out.println("resutl: " + i);
//	        pstmt.close();
//	        conn.close();
//	    } catch (SQLException e) {
//	        e.printStackTrace();
//	    }
//	    return i;
//	}
//	
//	
//	
//	static class Student {
//        private String Id;
//        private String Name;
//        private String Sex;
//        private String Age;
//
//        Student(String Name, String Sex, String Age) {
//            this.Id = null; //default
//            this.Name = Name;
//            this.Sex = Sex;
//            this.Age = Age;
//        }
//
//        public String getId() {
//            return Id;
//        }
//
//        public void setId(String Id) {
//            this.Id = Id;
//        }
//
//        public String getName() {
//            return Name;
//        }
//
//        public void setName(String Name) {
//            this.Name = Name;
//        }
//
//        public String getSex() {
//            return Sex;
//        }
//
//        public void setSex(String Sex) {
//            this.Sex = Sex;
//        }
//
//        public String getAge() {
//            return Age;
//        }
//
//        public void setage(String Age) {
//            this.Age = Age;
//        }
//}
//}
