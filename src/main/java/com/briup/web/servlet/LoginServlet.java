package com.briup.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.briup.bean.Customer;
import com.briup.bean.ShopCar;
import com.briup.service.impl.CustomerServiceImpl;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	CustomerServiceImpl customerServiceImpl = new CustomerServiceImpl();
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		try {
			if ("".equals(username) && "".equals(password)) {
				throw new Exception("请输入用户名和密码");
			}else if ("".equals(username)&& !"".equals(password)) {
				throw new Exception("请输入用户名");
			}else if(!"".equals(username) && "".equals(password)){
				throw new Exception("请输入密码");
			}
			
			
			Customer customer = customerServiceImpl.findByUsername(username, password);

			HttpSession session = request.getSession();
			session.setAttribute("flag", "flag");
			session.setAttribute("customer", customer);
			request.getRequestDispatcher("index.jsp").forward(request, response);
			
			//创建一个购物车
			session.setAttribute("shopCar", new ShopCar());
			return;

		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}

	}
}
