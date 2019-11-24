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

@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			response.setContentType("test/html;charset=utf-8");
			CustomerServiceImpl customerServiceImpl = new CustomerServiceImpl();
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String zip = request.getParameter("zip");
			String address = request.getParameter("address");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			Customer customer = new Customer(username, password, zip, address, phone, email);
			customerServiceImpl.updateCustomer(customer);
			HttpSession session = request.getSession();
			session.setAttribute("customer", customer);
			request.getRequestDispatcher("userinfo.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();

		}
	}
}
