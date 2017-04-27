package com.stock.comm;

import java.util.List;

public class PageBean_easyui {
	//总记录数
	private int totalrecord;
	//总页数
	private int totalpage;
	//没页记录数
	private int pagesize;
	//当前页码
	private int pagecode;
	//当前页的数据
	private List beanList;
	
	private String url;
	
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public int getTotalrecord() {
		return totalrecord;
	}
	public void setTotalrecord(int totalrecord) {
		this.totalrecord = totalrecord;
	}
	public int getTotalpage() {
		totalpage = (totalrecord % pagesize == 0) ? (totalrecord / pagesize) : ((totalrecord / pagesize) + 1);
		return totalpage;
	}
	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
	}
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	public int getPagecode() {
		return pagecode;
	}
	public void setPagecode(int pagecode) {
		this.pagecode = pagecode;
	}
	public List getBeanList() {
		return beanList;
	}
	public void setBeanList(List beanList) {
		this.beanList = beanList;
	}	
}
