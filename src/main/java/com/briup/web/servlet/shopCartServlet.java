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
import com.briup.bean.ShopCar;
import com.briup.service.impl.ShopCarServiceImpl;

@WebServlet("/shopCartServlet")
public class shopCartServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int num=0;
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		ShopCarServiceImpl shopCarServiceImpl = new ShopCarServiceImpl();
		List<ShopCar> listShopCar = shopCarServiceImpl.findByCustomer(customer);
		session.setAttribute("listShopCar", listShopCar);
		for (ShopCar sc : listShopCar) {
			num+=sc.getNum();
		}
		session.setAttribute("num", num);
		request.getRequestDispatcher("shopCart.jsp").forward(request, response);
		
	}
}
