package com.briup.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.briup.bean.Customer;
import com.briup.service.impl.CustomerServiceImpl;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			
			CustomerServiceImpl customerServiceImpl = new CustomerServiceImpl();
			
			String username = request.getParameter("username");
			
			Customer cus= customerServiceImpl.regFind(username);
			
			String password = request.getParameter("password");
			String zip = request.getParameter("zip");
			String address = request.getParameter("address");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			Customer customer =	new Customer(username, password, zip, address, phone, email);

			customerServiceImpl.saveCustomer(customer);
		
				
				
		} catch (Exception e) {
			request.setAttribute("reg", e.getMessage());
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}
		
		
		
		
	}
}
