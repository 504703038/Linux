package com.scss.database.manage;

import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.scss.database.tables.*;

public class DBS_update {
	static Connection conn = DBS_connection.getConn();
	static PreparedStatement pstmt;
	
	private static void update(String sql) {
		try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        pstmt.executeUpdate();
	    }
		catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public static void update(User user){
		String sql = "update user set ";
		sql+="password='"+user.getPassword()+"',";
		sql+="role='"+user.getRole()+"' ";
		sql+="where user_id = '"+user.getUser_id()+"'";
		update(sql);
	}
	
	public static void update(Student stu)
	{
		String sql = "update student set ";
		sql+="student_name='"+stu.getStudent_name()+"',";
		sql+="clas="+stu.getClas()+",";
		sql+="major_id='"+stu.getMajor_id()+"',";
		sql+="sex='"+stu.getSex()+"',";
		sql+="birthday=date_format('"+stu.getBirthday()+"','%Y-%m-%d'),";
		sql+="id_card='"+stu.getId_card()+"',";
		sql+="phone='"+stu.getPhone()+"' ";
		sql+="where student_id='"+stu.getStudent_id()+"'";
		System.out.println("student_update:"+sql);
		update(sql);
	}
	
	
	public static void update(Instructor ins)
	{
		String sql = "update instructor set ";
		sql+="instructor_name='"+ins.getInstructor_name()+"',";
		sql+="sex='"+ins.getSex()+"',";
		sql+="birthday=date_format('"+ins.getBirthday()+"','%Y-%m-%d'),";
		sql+="id_card='"+ins.getId_card()+"',";
		sql+="phone='"+ins.getPhone()+"' ";
		sql+="where instructor_id='"+ins.getInstructor_id()+"'";
		System.out.println("instructor_update:"+sql);
		update(sql);
	}
}
