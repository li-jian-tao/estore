package com.briup.service.impl;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.briup.bean.Customer;
import com.briup.dao.CustomerDao;
import com.briup.service.ICustomerService;

public class CustomerServiceImpl implements ICustomerService {

	/**
	 * 登陆查询
	 */
	@Override
	public Customer findByUsername(String username, String password) throws Exception {
		InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory build = new SqlSessionFactoryBuilder().build(in);
		SqlSession session = build.openSession();
		CustomerDao dao = session.getMapper(CustomerDao.class);
		Customer customer = dao.findByUsername(username);
		if (customer != null) {
			if (password.equals(customer.getPassword())) {
				return customer;
			} else {
				
				throw new Exception("密码错误");
			}
		} else {
			throw new Exception("用户名不存在");
		}
	}

	@Override
	public void saveCustomer(Customer customer) throws Exception {
		InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory build = new SqlSessionFactoryBuilder().build(in);
		SqlSession session = build.openSession();
		CustomerDao dao = session.getMapper(CustomerDao.class);
		dao.saveCustomer(customer);
		session.commit();
		session.close();

	}
	
	@Override
	public Customer regFind(String username) throws Exception {
		InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory build = new SqlSessionFactoryBuilder().build(in);
		SqlSession session = build.openSession();
		CustomerDao dao = session.getMapper(CustomerDao.class);
		
		Customer customer = dao.regFind(username);
		if (customer!=null) {
			throw new Exception("用户名已注册");
		}
			return null;
		
	
	}


	@Override
	public void updateCustomer(Customer customer) throws Exception{
		InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory build = new SqlSessionFactoryBuilder().build(in);
		SqlSession session = build.openSession();
		CustomerDao dao = session.getMapper(CustomerDao.class);
		dao.updateCustomer(customer);
		session.commit();
		session.close();
	}

	
}
