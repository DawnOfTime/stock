package com.stock.personnel.dao;

import java.util.List;

import org.hibernate.Session;

import com.stock.comm.HibernateSessionFactory;
import com.stock.comm.PageBean_easyui;
import com.stock.pojo.Personnel;

public class PersonnelDao {

	public List findPersonnel(Personnel personnel, PageBean_easyui pageBean) {
		Session session = HibernateSessionFactory.getSession();
		long count = (long) session.createQuery("select count(*) from Personnel").uniqueResult();
		pageBean.setTotalrecord((int) count);
		String hql = "from Personnel ";
		if(null!=personnel){
			hql+="where id = '"+personnel.getId()+"'";
		}
		List<Personnel> personnelList = session.createQuery(hql)
				.setFirstResult((pageBean.getPagecode()-1)*pageBean.getPagesize())
				.setMaxResults(pageBean.getPagesize())
				.list();
		return personnelList;
	}

	public String inserted(Personnel[] personnels) {
		Session session = HibernateSessionFactory.getSession();
		Personnel personnel = null;
		for (int i = 0; i < personnels.length; i++) {
			personnel = personnels[i];
			personnel.setPassword("123456");//密码默认为123456
			session.save(personnel);
		}
		return "ok";
	}

	public String deleted(Personnel[] personnels) {
		Session session = HibernateSessionFactory.getSession();
		Personnel personnel = null;
		for (int i = 0; i < personnels.length; i++) {
			personnel = personnels[i];
			session.delete(personnel);
		}
		return "ok";
	}

	public String updated(Personnel[] personnels) {
		Session session = HibernateSessionFactory.getSession();
		Personnel personnel = null;
		for (int i = 0; i < personnels.length; i++) {
			personnel = personnels[i];
			session.update(personnel);
		}
		return "ok";
	}

	public List ruletype() {
		Session session = HibernateSessionFactory.getSession();
		List list = session.createQuery("from Rule").list();
		return list;
	}

	public List department() {
		Session session = HibernateSessionFactory.getSession();
		List list = session.createQuery("from Department").list();
		return list;
	}

	public void resetpassword(Personnel personnel) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		Personnel person = (Personnel) session.get(Personnel.class, personnel.getId());
		System.out.println("before  "+person);
		person.setPassword("123456");
		System.out.println("after   "+person);
		session.update(person);
	}

}
