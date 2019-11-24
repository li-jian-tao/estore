package com.briup.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.briup.bean.Book;
import com.briup.bean.Customer;
import com.briup.bean.ShopCar;
import com.briup.service.impl.ShopCarServiceImpl;

@WebServlet("/UpdateShopCartServlet")
public class UpdateShopCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		int id = Integer.parseInt(request.getParameter("id"));
		Book book = new Book(id);
		int num = Integer.parseInt(request.getParameter("num"));
		ShopCar shopCar = new ShopCar(customer,book,num);
		
		ShopCarServiceImpl shopCarServiceImpl = new ShopCarServiceImpl();
		shopCarServiceImpl.updateShopCar(shopCar);
		
		request.getRequestDispatcher("/shopCartServlet").forward(request, response);
	}
}
