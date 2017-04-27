package com.stock.menu.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.junit.Test;

import com.stock.comm.HibernateSessionFactory;
import com.stock.comm.PageBean_easyui;
import com.stock.pojo.Menu;
import com.stock.pojo.Personnel;


public class MenuDao {
	HttpServletRequest request=ServletActionContext.getRequest();
	HttpSession httpSession = request.getSession();//获取session
	public List showMenu() {
		Session session = HibernateSessionFactory.getSession();
		Personnel personnel = (Personnel) httpSession.getAttribute("person");
		String hql = "SELECT DISTINCT * FROM "+
					"(SELECT menu.id,menu.num,menu.`name`,menu.father_num,menu.`level`,menu.menuurl , 1 checked FROM menu right JOIN "+
					"(SELECT * FROM rule_menu WHERE rid in(SELECT id FROM rule WHERE rnum = '"+personnel.getRnum()+"')) rm "+
					"on rm.mnum = menu.num "+
					"UNION ALL "+
					"SELECT menu.id,menu.num,menu.`name`,menu.father_num,menu.`level`,menu.menuurl , 1 checked FROM menu WHERE num IN( "+
					"SELECT distinct menu.father_num FROM menu right JOIN "+
					"(SELECT * FROM rule_menu WHERE rid in(SELECT id FROM rule WHERE rnum = '"+personnel.getRnum()+"')) rm "+
					"on rm.mnum = menu.num WHERE menu.`level` = '2' "+
					")) s ORDER BY s.num";
//		String hql = "SELECT id,num,name,father_num,level,menuurl,1 checked from menu";
		List<Menu> menuList = session.createSQLQuery(hql)
				.addEntity(Menu.class).list();
		return menuList;
	}

	public List findMenu(Menu menu, PageBean_easyui pageBean) {
		Session session = HibernateSessionFactory.getSession();
		long count = (long) session.createQuery("select count(*) from Menu").uniqueResult();
		pageBean.setTotalrecord((int) count);
		
		List<Menu> menuList = session.createSQLQuery("select id,father_num,level,menuurl,name,num,0 checked from menu order by num")
				.addEntity(Menu.class)
				.setFirstResult((pageBean.getPagecode()-1)*pageBean.getPagesize())
				.setMaxResults(pageBean.getPagesize())
				.list();
		return menuList;
	}

	public String inserted(Menu[] menus) {
		Session session = HibernateSessionFactory.getSession();
		Menu menu = null;
		for (int i = 0; i < menus.length; i++) {
			menu = menus[i];
			menu.setChecked(0);
			session.save(menu);
		}
		return "ok";
	}

	public String deleted(Menu[] menus) {
		Session session = HibernateSessionFactory.getSession();
		Menu menu = null;
		for (int i = 0; i < menus.length; i++) {
			menu = menus[i];
			session.delete(menu);
		}
		return "ok";
	}

	public String updated(Menu[] menus) {
		Session session = HibernateSessionFactory.getSession();
		Menu menu = null;
		for (int i = 0; i < menus.length; i++) {
			menu = menus[i];
			session.update(menu);
		}
		return "ok";
	}
	
}
