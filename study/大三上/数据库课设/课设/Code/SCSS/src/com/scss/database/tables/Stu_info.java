package com.scss.database.tables;

import java.sql.Date;

public class Stu_info {
	String student_id
		,student_name
		,sex
		,id_card
		,phone
		,depart_name
		,major_name;
	int clas;
	Date birthday;
	
	
	public String getStudent_id() {
		return student_id;
	}
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
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
	public int getClas() {
		return clas;
	}
	public void setClas(int clas) {
		this.clas = clas;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getStudent_name() {
		return student_name;
	}
	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}
	public String getDepart_name() {
		return depart_name;
	}
	public void setDepart_name(String depart_name) {
		this.depart_name = depart_name;
	}
	public String getMajor_name() {
		return major_name;
	}
	public void setMajor_name(String major_name) {
		this.major_name = major_name;
	}
	public String toString() {
		String res="";
		res+="student_id: " + student_id + "\n";
		res+="student_name: " + student_name + "\n";
		res+="clas: " + clas + "\n";
		res+="major: " + major_name + "\n";
		res+="depart_name: " + depart_name + "\n";
		res+="sex: " + sex + "\n";
		res+="birthday: " + birthday + "\n";
		res+="id_card: " + id_card + "\n";
		res+="phone: " + phone + "\n";
		return res;
	}
}
