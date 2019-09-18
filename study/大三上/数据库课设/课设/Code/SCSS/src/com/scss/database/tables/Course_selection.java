package com.scss.database.tables;

public class Course_selection {
	private String student_id;
	private String section_id;
	private float usual_grade;
	private float final_grade;
	private float grade;
	private float gpa;
	
	public String getStudent_id() {
		return student_id;
	}
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	public String getSection_id() {
		return section_id;
	}
	public void setSection_id(String section_id) {
		this.section_id = section_id;
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
	public float getGpa() {
		return gpa;
	}
	public void setGpa(float gpa) {
		this.gpa = gpa;
	}
	
}
