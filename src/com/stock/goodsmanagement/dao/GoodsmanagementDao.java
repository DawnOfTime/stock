package com.stock.goodsmanagement.dao;

import java.util.List;

import org.hibernate.Session;

import com.stock.comm.HibernateSessionFactory;
import com.stock.pojo.Goods;

public class GoodsmanagementDao {

	public List tree() {
		Session session = HibernateSessionFactory.getSession();
		List<Goods> goodsapply = session.createQuery("from Goods").list();
		return goodsapply;
	}

	public void addtree(Goods goods) {
		Session session = HibernateSessionFactory.getSession();
		session.save(goods);
	}

	public void updatetree(Goods goods) {
		Session session = HibernateSessionFactory.getSession();
		List list = session.createQuery("from Goods where num = '"+goods.getNum()+"'").list();
		Goods good = (Goods)list.get(0);
		good.setName(goods.getName());
		session.update(goods);
	}

	public void removetree(Goods goods) {
		Session session = HibernateSessionFactory.getSession();
		List list = session.createQuery("from Goods where num = '"+goods.getNum()+"'").list();
		Goods good = (Goods)list.get(0);
		session.delete(good);
	}

}
