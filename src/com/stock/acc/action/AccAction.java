package com.stock.acc.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.stock.acc.service.AccService;
import com.stock.comm.HttpUtil;
import com.stock.comm.PageBean_easyui;
import com.stock.pojo.Acc;

public class AccAction {
	HttpServletRequest request=ServletActionContext.getRequest();
	HttpServletResponse response=ServletActionContext.getResponse();
	
	private String page;
	private String rows;
	private String deleted;
 	private String inserted; 
 	private String updated;
 	
 	private Acc acc;
 	private AccService service = new AccService();
 	public String find() throws Exception{
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
		
		List list = service.find(acc,pageBean);
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();//定义map 
		jsonMap.put("total", pageBean.getTotalrecord());//total键 存放总记录数，必须的  
        jsonMap.put("rows", list);//rows键 存放每页记录 list  
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		System.out.println(JSONObject.fromObject(jsonMap)+"");
		out.write(JSONObject.fromObject(jsonMap)+"");
		out.close();
 		return null;
 	}
 	public String save() throws IOException{
 		JSONArray jsonArray = null;
		Acc[] acc = null;
		String finall = null;
		if(null!=inserted){
			jsonArray=JSONArray.fromObject(inserted);
			acc = (Acc[]) JSONArray.toArray(jsonArray,Acc.class);
			finall = service.inserted(acc);
		}
		if(null!=deleted){
	 		jsonArray = JSONArray.fromObject(deleted);
	 		acc = (Acc[]) JSONArray.toArray(jsonArray,Acc.class);
	 		finall = service.deleted(acc);
	 	}
	 	if(null!=updated){
	 		jsonArray = JSONArray.fromObject(updated);
	 		acc = (Acc[]) JSONArray.toArray(jsonArray,Acc.class);
	 		finall = service.updated(acc);
	 	}
	 	response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.write(finall);
		out.close();
 		return null;
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
	public Acc getAcc() {
		return acc;
	}
	public void setAcc(Acc acc) {
		this.acc = acc;
	}
}
