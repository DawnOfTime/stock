package com.stock.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Table;

@Entity
public class GoodsApply {
	@Id
	@GeneratedValue(generator="paymentableGenerator")
	@GenericGenerator(name="paymentableGenerator",strategy="uuid")
	private String id;
	private String goodscode;
	/*@Transient*///不持久化
//	private String goodtype;
	/*@Transient*/
//	private String names;
	/*@Transient*/
//	private String spec;
	private String model;
	private String unit;
	private String number;
	private String applicant;
	private String applytime;
	private String approver;
	private String approvaltime;
	private String state;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getGoodscode() {
		return goodscode;
	}
	public void setGoodscode(String goodscode) {
		this.goodscode = goodscode;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getApplicant() {
		return applicant;
	}
	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}
	public String getApplytime() {
		return applytime;
	}
	public void setApplytime(String applytime) {
		this.applytime = applytime;
	}
	public String getApprover() {
		return approver;
	}
	public void setApprover(String approver) {
		this.approver = approver;
	}
	public String getApprovaltime() {
		return approvaltime;
	}
	public void setApprovaltime(String approvaltime) {
		this.approvaltime = approvaltime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "GoodsApply [id=" + id + ", goodscode=" + goodscode + ", model="
				+ model + ", unit=" + unit + ", number=" + number
				+ ", applicant=" + applicant + ", applytime=" + applytime
				+ ", approver=" + approver + ", approvaltime=" + approvaltime
				+ ", state=" + state + "]";
	}
}
