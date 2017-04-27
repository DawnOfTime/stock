package com.stock.login.dao;

import java.util.List;

import org.hibernate.Session;

import com.stock.comm.HibernateSessionFactory;
import com.stock.pojo.Personnel;

public class LoginDao {

	public Personnel login(Personnel personnel) {
		Session session=HibernateSessionFactory.getSession();
		List<Personnel> personlist = session.createQuery("from Personnel where id_card_num = '"+personnel.getId_card_num()+"'").list(); 
		Personnel personif = null;
		if(personlist.size()>0){
			personif = personlist.get(0);
		}
		return personif;
	}

}
