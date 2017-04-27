package com.stock.goodsmanagement.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.junit.Test;

import com.stock.goodsmanagement.service.GoodsmanagementService;
import com.stock.pojo.Goods;

public class GoodsmanagementAction {
	HttpServletRequest request=ServletActionContext.getRequest();
	HttpServletResponse response=ServletActionContext.getResponse();
	
	GoodsmanagementService service = new GoodsmanagementService();
	
	private String fun;
	private Goods goods;
	public String tree() throws IOException{
		String treejsonstr = service.tree();
		System.out.println(treejsonstr);
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.write(treejsonstr);
		out.close();
		return null;
	}
	//保存修改和添加的内容(treeadd添加、treeupdate修改)
	public String save(){
		if(fun.equals("treeadd")){
			service.addtree(goods);
		}
		if(fun.equals("treeupdate")){
			service.updatetree(goods);
		}
		return null;
	}
	public String removetree(){
		service.removetree(goods);
		return null;
	}
	public String getFun() {
		return fun;
	}
	public void setFun(String fun) {
		this.fun = fun;
	}
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
}
