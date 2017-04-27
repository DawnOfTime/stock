package com.stock.acc.service;

import java.util.List;

import com.stock.acc.dao.AccDao;
import com.stock.comm.PageBean_easyui;
import com.stock.pojo.Acc;

public class AccService {
	private AccDao dao = new AccDao();
	public List find(Acc acc, PageBean_easyui pageBean) {
		return dao.find(acc,pageBean);
	}
	public String inserted(Acc[] acc) {
		return dao.inserted(acc);
	}
	public String deleted(Acc[] acc) {
		return dao.deleted(acc);
	}
	public String updated(Acc[] acc) {
		return dao.updated(acc);
	}

}
