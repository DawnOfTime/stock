package com.stock.supplier.service;

import java.util.List;

import com.stock.comm.PageBean_easyui;
import com.stock.pojo.Supplier;
import com.stock.supplier.dao.SupplierDao;

public class SupplierServcie {
	private SupplierDao dao = new SupplierDao();
	public List findSupplier(Supplier supplier, PageBean_easyui pageBean) {
		return dao.findSupplier(supplier,pageBean);
	}

	public String inserted(Supplier[] suppliers) {
		return dao.inserted(suppliers);
	}

	public String deleted(Supplier[] suppliers) {
		return dao.deleted(suppliers);
	}

	public String updated(Supplier[] suppliers) {
		return dao.updated(suppliers);
	}

}
