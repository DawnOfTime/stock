package com.stock.login.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.stock.login.service.LoginService;
import com.stock.pojo.Acc;
import com.stock.pojo.Personnel;

public class LoginAction {
	HttpServletRequest request=ServletActionContext.getRequest();
	private LoginService service = new LoginService();
	private Personnel personnel;
	public String login(){
		String strMessage = service.login(personnel);
		if(strMessage.equals("")){
			return Action.SUCCESS;
		}else{
			request.setAttribute("strMessage", strMessage);
			return "lost";
		}
	}
	public String exit(){
		HttpSession httpSession = request.getSession();//获取session
		httpSession.removeAttribute("person");
		return Action.SUCCESS;
	}
	public Personnel getPersonnel() {
		return personnel;
	}
	public void setPersonnel(Personnel personnel) {
		this.personnel = personnel;
	}
}
