package com.stock.supplier.dao;

import java.util.List;

import org.hibernate.Session;

import com.stock.comm.HibernateSessionFactory;
import com.stock.comm.PageBean_easyui;
import com.stock.pojo.Supplier;

public class SupplierDao {

	public List findSupplier(Supplier supplier, PageBean_easyui pageBean) {
		Session session = HibernateSessionFactory.getSession();
		long count = (long) session.createQuery("select count(*) from Supplier").uniqueResult();
		pageBean.setTotalrecord((int) count);
		
		List<Supplier> supplierList = session.createQuery("from Supplier order by num")
				.setFirstResult((pageBean.getPagecode()-1)*pageBean.getPagesize())
				.setMaxResults(pageBean.getPagesize())
				.list();
		return supplierList;
	}

	public String inserted(Supplier[] suppliers) {
		Session session = HibernateSessionFactory.getSession();
		Supplier supplier = null;
		for (int i = 0; i < suppliers.length; i++) {
			supplier = suppliers[i];
			session.save(supplier);
		}
		return "ok";
	}

	public String deleted(Supplier[] suppliers) {
		Session session = HibernateSessionFactory.getSession();
		Supplier supplier = null;
		for (int i = 0; i < suppliers.length; i++) {
			supplier = suppliers[i];
			session.delete(supplier);
		}
		return "ok";
	}

	public String updated(Supplier[] suppliers) {
		Session session = HibernateSessionFactory.getSession();
		Supplier supplier = null;
		for (int i = 0; i < suppliers.length; i++) {
			supplier = suppliers[i];
			session.update(supplier);
		}
		return "ok";
	}

}
