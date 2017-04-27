package com.stock.personnel.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.opensymphony.xwork2.Action;
import com.stock.comm.HttpUtil;
import com.stock.comm.PageBean_easyui;
import com.stock.personnel.service.PersonnelServcie;
import com.stock.pojo.Personnel;

public class PersonnelAction {
	HttpServletRequest request=ServletActionContext.getRequest();
	HttpServletResponse response=ServletActionContext.getResponse();
	
	private PersonnelServcie service = new PersonnelServcie();
	private Personnel personnel;
	private String mode;
	
	private String page;
	private String rows;
	private String deleted;
 	private String inserted; 
 	private String updated;
	public String findPersonnel() throws IOException{
		//当前页  
        int intPage = Integer.parseInt((page == null || page == "0") ? "1":page);  
        //每页显示条数  
        int number = Integer.parseInt((rows == null || rows == "0") ? "10":rows);  
        //每页的开始记录  第一页为1  第二页为number +1   
        int start = (intPage-1)*number;  
        
        //拼接URL的同时解码
		PageBean_easyui pageBean = new PageBean_easyui();
		pageBean.setPagecode(intPage);
		pageBean.setPagesize(number);
		
		String pattern = "";
		String getpar = HttpUtil.getParameterUrl(request.getParameterMap(),request,pattern);
		pageBean.setUrl(getpar);
		
		List list = service.findPersonnel(personnel,pageBean,mode);
		Map<String, Object> jsonMap = new HashMap<String, Object>();//定义map 
		jsonMap.put("total", pageBean.getTotalrecord());//total键 存放总记录数，必须的  
        jsonMap.put("rows", list);//rows键 存放每页记录 list  
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.write(JSONObject.fromObject(jsonMap)+"");
		out.close();
		return null;
	}
	public String save() throws IOException{
		JSONArray jsonArray = null;
		Personnel[] personnel = null;
		String finall = null;
		if(null!=inserted){
			jsonArray=JSONArray.fromObject(inserted);
			personnel = (Personnel[]) JSONArray.toArray(jsonArray,Personnel.class);
			finall = service.inserted(personnel);
		}
		if(null!=deleted){
	 		jsonArray = JSONArray.fromObject(deleted);
	 		personnel = (Personnel[]) JSONArray.toArray(jsonArray,Personnel.class);
	 		finall = service.deleted(personnel);
	 	}
	 	if(null!=updated){
	 		jsonArray = JSONArray.fromObject(updated);
	 		personnel = (Personnel[]) JSONArray.toArray(jsonArray,Personnel.class);
	 		finall = service.updated(personnel);
	 	}
	 	response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.write(finall);
		out.close();
		return null;
	}
	//获取所有角色
	public String ruletype() throws IOException{
		List list = service.ruletype();
		String str = JSONArray.fromObject(list)+"";
		str = str.replaceAll("rnum", "valueField");
		str = str.replaceAll("rname", "textField");
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.write(str);
		out.close();
		return null;
	}
	//获取所有部门
	public String department() throws IOException{
		List list = service.department();
		String str = JSONArray.fromObject(list)+"";
		str = str.replaceAll("num", "valueField");
		str = str.replaceAll("name", "textField");
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.write(str);
		out.close();
		return null;
	}
	//重置密码
	public String resetpassword() throws IOException{
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++       "+personnel);
		service.resetpassword(personnel);
		return null;
	}
	public Personnel getPersonnel() {
		return personnel;
	}
	public void setPersonnel(Personnel personnel) {
		this.personnel = personnel;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getRows() {
		return rows;
	}
	public void setRows(String rows) {
		this.rows = rows;
	}
	public String getDeleted() {
		return deleted;
	}
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	public String getInserted() {
		return inserted;
	}
	public void setInserted(String inserted) {
		this.inserted = inserted;
	}
	public String getUpdated() {
		return updated;
	}
	public void setUpdated(String updated) {
		this.updated = updated;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
}
