package com.stock.stockgoods.service;

import java.util.List;

import com.stock.stockgoods.dao.StockgoodsDaoImpl;
import com.stock.stockgoods.inter.StockgoodsDaoInter;
import com.stock.stockgoods.inter.StockgoodsServiceInter;

public class StockgoodsServiceImpl implements StockgoodsServiceInter{
	private StockgoodsDaoInter daoInter = new StockgoodsDaoImpl();
	@Override
	public List find() {
		System.out.println("service");
		return daoInter.find();
	}
	
}
