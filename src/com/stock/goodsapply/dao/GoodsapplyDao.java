package com.stock.goodsapply.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.stock.comm.HibernateSessionFactory;
import com.stock.comm.PageBean_easyui;
import com.stock.pojo.Goods;
import com.stock.pojo.GoodsApply;
import com.stock.pojo.GoodsApplyOut;

public class GoodsapplyDao {

	public List findGoodsapply(GoodsApply goodsApply, PageBean_easyui pageBean) {
		Session session = HibernateSessionFactory.getSession();
		long count = (long) session.createQuery("select count(*) from GoodsApply where applicant = '"+goodsApply.getApplicant()+"'").uniqueResult();
		pageBean.setTotalrecord((int) count);
		String hql = "select apply.id,apply.goodscode,apply.model,apply.unit,apply.number,per.`name` applicant,apply.applytime,"
				+ "apply.approver,apply.approvaltime,apply.state,apply.spec,apply.`names`,apply.goodtype from "
				+ "(select gsapp.*,goods.spec,goods.`names`,goods.goodtype from goodsapply gsapp LEFT JOIN "
				+ "(select a.num,a.name as spec,b.name as names,c.name as goodtype from goods a "
				+ "LEFT JOIN goods b on a.father_num = b.num "
				+ "LEFT JOIN goods c on b.father_num = c.num) goods on gsapp.goodscode = goods.num) apply "
				+ "LEFT JOIN personnel per on apply.applicant = per.id "
				+ "where per.id = '"+goodsApply.getApplicant()+"'";
		/*List goodsapplylist = session.createQuery("from GoodsApply")
				.setFirstResult((pageBean.getPagecode()-1)*pageBean.getPagesize())
				.setMaxResults(pageBean.getPagesize())
				.list();*/
		List goodsapplylist = session.createSQLQuery(hql)
				.addEntity(GoodsApplyOut.class)
				.setFirstResult((pageBean.getPagecode()-1)*pageBean.getPagesize())
				.setMaxResults(pageBean.getPagesize())
				.list();
		return goodsapplylist;
	}

	public List getgoods(Goods goods) {
		Session session = HibernateSessionFactory.getSession();
		String hql = "from Goods where 1=1 ";
		if(null!=goods){
			if(null!=goods.getLevel() && !goods.getLevel().equals("")){
				hql+=" and level = '"+goods.getLevel()+"'";
			}
			if(null!=goods.getNum() && !goods.getNum().equals("")){
				hql+=" and father_num = '"+goods.getNum()+"'";
			}
		}
		List list = session.createQuery(hql).list();
		return list;
	}

	public void save(List<GoodsApply> list) {
		Session session = HibernateSessionFactory.getSession();
		for (GoodsApply goodsApply : list) {
			System.out.println("添加：         "+goodsApply);
			session.save(goodsApply);
//			session.createSQLQuery("insert into GoodsApply "
//					+ "values (uuid(), '"+goodsApply.getGoodscode()+"', '"+goodsApply.getModel()+"', '"+goodsApply.getUnit()+"', '"+goodsApply.getNumber()+"', '"
//					+ goodsApply.getApplicant()+"', '"+goodsApply.getApplytime()+"', '"+goodsApply.getApprover()+"', '"+goodsApply.getApprovaltime()+"', '"+goodsApply.getState()+"')")
//					.executeUpdate();
		}
	}
	@Test
	public void test(){
//		01030201,aa,支,3
		Session session = HibernateSessionFactory.getSession();
		Transaction tx=session.beginTransaction();
		GoodsApply goodsApply = new GoodsApply();
		goodsApply.setGoodscode("01030201");
		goodsApply.setModel("aa");
		goodsApply.setUnit("支");
		goodsApply.setNumber("3");
		goodsApply.setApplytime("2017-03-03 11:51:57");
		goodsApply.setState("1");
		System.out.println(goodsApply);
		session.save(goodsApply);
//		session.createSQLQuery("insert into GoodsApply "
//				+ "values (uuid(), '"+goodsApply.getGoodscode()+"', '"+goodsApply.getModel()+"', '"+goodsApply.getUnit()+"', '"+goodsApply.getNumber()+"', '"
//				+ goodsApply.getApplicant()+"', '"+goodsApply.getApplytime()+"', '"+goodsApply.getApprover()+"', '"+goodsApply.getApprovaltime()+"', '"+goodsApply.getState()+"')")
//				.executeUpdate();
		System.out.println(goodsApply);
		
		
//		String hql = "select apply.id,apply.goodscode,apply.model,apply.unit,apply.number,per.`name` applicant,apply.applytime,"
//				+ "apply.approver,apply.approvaltime,apply.state,apply.spec,apply.`names`,apply.goodtype from "
//				+ "(select gsapp.*,goods.spec,goods.`names`,goods.goodtype from goodsapply gsapp LEFT JOIN "
//				+ "(select a.num,a.name as spec,b.name as names,c.name as goodtype from goods a "
//				+ "LEFT JOIN goods b on a.father_num = b.num "
//				+ "LEFT JOIN goods c on b.father_num = c.num) goods on gsapp.goodscode = goods.num) apply "
//				+ "LEFT JOIN personnel per on apply.applicant = per.id "
//				+ "where per.id = '1'";
//		List goodsapplylist = session.createSQLQuery(hql)
//				.addEntity(GoodsApply.class)
//				.list();
//		System.out.println(goodsapplylist);
		
		tx.commit();
	}
}
