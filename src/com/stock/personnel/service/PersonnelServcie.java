package com.stock.personnel.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.junit.Test;

import com.stock.comm.PageBean_easyui;
import com.stock.personnel.dao.PersonnelDao;
import com.stock.pojo.Personnel;

public class PersonnelServcie {
	HttpServletRequest request=ServletActionContext.getRequest();
	private PersonnelDao dao = new PersonnelDao();

	public List findPersonnel(Personnel personnel, PageBean_easyui pageBean, String mode) {
		HttpSession session = request.getSession();
		Personnel person = (Personnel) session.getAttribute("person");
		if(null!=mode && mode.equals("1")){
			personnel = person;
		}
		return dao.findPersonnel(personnel,pageBean);
	}

	public String inserted(Personnel[] personnel) {
		return dao.inserted(personnel);
	}

	public String deleted(Personnel[] personnel) {
		// TODO Auto-generated method stub
		return dao.deleted(personnel);
	}

	public String updated(Personnel[] personnel) {
		// TODO Auto-generated method stub
		return dao.updated(personnel);
	}

	public List ruletype() {
		// TODO Auto-generated method stub
		return dao.ruletype();
	}
	public List department() {
		// TODO Auto-generated method stub
		return dao.department();
	}
	@Test
	public void test(){
//		String str = JSONArray.fromObject(dao.department())+"";
//		str = str.replaceAll("num", "valueField");
//		str = str.replaceAll("name", "textField");
		String a = "asdfasdfasdfasdfasdfasd \r fasdfasdfasdfasdfasdfasdfasdf";
		System.out.println(a);
	}

	public void resetpassword(Personnel personnel) {
		// TODO Auto-generated method stub
		dao.resetpassword(personnel);
	}
}
