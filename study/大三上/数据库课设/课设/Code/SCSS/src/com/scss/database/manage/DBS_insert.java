package com.scss.database.manage;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.scss.database.tables.Course_selection;
import com.scss.database.tables.Student_training;

public class DBS_insert {
	static Connection conn = DBS_connection.getConn();
	static PreparedStatement pstmt;
	
	private static void insert(String sql) {
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        pstmt.executeUpdate();
	    } 
	    catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public static void insert(Course_selection co_sel) {
		// TODO Auto-generated method stub
		String sql = "insert into course_selection (student_id,section_id,usual_grade,final_grade,grade) values (";
		sql+="'"+co_sel.getStudent_id()+"'";
		sql+=","+"'"+co_sel.getSection_id()+"'";
		sql+=","+(co_sel.getUsual_grade()==0?"null":co_sel.getUsual_grade());
		sql+=","+(co_sel.getFinal_grade()==0?"null":co_sel.getFinal_grade());
		sql+=","+(co_sel.getGrade()==0?"null":co_sel.getGrade())+")";
		System.out.println("course_selection_insert:"+sql);
		insert(sql);
	}
	
	public static void insert(Student_training stu_tra) {
		Date time = new Date();
		SimpleDateFormat dateFormat= new SimpleDateFormat("yyyyMMddhhmmss");
		String case_id=dateFormat.format(time);
		String sql = "insert into student_training (case_id,student_id,date,level,type,description) values (";
		sql+="'"+case_id+"',";
		sql+="'"+stu_tra.getStudent_id()+"',";
		sql+="date_format('"+stu_tra.getDate()+"','%Y-%m-%d'),";
		sql+="'"+stu_tra.getLevel()+"',";
		sql+="'"+stu_tra.getType()+"',";
		sql+="'"+stu_tra.getDescription()+"')";
		System.out.println("student_training_insert:"+sql);
		insert(sql);
	}
	
}
