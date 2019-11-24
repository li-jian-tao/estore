package com.briup.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.briup.bean.Customer;
import com.briup.bean.Order;
import com.briup.bean.OrderLine;
import com.briup.bean.ShopCar;
import com.briup.dao.OrderDao;
import com.briup.dao.OrderLineDao;
import com.briup.dao.ShopCarDao;
import com.briup.service.IOrderService;

public class OrderServiceImpl implements IOrderService{
	InputStream in;
	SqlSessionFactory build;
	SqlSession session;
	OrderDao daoOrder;
	OrderLineDao daoOrderLine;
	ShopCarDao shopCar;
	{
		try {
			 in = Resources.getResourceAsStream("mybatis-config.xml");
			 build = new SqlSessionFactoryBuilder().build(in);
			 session = build.openSession();
			 daoOrder = session.getMapper(OrderDao.class);
			 daoOrderLine = session.getMapper(OrderLineDao.class);
			 shopCar=session.getMapper(ShopCarDao.class);
		} catch (IOException e) {
			e.printStackTrace();
		
		}
	}
	@Override
	public void saveOrder(Order order, List<ShopCar> cars) {
		daoOrder.saveOrder(order);
		session.commit();
		List<Order> orders = daoOrder.findAllOrders();
		for (Order o : orders) {
			if (o.getCustomer().getId().equals(order.getCustomer().getId())) {
				order.setId(o.getId());
			}
		}
		for (ShopCar shopCar : cars) {
			daoOrderLine.saveOrderLine(new OrderLine(shopCar.getNum(),shopCar.getBook(),order));
		}
		shopCar.clearShopCarByCustomer(order.getCustomer()); 
		session.commit();
		
	}                                                                                  

	@Override
	public List<Order> findByCustomer(Customer customer) {
		List<Order> orders = daoOrder.findByCustomer(customer);
		return orders;
	}

}
