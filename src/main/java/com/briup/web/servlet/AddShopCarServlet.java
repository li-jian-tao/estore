package com.briup.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
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

@WebServlet("/AddShopCarServlet")
public class AddShopCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ShopCarServiceImpl shopCarServiceImpl = new ShopCarServiceImpl();
		
		HttpSession session = request.getSession();
		
		Book book = (Book) session.getAttribute("book"); //获得当前购买的书本
		Customer customer = (Customer) session.getAttribute("customer");//获得当前用户
		ShopCar shopCar = (ShopCar) session.getAttribute("shopCar");//获得购物车
		int num = Integer.parseInt(request.getParameter("NUM")); //获得购买的数量

		List<ShopCar> shops = shopCarServiceImpl.findByCustomer(customer);
		for (ShopCar s : shops) {
			if (s.getBook().getId()==book.getId()) {
				Integer num2 = s.getNum();
				num+=num2;
				shopCar.setBook(book);
				shopCar.setCustomer(customer);
				shopCar.setNum(num);
				shopCarServiceImpl.updateShopCar(shopCar);
				request.getRequestDispatcher("index.jsp").forward(request, response);
				return;
			}
		}
		
		shopCar.setBook(book);
		shopCar.setCustomer(customer);
		shopCar.setNum(num);
		

		
		shopCarServiceImpl.saveShopCar(shopCar);
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
	}
}
