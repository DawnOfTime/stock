package com.stock.goodsapply.service;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.junit.Test;

import com.stock.comm.PageBean_easyui;
import com.stock.goodsapply.dao.GoodsapplyDao;
import com.stock.pojo.Goods;
import com.stock.pojo.GoodsApply;
import com.stock.pojo.Personnel;

public class GoodsapplyServcie {
	private GoodsapplyDao dao = new GoodsapplyDao();
	HttpServletRequest request=ServletActionContext.getRequest();
	HttpSession session = request.getSession();
	Personnel personnel = (Personnel) session.getAttribute("person");
	public List findGoodsapply(GoodsApply goodsApply, PageBean_easyui pageBean) {
		// TODO Auto-generated method stub
		if(null!=goodsApply){
			goodsApply.setApplicant(personnel.getId());
		}else{
			GoodsApply gsApply = new GoodsApply();
			gsApply.setApplicant(personnel.getId());
			goodsApply = gsApply;
		}
		
		return dao.findGoodsapply(goodsApply,pageBean);
	}

	public List getgoods(Goods goods) {
		// TODO Auto-generated method stub
		return dao.getgoods(goods);
	}

	public void save(String goodsApplyArray) throws Exception {
		List<GoodsApply> list = new ArrayList<GoodsApply>();
		String [] rowobj = goodsApplyArray.split("\\|");
		String [] attrs = {"goodscode","model","unit","number"};
		Class clazz = GoodsApply.class;
		Method [] ms = clazz.getMethods();
		for (int i = 0; i < rowobj.length; i++) {
			String [] colobj = rowobj[i].split(",");
			Object obj = clazz.newInstance();
			for (int j = 0; j < colobj.length; j++) {
				for (Method m : ms) {
					if(m.getName().toUpperCase().equals(("set"+attrs[j]).toUpperCase())){
						m.invoke(obj, colobj[j]);
					}
				}
			}
			GoodsApply goodsApply = (GoodsApply)obj;
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			goodsApply.setApplicant(personnel.getId());
			goodsApply.setApplytime(df.format(new Date()));
			goodsApply.setState("1");//待审核
			list.add(goodsApply);
		}
		dao.save(list);
	}
	@Test
	public void test(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		System.out.println(df.format(new Date()));
	}
}
