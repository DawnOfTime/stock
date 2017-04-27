package com.stock.department.dao;

import java.util.List;

import org.hibernate.Session;

import com.stock.comm.HibernateSessionFactory;
import com.stock.comm.PageBean_easyui;
import com.stock.pojo.Department;

public class DepartmentDao {

	public List findDepartment(Department department, PageBean_easyui pageBean) {
		Session session = HibernateSessionFactory.getSession();
		long count = (long) session.createQuery("select count(*) from Department").uniqueResult();
		pageBean.setTotalrecord((int) count);
		
		List<Department> departmentList = session.createQuery("from Department order by num")
				.setFirstResult((pageBean.getPagecode()-1)*pageBean.getPagesize())
				.setMaxResults(pageBean.getPagesize())
				.list();
		return departmentList;
	}

	public String inserted(Department[] departments) {
		Session session = HibernateSessionFactory.getSession();
		Department department = null;
		for (int i = 0; i < departments.length; i++) {
			department = departments[i];
			session.save(department);
		}
		return "ok";
	}

	public String deleted(Department[] departments) {
		Session session = HibernateSessionFactory.getSession();
		Department department = null;
		for (int i = 0; i < departments.length; i++) {
			department = departments[i];
			session.delete(department);
		}
		return "ok";
	}

	public String updated(Department[] departments) {
		Session session = HibernateSessionFactory.getSession();
		Department department = null;
		for (int i = 0; i < departments.length; i++) {
			department = departments[i];
			session.update(department);
		}
		return "ok";
	}

}
