package com.scss.database.tables;

public class Student_grade {
    
    String semseter
    	,course_name
    	,type
    	,section_id
    	,limi
    	,student_id;
    int year;
    float credit
	    ,usual_grade
		,final_grade
		,grade;
    

	public String getStudent_id() {
		return student_id;
	}
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	public String getSemseter() {
		return semseter;
	}
	public void setSemseter(String semseter) {
		this.semseter = semseter;
	}
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	public String getSection_id() {
		return section_id;
	}
	public void setSection_id(String section_id) {
		this.section_id = section_id;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public float getUsual_grade() {
		return usual_grade;
	}
	public void setUsual_grade(float usual_grade) {
		this.usual_grade = usual_grade;
	}
	public float getFinal_grade() {
		return final_grade;
	}
	public void setFinal_grade(float final_grade) {
		this.final_grade = final_grade;
	}
	public float getGrade() {
		return grade;
	}
	public void setGrade(float grade) {
		this.grade = grade;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public float getCredit() {
		return credit;
	}
	public void setCredit(float credit) {
		this.credit = credit;
	}
	public String getLimi() {
		return limi;
	}
	public void setLimi(String limi) {
		this.limi = limi;
	}
    
    
}
