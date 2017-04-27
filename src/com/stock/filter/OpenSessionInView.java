package com.stock.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.stock.comm.HibernateSessionFactory;

public class OpenSessionInView implements Filter{
	public void destroy(){}
	
	@Override
	public void init(FilterConfig arg0)throws ServletException{
		
	}
	
	@Override
	public void doFilter(ServletRequest request,ServletResponse response,
			FilterChain chain)throws IOException,ServletException{
		Session session=null;
		Transaction tx=null;
		try{
			session=HibernateSessionFactory.getSession();
			tx=session.beginTransaction();
			
			chain.doFilter(request, response);
              //过滤器允许请求继续执行,
			//web工程执行顺序如下(在如下过程中不处理提交和关闭session的操作):		
              //servlet/action-->service--dao--getSession--servlet/action-->jsp
			tx.commit();
			System.out.println("提交事务");
		}catch(Exception e){
			if(tx!=null)
				tx.rollback();
		}finally{
			HibernateSessionFactory.closeSession();
		}
		
	}

}