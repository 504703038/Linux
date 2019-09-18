package com.scss.database.tables;

import java.sql.Date;

public class Instructor {
	private String instructor_id
		,instructor_name
		,depart_id
		,sex
		,id_card
		,phone;
	private Date birthday;
	
	
	public String getInstructor_id() {
		return instructor_id;
	}
	public void setInstructor_id(String instructor_id) {
		this.instructor_id = instructor_id;
	}
	public String getInstructor_name() {
		return instructor_name;
	}
	public void setInstructor_name(String instructor_name) {
		this.instructor_name = instructor_name;
	}
	public String getDepart_id() {
		return depart_id;
	}
	public void setDepart_id(String depart_id) {
		this.depart_id = depart_id;
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
	public String toString() {
		String res="";
		res+="instructor_id: " + instructor_id + "\n";
		res+="instructor_name: " + instructor_name + "\n";
		res+="depart_id: " + depart_id + "\n";
		res+="sex: " + sex + "\n";
		res+="birthday: " + birthday + "\n";
		res+="id_card: " + id_card + "\n";
		res+="phone: " + phone + "\n";
		return res;
	}
	
}
