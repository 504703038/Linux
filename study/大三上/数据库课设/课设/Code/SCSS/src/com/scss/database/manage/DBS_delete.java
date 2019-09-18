package com.scss.database.manage;

import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.scss.database.tables.*;

public class DBS_delete {
	static Connection conn = DBS_connection.getConn();
	static PreparedStatement pstmt;
	
	private static void delete(String sql) {
		try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        pstmt.executeUpdate();
	    }
		catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public static void delete(Course_selection co_sel) {
		// TODO Auto-generated method stub
		String sql = "delete from course_selection ";
		String student_id = co_sel.getStudent_id();
		String section_id = co_sel.getSection_id();
		if (student_id!=null || section_id!=null) sql+="where ";
		if (student_id!=null) sql+="student_id ='"+student_id+"' ";
		if (student_id!=null && section_id!=null) sql+="and ";
		if (section_id!=null) sql+="section_id ='"+section_id+"' ";
		System.out.println("course_selection_delete:"+sql);
		delete(sql);
	}
	
	public static void delete(Student_training stu_tra) {
		// TODO Auto-generated method stub
		String sql = "delete from student_training ";
		String case_id = stu_tra.getCase_id();
		if (case_id!=null) sql+="where ";
		if (case_id!=null) sql+="case_id ='"+case_id+"' ";
		System.out.println("student_training_delete:"+sql);
		delete(sql);
	}
	public static void delete(Course_section sec) {
		String sql = "delete from course_section ";
		String section_id = sec.getSection_id();
		//将所有关于这门课(section)的选课数据删除	begin
		Course_selection sel = new Course_selection();
		sel.setSection_id(section_id);
		ArrayList<Course_selection> list = DBS_select.select(sel);
		for(Course_selection tmp:list) {
			delete(tmp);
		}
		//end
		if (section_id!=null) sql+="where ";
		if (section_id!=null) sql+="section_id ='"+section_id+"' ";
		System.out.println("course_section_delete"+sql);
		delete(sql);
	}

	public static void delete(Course course) {
		String sql = "delete from course ";
		String course_id = course.getCourse_id();
		//删除所有关于这门课(course)的开课信息		begin
		Course_section sec = new Course_section();
		sec.setCourse_id(course_id);
		ArrayList<Course_section> list = DBS_select.select(sec);
		for(Course_section tmp:list) {
			delete(tmp);
		}
		//end
		if (course_id!=null) sql+="where ";
		if (course_id!=null) sql+="course_id ='"+course_id+"' ";
		System.out.println("course_delete"+sql);
		delete(sql);
	}
	
	public static void delete(User user) {
		String sql = "delete from user ";
		String user_id = user.getUser_id();
		if (user_id!=null) sql+="where ";
		if (user_id!=null) sql+="user_id ='"+user_id+"' ";
		System.out.println("user_delete:"+sql);
		delete(sql);
	}
	
	
	public static void delete(Student stu) {
		String sql = "delete from student ";
		String student_id = stu.getStudent_id();
		//删除该学生的用户信息		begin
		User user = new User();
		user.setUser_id(student_id);
		delete(user);
		//end
		//删除该学生的选课信息		begin
		Course_selection sel = new Course_selection();
		sel.setStudent_id(student_id);
		ArrayList<Course_selection> Course_selection_list = DBS_select.select(sel);
		for(Course_selection tmp : Course_selection_list) {
			delete(tmp);
		}
		//end
		//删除该学生的奖罚信息		begin
		Student_training stu_tra = new Student_training();
		stu_tra.setStudent_id(student_id);
		ArrayList<Student_training> Student_training_list = DBS_select.select(stu_tra);
		for(Student_training tmp : Student_training_list) {
			delete(tmp);
		}
		//end
		if (student_id!=null) sql+="where ";
		if (student_id!=null) sql+="student_id ='"+student_id+"' ";
		System.out.println("student_delete"+sql);
		delete(sql);
	}
	
	
	public static void delete(Instructor ins) {
		String sql = "delete from instructor ";
		String instructor_id = ins.getInstructor_id();
		//删除该教师的用户信息		begin
		User user = new User();
		user.setUser_id(instructor_id);
		delete(user);
		//end
		//删除该教师的开课信息		begin
		Course_section sec = new Course_section();
		sec.setInstructor_id(instructor_id);
		ArrayList<Course_section> list = DBS_select.select(sec);
		for(Course_section tmp:list) {
			delete(tmp);
		}
		//end
		if (instructor_id!=null) sql+="where ";
		if (instructor_id!=null) sql+="instructor_id ='"+instructor_id+"' ";
		System.out.println("student_delete"+sql);
		delete(sql);
	}
	
	public static void delete(Major major) {
		String sql = "delete from major ";
		String major_id = major.getMajor_id();
		//删除该系的所有学生		begin
		Student stu = new Student();
		stu.setMajor_id(major_id);
		ArrayList<Student> Student_list = DBS_select.select(stu);
		for(Student tmp:Student_list) {
			System.out.println("\n"+"student will be deleted:"+tmp+"\n");
			delete(tmp);
		}
		//end
		if (major_id!=null) sql+="where ";
		if (major_id!=null) sql+="major_id ='"+major_id+"' ";
		System.out.println("major_delete"+sql);
		delete(sql); 
	}

	public static void delete(Department depart) {
		String sql = "delete from department ";
		String depart_id = depart.getDepart_id();
		//删除该学院的所有教师		begin
		Instructor ins = new Instructor();
		ins.setDepart_id(depart_id);
		ArrayList<Instructor> Instructor_list = DBS_select.select(ins);
		for(Instructor tmp:Instructor_list) {
			System.out.println("\n"+"instructor will be deleted:"+tmp+"\n");
			delete(tmp);
		}
		//end
		//删除该学院的所有专业		begin
		Major major = new Major();
		major.setDepart_id(depart_id);
		ArrayList<Major> Major_list = DBS_select.select(major);
		for(Major tmp:Major_list) {
			delete(tmp);
		}
		//end
		//删除该学院开设的所有课程		begin
		Course course = new Course();
		course.setDepart_id(depart_id);
		ArrayList<Course> Course_list = DBS_select.select(course);
		for(Course tmp:Course_list) {
			delete(tmp);
		}
		//end
		if (depart_id!=null) sql+="where ";
		if (depart_id!=null) sql+="depart_id ='"+depart_id+"' ";
		System.out.println("depart_delete:"+sql);
		delete(sql); 
	}
	
	
}
