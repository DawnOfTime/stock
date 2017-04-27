package com.stock.goodsapply.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.stock.comm.HttpUtil;
import com.stock.comm.PageBean_easyui;
import com.stock.goodsapply.service.GoodsapplyServcie;
import com.stock.pojo.Goods;
import com.stock.pojo.GoodsApply;

public class GoodsapplyAction {
	HttpServletRequest request=ServletActionContext.getRequest();
	HttpServletResponse response=ServletActionContext.getResponse();
	private GoodsapplyServcie service = new GoodsapplyServcie();
	private GoodsApply goodsApply;
	private String goodsApplyArray;
	private Goods goods;
	private String page;
	private String rows;
	private String deleted;
 	private String inserted; 
 	private String updated;
	public String find() throws IOException{
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
		
		List list = service.findGoodsapply(goodsApply,pageBean);
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
	public String getgoods() throws IOException{
		System.out.println("goods======="+goods);
		List list = service.getgoods(goods);
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.write(JSONArray.fromObject(list)+"");
		out.close();
		return null;
	}
	//提交申请单
	public String save() throws Exception{
		service.save(goodsApplyArray);
		return null;
	}
	public GoodsApply getGoodsApply() {
		return goodsApply;
	}
	public void setGoodsApply(GoodsApply goodsApply) {
		this.goodsApply = goodsApply;
	}
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
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
	public String getGoodsApplyArray() {
		return goodsApplyArray;
	}
	public void setGoodsApplyArray(String goodsApplyArray) {
		this.goodsApplyArray = goodsApplyArray;
	}
}
