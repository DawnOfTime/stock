package com.stock.depot.dao;

import java.util.List;

import org.hibernate.Session;

import com.stock.comm.HibernateSessionFactory;
import com.stock.comm.PageBean_easyui;
import com.stock.pojo.Depot;

public class DepotDao {

	public List findSupplier(Depot depot, PageBean_easyui pageBean) {
		Session session = HibernateSessionFactory.getSession();
		long count = (long) session.createQuery("select count(*) from Depot").uniqueResult();
		pageBean.setTotalrecord((int) count);
		
		List<Depot> depotList = session.createQuery("from Depot order by num")
				.setFirstResult((pageBean.getPagecode()-1)*pageBean.getPagesize())
				.setMaxResults(pageBean.getPagesize())
				.list();
		return depotList;
	}

	public String inserted(Depot[] depots) {
		Session session = HibernateSessionFactory.getSession();
		Depot depot = null;
		for (int i = 0; i < depots.length; i++) {
			depot = depots[i];
			session.save(depot);
		}
		return "ok";
	}

	public String deleted(Depot[] depots) {
		Session session = HibernateSessionFactory.getSession();
		Depot depot = null;
		for (int i = 0; i < depots.length; i++) {
			depot = depots[i];
			session.delete(depot);
		}
		return "ok";
	}

	public String updated(Depot[] depots) {
		Session session = HibernateSessionFactory.getSession();
		Depot depot = null;
		for (int i = 0; i < depots.length; i++) {
			depot = depots[i];
			session.update(depot);
		}
		return "ok";
	}

}
