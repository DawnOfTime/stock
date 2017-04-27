package com.stock.depot.service;

import java.util.List;

import com.stock.comm.PageBean_easyui;
import com.stock.depot.dao.DepotDao;
import com.stock.pojo.Depot;

public class DepotService {
	private DepotDao dao = new DepotDao();
	public List findSupplier(Depot depot, PageBean_easyui pageBean) {
		return dao.findSupplier(depot,pageBean);
	}

	public String inserted(Depot[] depots) {
		return dao.inserted(depots);
	}

	public String deleted(Depot[] depots) {
		return dao.deleted(depots);
	}

	public String updated(Depot[] depots) {
		return dao.updated(depots);
	}

}
