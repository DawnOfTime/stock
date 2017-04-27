package com.stock.acc.dao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.junit.Test;

import com.stock.comm.HibernateSessionFactory;
import com.stock.comm.PageBean_easyui;
import com.stock.pojo.Acc;

public class AccDao {
	Date d = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
	public List find(Acc acc, PageBean_easyui pageBean) {
		Session session = HibernateSessionFactory.getSession();
		long count = (long) session.createQuery("select count(*) from Acc").uniqueResult();
		pageBean.setTotalrecord((int) count);
		
		List<Acc> list = session.createQuery("from Acc")
				.setFirstResult((pageBean.getPagecode()-1)*pageBean.getPagesize())
				.setMaxResults(pageBean.getPagesize())
				.list();
		return list;
	}

	public String inserted(Acc[] accs) {
		Session session = HibernateSessionFactory.getSession();
		Acc acc = null;
		for (int i = 0; i < accs.length; i++) {
			acc = accs[i];
			acc.setCreattime(sdf.format(d));
			acc.setUpdatetime(sdf.format(d));
			session.save(acc);
		}
		return "ok";
	}

	public String deleted(Acc[] accs) {
		Session session = HibernateSessionFactory.getSession();
		Acc acc = null;
		for (int i = 0; i < accs.length; i++) {
			acc = accs[i];
			session.delete(acc);
		}
		return "ok";
	}

	public String updated(Acc[] accs) {
		Session session = HibernateSessionFactory.getSession();
		Acc acc = null;
		for (int i = 0; i < accs.length; i++) {
			acc = accs[i];
			acc.setUpdatetime(sdf.format(d));
			session.update(acc);
		}
		return "ok";
	}
}
