package com.scss.database.tables;

public class User {
	private String user_id;
	private String password;
	private int role;
	private Student student;
	private Instructor instructor;
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public String getPassword() {
		return password;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Instructor getInstructor() {
		return instructor;
	}
	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}
	public String toString() {
		String res="";
		res+="user_id: " + user_id + "\n";
		res+="password: " + password + "\n";
		res+="role: " + role + "\n";
		return res;
	}
}
