package com.briup.service;

import com.briup.bean.Customer;

public interface ICustomerService {

	Customer findByUsername(String username, String password) throws Exception;
	
	void saveCustomer(Customer customer) throws Exception;
	Customer regFind(String username) throws Exception;
	
	void updateCustomer(Customer customer) throws Exception;
}
