package com.stock.rule.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.stock.comm.HibernateSessionFactory;
import com.stock.comm.PageBean_easyui;
import com.stock.pojo.Acc;
import com.stock.pojo.Menu;
import com.stock.pojo.Rule;
import com.stock.pojo.Rule_Menu;

public class RuleDao {

	public List find(Rule rule, PageBean_easyui pageBean) {
		Session session = HibernateSessionFactory.getSession();
		long count = (long) session.createQuery("select count(*) from Rule").uniqueResult();
		pageBean.setTotalrecord((int) count);
		
		List<Rule> list = session.createQuery("from Rule order by rnum")
				.setFirstResult((pageBean.getPagecode()-1)*pageBean.getPagesize())
				.setMaxResults(pageBean.getPagesize())
				.list();
		return list;
	}

	public String inserted(Rule[] rules) {
		Session session = HibernateSessionFactory.getSession();
		Rule rule = null;
		for (int i = 0; i < rules.length; i++) {
			rule = rules[i];
			session.save(rule);
		}
		return "ok";
	}

	public String deleted(Rule[] rules) {
		Session session = HibernateSessionFactory.getSession();
		Rule rule = null;
		for (int i = 0; i < rules.length; i++) {
			rule = rules[i];
			session.delete(rule);
		}
		return "ok";
	}

	public String updated(Rule[] rules) {
		Session session = HibernateSessionFactory.getSession();
		Rule rule = null;
		for (int i = 0; i < rules.length; i++) {
			rule = rules[i];
			session.update(rule);
		}
		return "ok";
	}

	public String powerSet(String roleid, String menuIds) {
		Session session = HibernateSessionFactory.getSession();
		//先删除关系，再添加关系
		List<Rule_Menu> rmList = session.createQuery("from Rule_Menu where rid = '"+roleid+"'").list();
		for (Rule_Menu rule_Menu : rmList) {
			session.delete(rule_Menu);
		}
		String [] menuArr = menuIds.split(",");
		for (int i = 0; i < menuArr.length; i++) {
			Rule_Menu rule_menu = new Rule_Menu();
			rule_menu.setMnum(menuArr[i]);
			rule_menu.setRid(roleid);
			session.save(rule_menu);
		}
		
		return "ok";
	}

	public List menuTree(Rule rule) {
		Session session = HibernateSessionFactory.getSession();
		List<Menu> menuList = session.createSQLQuery(
				"SELECT menu.id,menu.num,menu.`name`,menu.father_num,menu.`level`,menu.menuurl,1 checked FROM menu WHERE num in(SELECT mnum FROM rule_menu WHERE rid = '"+rule.getId()+"') "
				+ "union all "
				+ "SELECT menu.id,menu.num,menu.`name`,menu.father_num,menu.`level`,menu.menuurl,0 checked FROM menu WHERE num not in(SELECT mnum FROM rule_menu WHERE rid = '"+rule.getId()+"');"
				).addEntity(Menu.class).list();
		return menuList;
	}
	@Test
	public void test(){
		Session session = HibernateSessionFactory.getSession();
		Transaction tx=session.beginTransaction();
		List<Menu> menuList = session.createQuery("from Menu order by num").list();
		System.out.println(menuList);
		tx.commit();
		session.close();
	}
}
