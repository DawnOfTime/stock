package com.stock.stockgoods.dao;

import java.util.ArrayList;
import java.util.List;

import com.stock.stockgoods.inter.StockgoodsDaoInter;

public class StockgoodsDaoImpl implements StockgoodsDaoInter{

	@Override
	public List find() {
		System.out.println("dao");
		List list = new ArrayList();
		list.add("a");
		return list;
	}

}
