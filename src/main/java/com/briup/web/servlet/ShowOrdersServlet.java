package com.briup.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.briup.bean.Customer;
import com.briup.bean.Order;
import com.briup.bean.OrderLine;
import com.briup.service.impl.OrderServiceImpl;

@WebServlet("/ShowOrdersServlet")
public class ShowOrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int booknum=0;
		Double totalnum = 0.0;
		OrderServiceImpl orderServiceImpl = new OrderServiceImpl();
		HttpSession session = request.getSession();
		Customer customer=(Customer) session.getAttribute("customer");
		
		List<Order> orders = orderServiceImpl.findByCustomer(customer);
		for (Order order : orders) {
			totalnum+=order.getTotal();
			List<OrderLine> orderLines = order.getLines();
			for (OrderLine orderline : orderLines) {
				booknum+=orderline.getNum();
			}
		}
		session.setAttribute("totalnum", totalnum);
		session.setAttribute("booknum", booknum);
		session.setAttribute("orders", orders);
		request.getRequestDispatcher("confirm.jsp").forward(request, response);
	}
}
