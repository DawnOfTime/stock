package com.stock.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Rule_Menu {
	@Id
	@GeneratedValue(generator="paymentableGenerator")
	@GenericGenerator(name="paymentableGenerator",strategy="uuid")
	private String id;
	private String rid;
	private String mnum;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRid() {
		return rid;
	}
	public void setRid(String rid) {
		this.rid = rid;
	}
	public String getMnum() {
		return mnum;
	}
	public void setMnum(String mnum) {
		this.mnum = mnum;
	}
	@Override
	public String toString() {
		return "Rule_Menu [id=" + id + ", rid=" + rid + ", mnum=" + mnum + "]";
	}
}
