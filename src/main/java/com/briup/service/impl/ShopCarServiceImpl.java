package com.briup.service.impl;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.briup.bean.Customer;
import com.briup.bean.ShopCar;
import com.briup.dao.ShopCarDao;
import com.briup.service.IShopCarService;

public class ShopCarServiceImpl implements IShopCarService{
	static ShopCarDao dao;
	static InputStream in;
	static SqlSessionFactory build;
	static SqlSession session;
	static{
		try {
			 in = Resources.getResourceAsStream("mybatis-config.xml");
			 build = new SqlSessionFactoryBuilder().build(in);
			 session = build.openSession();
			 dao = session.getMapper(ShopCarDao.class);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<ShopCar> findByCustomer(Customer customer) {	
			List<ShopCar> shopCars = dao.findByCustomer(customer);
			return shopCars;

	}

	@Override
	public void saveShopCar(ShopCar shopCar) {
		
			dao.saveShopCar(shopCar);
			session.commit();
			
			
	}

	
	@Override
	public void updateShopCar(ShopCar shopCar) {
		dao.updateShopCar(shopCar);
		session.commit();
		
	}
	
	@Override
	public void saveShopCar(List<ShopCar> cars, Integer bookId, Customer customer) {
		// TODO Auto-generated method stub
		
	}

	

}
