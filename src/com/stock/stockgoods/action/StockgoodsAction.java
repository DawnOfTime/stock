package com.stock.stockgoods.action;

import java.util.List;

import org.junit.Test;

import com.stock.stockgoods.inter.StockgoodsServiceInter;
import com.stock.stockgoods.service.StockgoodsServiceImpl;

public class StockgoodsAction {
	private StockgoodsServiceInter serviceInter;
	public List find(){
//		StockgoodsServiceImpl stockgoodsServiceImpl = new StockgoodsServiceImpl();
		serviceInter = new StockgoodsServiceImpl();
		List list = serviceInter.find();
		return list;
	}
	@Test
	public void test(){
		List list = find();
		for (Object object : list) {
			String str = (String) object;
			System.out.println(str);
		}
	}
	public static void main(String[] args) {
		StockgoodsAction act = new StockgoodsAction();
		List list = act.find();
		System.out.println(list);
	}
}
