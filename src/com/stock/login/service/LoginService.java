package com.stock.login.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.stock.login.dao.LoginDao;
import com.stock.pojo.Personnel;

public class LoginService {
	HttpServletRequest request=ServletActionContext.getRequest();
//	HttpServletResponse response=ServletActionContext.getResponse();
	private LoginDao dao = new LoginDao();
	public String login(Personnel personnel) {
		Personnel person = dao.login(personnel);
		String strmessage = "";
		if(null!=person){
			if(person.getPassword().equals(personnel.getPassword())){
				HttpSession httpSession = request.getSession();
				httpSession.setAttribute("person", person);
			}else{
				strmessage ="密码输入错误！！";
			}
		}else{
			strmessage ="用户名不存在！！";
		}
		
		return strmessage;
	}

}
