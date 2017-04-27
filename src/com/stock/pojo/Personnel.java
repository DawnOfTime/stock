package com.stock.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Personnel {
	@Id
	@GeneratedValue(generator="paymentableGenerator")
	@GenericGenerator(name="paymentableGenerator",strategy="uuid")
	private String id;
	private String name;
	private String sex;
	private String birthday;
	private String id_card_num;
	private String department_num;
	private String state;
	private String password;
	private String rnum;
	private String remarks;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getId_card_num() {
		return id_card_num;
	}
	public void setId_card_num(String id_card_num) {
		this.id_card_num = id_card_num;
	}
	public String getDepartment_num() {
		return department_num;
	}
	public void setDepartment_num(String department_num) {
		this.department_num = department_num;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRnum() {
		return rnum;
	}
	public void setRnum(String rnum) {
		this.rnum = rnum;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Override
	public String toString() {
		return "Personnel [id=" + id + ", name=" + name + ", sex=" + sex
				+ ", birthday=" + birthday + ", id_card_num=" + id_card_num
				+ ", department_num=" + department_num + ", state=" + state
				+ ", password=" + password + ", rnum=" + rnum + ", remarks="
				+ remarks + "]";
	}
}
