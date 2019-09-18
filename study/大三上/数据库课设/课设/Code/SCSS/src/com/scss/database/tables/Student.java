package com.scss.database.tables;

import java.sql.Date;

public class Student {
	private String student_id
		,student_name
		,major_id
		,sex
		,id_card
		,phone;
	
	private int clas;
	private Date birthday;
	
	public String getStudent_id() {
		return student_id;
	}
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	public int getClas() {
		return clas;
	}
	public void setClas(int clas) {
		this.clas = clas;
	}
	public String getMajor_id() {
		return major_id;
	}
	public void setMajor_id(String major_id) {
		this.major_id = major_id;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getId_card() {
		return id_card;
	}
	public void setId_card(String id_card) {
		this.id_card = id_card;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getStudent_name() {
		return student_name;
	}
	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}
	public String toString() {
		String res="";
		res+="student_id: " + student_id + "\n";
		res+="student_name: " + student_name + "\n";
		res+="clas: " + clas + "\n";
		res+="major_id: " + major_id + "\n";
		res+="sex: " + sex + "\n";
		res+="birthday: " + birthday + "\n";
		res+="id_card: " + id_card + "\n";
		res+="phone: " + phone + "\n";
		return res;
	}
	
}
