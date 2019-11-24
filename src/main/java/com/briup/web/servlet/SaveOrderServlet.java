package com.briup.web.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.briup.bean.Customer;
import com.briup.bean.Order;
import com.briup.bean.ShopCar;
import com.briup.service.impl.OrderServiceImpl;

@WebServlet("/SaveOrderServlet")
public class SaveOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		OrderServiceImpl orderServiceImpl = new OrderServiceImpl();
		
		HttpSession session = request.getSession();
		
		Customer customer=(Customer)session.getAttribute("customer");
		double total = Double.parseDouble( request.getParameter("total"));
		Timestamp orderdate = new java.sql.Timestamp(new java.util.Date().getTime());
		Order order = new Order();
			order.setTotal(total);
			order.setCustomer(customer);
			order.setOrderDate(orderdate);
		
		List<ShopCar> listShopCar = (List<ShopCar>) session.getAttribute("listShopCar");
		orderServiceImpl.saveOrder(order, listShopCar);
		
		request.getRequestDispatcher("/ShowOrdersServlet").forward(request, response);
	}
}
