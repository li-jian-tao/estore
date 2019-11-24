package com.briup.dao;

import com.briup.bean.Customer;

public interface CustomerDao {
	
	Customer findByUsername(String username); //登陆
	
	void saveCustomer(Customer customer); //注册
	Customer regFind(String username);   //注册查询
	
	void updateCustomer(Customer customer); //修改信息

}
