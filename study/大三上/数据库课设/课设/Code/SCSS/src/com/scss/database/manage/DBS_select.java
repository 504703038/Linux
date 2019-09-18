package com.scss.database.manage;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.scss.database.tables.*;


public class DBS_select {
	static Connection conn = DBS_connection.getConn();
	static PreparedStatement pstmt;
	
	
	private static String Fuzzy_search(String column, String value) {
		System.out.println("Fuzzy_search:"+value);
		String sql="("+column+" like '%";
		for(int i=0;i<value.length();i++) {
			sql+=value.charAt(i)+"%";
		}
		sql+="') ";
		return sql;
	}
	
	//从user表中查询
	public static User select(User user)
	{
		String sql = "select * from user where user_id ='"+user.getUser_id()+"'";
		System.out.println("user_select:"+sql);
		try {
			pstmt = (PreparedStatement)conn.prepareStatement(sql);
			ResultSet result= pstmt.executeQuery();
			if (result.next()) {
				user.setUser_id(result.getString("user_id"));
				user.setPassword(result.getString("password"));
				user.setRole(result.getInt("role"));
				if (user!=null) {
					if (user.getRole()==0){
						Student stu = new Student();
						stu.setStudent_id(user.getUser_id());
						user.setStudent(select(stu).get(0));
					}
					if (user.getRole()==1) {
						Instructor tch = new Instructor();
						tch.setInstructor_id(user.getUser_id());
						user.setInstructor(select(tch).get(0));
						System.out.println(user.getInstructor());
					}
				}
			}else user = null;
			return user;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static ArrayList<Instructor> select(Instructor ins) {
		String sql = "select * from instructor ";
		String instructor_id = ins.getInstructor_id();
		String depart_id = ins.getDepart_id();
		if (instructor_id!=null || depart_id!=null) sql+="where ";
		if (instructor_id!=null) sql+="instructor_id = '"+instructor_id+"'";
		if (instructor_id!=null && depart_id!=null) sql+="and ";
		if (depart_id!=null) sql+="depart_id = '"+depart_id+"' ";
		System.out.println("instructor_select:"+sql);
		ArrayList<Instructor> list = new ArrayList<Instructor>();
		try {
			pstmt = (PreparedStatement)conn.prepareStatement(sql);
			ResultSet result= pstmt.executeQuery();
			while (result.next()) {
				Instructor tmp = new Instructor();
				tmp.setInstructor_id(result.getString("instructor_id"));
				tmp.setInstructor_name(result.getString("instructor_name"));
				tmp.setDepart_id(result.getString("depart_id"));
				tmp.setSex(result.getString("sex"));
				tmp.setBirthday(result.getDate("birthday"));
				tmp.setId_card(result.getString("id_card"));
				tmp.setPhone(result.getString("phone"));
				list.add(tmp);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static ArrayList<Student> select(Student stu) {
		String sql = "select * from student ";
		String student_id = stu.getStudent_id();
		String major_id = stu.getMajor_id();
		if (student_id!=null || major_id!=null) sql+="where ";
		if (student_id!=null) sql+="student_id = '"+student_id+"'";
		if (student_id!=null && major_id!=null) sql+="and ";
		if (major_id!=null) sql+="major_id = '"+major_id+"' ";
		System.out.println("student_select:"+sql);
		ArrayList<Student> list = new ArrayList<Student>();
		try {
			pstmt = (PreparedStatement)conn.prepareStatement(sql);
			ResultSet result= pstmt.executeQuery();
			while (result.next()) {
				Student tmp = new Student();
				tmp.setStudent_id(result.getString("student_id"));
				tmp.setStudent_name(result.getString("student_name"));
				tmp.setClas(result.getInt("clas"));
				tmp.setMajor_id(result.getString("major_id"));
				tmp.setSex(result.getString("sex"));
				tmp.setBirthday(result.getDate("birthday"));
				tmp.setId_card(result.getString("id_card"));
				tmp.setPhone(result.getString("phone"));
				list.add(tmp);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ArrayList<Student_training> select(Student_training stu_tra) {
		String sql = "select * from award_info ";
		String student_id = stu_tra.getStudent_id();
		String student_name = stu_tra.getStudent_name();
		if (student_id!=null || student_name!=null) sql+="where ";
		if (student_id!=null) sql+="student_id = '"+student_id+"'";
		if (student_id!=null && student_name!=null) sql+="and ";
		if (student_name!=null) sql+=Fuzzy_search("student_name", student_name);
		System.out.println("stu_training_select:"+sql);
		ArrayList<Student_training> list = new ArrayList<Student_training>();
		try {
			pstmt = (PreparedStatement)conn.prepareStatement(sql);
			ResultSet result= pstmt.executeQuery();
			while(result.next()) {
				Student_training tmp = new Student_training();
				tmp.setCase_id(result.getString("case_id")); 
				tmp.setDate(result.getDate("date"));
				tmp.setDescription(result.getString("description"));
				tmp.setLevel(result.getString("level"));
				tmp.setStudent_name(result.getString("student_name"));
				tmp.setStudent_id(result.getString("student_id"));
				tmp.setType(result.getString("type"));
				list.add(tmp);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ArrayList<Department> select(Department depart) {
		String sql = "select * from department ";
		String depart_id = depart.getDepart_id();
		String depart_name = depart.getDepart_name();
		if (depart_id!=null || depart_name!=null) sql+="where ";
		if (depart_id!=null) sql+="depart_id = '"+depart_id+"'";
		if (depart_id!=null && depart_name!=null) sql+="and ";
		if (depart_name!=null) sql+="depart_name = '"+depart_name+"'";
		System.out.println("depart_select:"+sql);
		ArrayList<Department> list = new ArrayList<Department>();
		try {
			pstmt = (PreparedStatement)conn.prepareStatement(sql);
			ResultSet result= pstmt.executeQuery();
			while(result.next()) {
				Department tmp = new Department();
				tmp.setDepart_id(result.getString("depart_id")); 
				tmp.setDepart_name(result.getString("depart_name"));
				tmp.setAddress(result.getString("address"));
				list.add(tmp);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ArrayList<Major_info> select(Major_info major_info) {
		
		String sql = "select * from major_info ";
		String depart_name = major_info.getDepart_name();
		String major_name = major_info.getMajor_name();
		if (depart_name!=null || major_name!=null) sql+="where ";
		if (depart_name!=null) sql+="depart_name = '"+depart_name+"'";
		if (depart_name!=null && major_name!=null) sql+="and ";
		if (major_name!=null) sql+="major_name = '"+major_name+"'";
		System.out.println("major_select:"+sql);
		ArrayList<Major_info> list = new ArrayList<Major_info>();
		try {
			pstmt = (PreparedStatement)conn.prepareStatement(sql);
			ResultSet result= pstmt.executeQuery();
			while(result.next()) {
				Major_info tmp = new Major_info();
				tmp.setDepart_id(result.getString("depart_id"));
				tmp.setDepart_name(result.getString("depart_name"));
				tmp.setMajor_id(result.getString("major_id"));
				tmp.setMajor_name(result.getString("major_name"));
				tmp.setStudy_time(result.getString("study_time"));
				list.add(tmp);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ArrayList<Ins_info> select(Ins_info ins_info) {
		String sql = "select * from ins_info ";
		String instructor_id = ins_info.getInstructor_id();
		String instructor_name = ins_info.getInstructor_name();
		if (instructor_id!=null || instructor_name!=null) sql+="where ";
		if (instructor_id!=null) sql+="instructor_id = '"+instructor_id+"'";
		if (instructor_id!=null && instructor_name!=null) sql+="and ";
		if (instructor_name!=null) sql+="instructor_name = '"+instructor_name+"'";
		System.out.println("ins_info_select:"+sql);
		ArrayList<Ins_info> list = new ArrayList<Ins_info>();
		try {
			pstmt = (PreparedStatement)conn.prepareStatement(sql);
			ResultSet result= pstmt.executeQuery();
			while(result.next()) {
				Ins_info tmp = new Ins_info();
				tmp.setInstructor_id(result.getString("instructor_id"));
				tmp.setInstructor_name(result.getString("instructor_name"));
				tmp.setSex(result.getString("sex"));
				tmp.setBirthday(result.getDate("birthday"));
				tmp.setId_card(result.getString("id_card"));
				tmp.setPhone(result.getString("phone"));
				tmp.setDepart_name(result.getString("depart_name"));
				list.add(tmp);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ArrayList<Stu_info> select(Stu_info stu_info) {
		String sql = "select * from stu_info ";
		String student_id = stu_info.getStudent_id();
		String student_name = stu_info.getStudent_name();
		if (student_id!=null || student_name!=null) sql+="where ";
		if (student_id!=null) sql+="student_id = '"+student_id+"'";
		if (student_id!=null && student_name!=null) sql+="and ";
		if (student_name!=null) sql+="student_name = '"+student_name+"'";
		System.out.println("stu_info_select:"+sql);
		ArrayList<Stu_info> list = new ArrayList<Stu_info>();
		try {
			pstmt = (PreparedStatement)conn.prepareStatement(sql);
			ResultSet result= pstmt.executeQuery();
			while(result.next()) {
				Stu_info tmp = new Stu_info();
				tmp.setStudent_id(result.getString("student_id"));
				tmp.setStudent_name(result.getString("student_name"));
				tmp.setClas(result.getInt("clas"));
				tmp.setMajor_name(result.getString("major_name"));
				tmp.setSex(result.getString("sex"));
				tmp.setBirthday(result.getDate("birthday"));
				tmp.setId_card(result.getString("id_card"));
				tmp.setPhone(result.getString("phone"));
				tmp.setDepart_name(result.getString("depart_name"));
				list.add(tmp);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ArrayList<Cour_info> select(Cour_info cour_info) {
		String sql = "select * from cour_info ";
		String course_id = cour_info.getCourse_id();
		String course_name = cour_info.getCourse_name();
		if (course_id!=null || course_name!=null) sql+="where ";
		if (course_id!=null) sql+="course_id = '"+course_id+"'";
		if (course_id!=null && course_name!=null) sql+="and ";
		if (course_name!=null) sql+="course_name = '"+course_name+"'";
		System.out.println("cour_info_select:"+sql);
		ArrayList<Cour_info> list = new ArrayList<Cour_info>();
		try {
			pstmt = (PreparedStatement)conn.prepareStatement(sql);
			ResultSet result= pstmt.executeQuery();
			while(result.next()) {
				Cour_info tmp = new Cour_info();
				tmp.setCourse_id(result.getString("course_id"));
				tmp.setCourse_name(result.getString("course_name"));
				tmp.setDepart_name(result.getString("depart_name"));
				tmp.setCredit(result.getFloat("credit"));
				tmp.setDescription(result.getString("description"));
				tmp.setType(result.getString("type"));
				list.add(tmp);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ArrayList<Section_info> select(Section_info sec_info) {
		String sql = "select * from section_info ";
		String section_id = sec_info.getSection_id();
		String course_name = sec_info.getCourse_name();
		String student_id=sec_info.getStudent_id();
		if (section_id!=null || course_name!=null || student_id!=null) sql+="where ";
		if (section_id!=null) sql+="section_id = '"+section_id+"' ";
		if (section_id!=null && course_name!=null) sql+="and ";
		if (course_name!=null) sql+=Fuzzy_search("course_name",course_name);
		if ((section_id!=null || course_name!=null) && student_id!=null) sql+="and ";
		if (student_id!=null) sql+="section_id not in (select section_id as section_id from select_info where student_id='"+student_id+"')";
		System.out.println("section_info_select:"+sql);
		ArrayList<Section_info> list = new ArrayList<Section_info>();
		
		try {
			pstmt = (PreparedStatement)conn.prepareStatement(sql);
			ResultSet result= pstmt.executeQuery();
			while(result.next()) {
				Section_info tmp = new Section_info();
				tmp.setSection_id(result.getString("section_id"));
				tmp.setCourse_name(result.getString("course_name"));
				tmp.setDepart_name(result.getString("depart_name"));
				tmp.setCredit(result.getFloat("credit"));
				tmp.setType(result.getString("type"));
				tmp.setSemseter(result.getString("semseter"));
				tmp.setCapacity(result.getInt("capacity"));
				tmp.setBegin_time(result.getDate("begin_time"));
				tmp.setEnd_time(result.getDate("end_time"));
				tmp.setInstructor_name(result.getString("instructor_name"));
				tmp.setYear(result.getInt("year"));
				//获取剩余课容量 begin
				Select_info sel_info = new Select_info();
				sel_info.setSection_id(tmp.getSection_id());
				ArrayList<Select_info> sel=select(sel_info);
				int res_capacity = tmp.getCapacity()-sel.size();
				tmp.setRes_capacity(res_capacity);
				//end
				list.add(tmp);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ArrayList<Select_info> select(Select_info sel_info) {
		String sql = "select * from select_info ";
		String student_id = sel_info.getStudent_id();
		String section_id  = sel_info.getSection_id();
		if (student_id!=null || section_id!=null) sql+="where ";
		if (student_id!=null) sql+="student_id = '"+student_id+"'";
		if (student_id!=null && section_id!=null) sql+="and ";
		if (section_id!=null) sql+="section_id = '"+section_id+"'";
		System.out.println("select_info_select:"+sql);
		ArrayList<Select_info> list = new ArrayList<Select_info>();
		try {
			pstmt = (PreparedStatement)conn.prepareStatement(sql);
			ResultSet result= pstmt.executeQuery();
			while(result.next()) {
				Select_info tmp = new Select_info();
				tmp.setCourse_name(result.getString("course_name"));
				tmp.setCredit(result.getFloat("credit"));
				tmp.setType(result.getString("type"));
				tmp.setBegin_time(result.getDate("begin_time"));
				tmp.setEnd_time(result.getDate("end_time"));
				tmp.setInstructor_name(result.getString("instructor_name"));
				tmp.setYear(result.getInt("year"));
				tmp.setStudent_id(student_id);
				tmp.setSection_id(result.getString("section_id"));
				list.add(tmp);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ArrayList<Student_grade> select(Student_grade sel_info) {
		String sql = "select * from stu_grade ";
		String student_id = sel_info.getStudent_id();
		String limi  = sel_info.getLimi();
		if (student_id!=null || limi!=null) sql+="where ";
		if (student_id!=null) sql+="student_id = '"+student_id+"'";
		if (student_id!=null && limi!=null) sql+="and ";
		if (limi!=null) sql+="grade"+limi;
		sql+=" and grade is not null";
		System.out.println("select_info_select:"+sql+"      student_id"+student_id);
		ArrayList<Student_grade> list = new ArrayList<Student_grade>();
		try {
			pstmt = (PreparedStatement)conn.prepareStatement(sql);
			ResultSet result= pstmt.executeQuery();
			while(result.next()) {
				Student_grade tmp = new Student_grade();
				tmp.setCourse_name(result.getString("course_name"));
				tmp.setCredit(result.getFloat("credit"));
				tmp.setType(result.getString("type"));
				tmp.setStudent_id(student_id);
				tmp.setSection_id(result.getString("section_id"));
				tmp.setSemseter(result.getString("semseter"));
				tmp.setYear(result.getInt("year"));
				tmp.setUsual_grade(result.getFloat("usual_grade"));
				tmp.setFinal_grade(result.getFloat("final_grade"));
				tmp.setGrade(result.getFloat("grade"));
				list.add(tmp);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ArrayList<Course_section> select(Course_section sec) {
		String sql = "select * from course_section ";
		String instructor_id = sec.getInstructor_id();
		String section_id  = sec.getSection_id();
		String course_id = sec.getCourse_id();
		if (instructor_id!=null || section_id!=null || course_id!=null) sql+="where ";
		if (instructor_id!=null) sql+="instructor_id = '"+instructor_id+"'";
		if (instructor_id!=null && section_id!=null) sql+="and ";
		if (section_id!=null) sql+="section_id = '"+section_id+"'";
		if ((instructor_id!=null || section_id!=null) && course_id!=null) sql+="and ";
		if (course_id!=null) sql+="course_id ='"+course_id+"' ";
		System.out.println("course_section_select:"+sql);
		ArrayList<Course_section> list = new ArrayList<Course_section>();
		try {
			pstmt = (PreparedStatement)conn.prepareStatement(sql);
			ResultSet result= pstmt.executeQuery();
			while(result.next()) {
				Course_section tmp = new Course_section();
				tmp.setSection_id(result.getString("section_id"));
				tmp.setCourse_id(result.getString("course_id"));
				tmp.setInstructor_id(instructor_id);
				tmp.setCapacity(result.getInt("capacity"));
				tmp.setYear(result.getInt("year"));
				tmp.setBegin_time(result.getDate("begin_time"));
				tmp.setEnd_time(result.getDate("end_time"));
				list.add(tmp);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public static ArrayList<Course> select(Course course) {
		String sql = "select * from course ";
		String course_id = course.getCourse_id();
		String depart_id = course.getDepart_id();
		if (course_id!=null || depart_id!=null) sql+="where ";
		if (course_id!=null) sql+="course_id ='"+course_id+"' ";
		if (course_id!=null && depart_id!=null) sql+="and ";
		if (depart_id!=null) sql+="depart_id ='"+depart_id+"' ";
		System.out.println("course_select:"+sql);
		ArrayList<Course> list = new ArrayList<Course>();
		try {
			pstmt = (PreparedStatement)conn.prepareStatement(sql);
			ResultSet result= pstmt.executeQuery();
			while(result.next()) {
				Course tmp = new Course();
				tmp.setCourse_id(result.getString("course_id"));
				tmp.setCourse_name(result.getString("course_name"));
				tmp.setType(result.getString("type"));
				tmp.setCredit(result.getFloat("credit"));
				tmp.setDepart_id(result.getString("depart_id"));
				tmp.setDescription(result.getString("description"));
				list.add(tmp);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ArrayList<Course_selection> select(Course_selection sel) {
		String sql = "select * from course_selection ";
		String section_id = sel.getSection_id();
		String student_id = sel.getStudent_id();
		if (section_id!=null || student_id!=null) sql+="where ";
		if (section_id!=null) sql+="section_id ='"+section_id+"' ";
		if (section_id!=null && student_id!=null) sql+="and ";
		if (student_id!=null) sql+="student_id ='"+student_id+"' ";
		System.out.println("course_selection_select:"+sql);
		ArrayList<Course_selection> list = new ArrayList<Course_selection>();
		try {
			pstmt = (PreparedStatement)conn.prepareStatement(sql);
			ResultSet result= pstmt.executeQuery();
			while(result.next()) {
				Course_selection tmp = new Course_selection();
				tmp.setSection_id(result.getString("section_id"));
				tmp.setStudent_id(result.getString("student_id"));
				tmp.setUsual_grade(result.getFloat("usual_grade"));
				tmp.setFinal_grade(result.getFloat("final_grade"));
				tmp.setGrade(result.getFloat("grade"));
				tmp.setGpa(result.getFloat("gpa"));
				list.add(tmp);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static ArrayList<Major> select(Major major) {
		String sql = "select * from major ";
		String major_id = major.getMajor_id();
		String depart_id = major.getDepart_id();
		if (major_id!=null || depart_id!=null) sql+="where ";
		if (major_id!=null) sql+="major_id ='"+major_id+"' ";
		if (major_id!=null && depart_id!=null) sql+="and ";
		if (depart_id!=null) sql+="depart_id ='"+depart_id+"' ";
		System.out.println("course_selection_select:"+sql);
		ArrayList<Major> list = new ArrayList<Major>();
		try {
			pstmt = (PreparedStatement)conn.prepareStatement(sql);
			ResultSet result= pstmt.executeQuery();
			while(result.next()) {
				Major tmp = new Major();
				tmp.setDepart_id(result.getString("depart_id"));
				tmp.setMajor_id(result.getString("major_id"));
				tmp.setMajor_name(result.getString("major_name"));
				tmp.setStudy_time(result.getString("study_time"));
				list.add(tmp);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
