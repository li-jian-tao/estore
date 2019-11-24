package com.briup.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.briup.bean.Category;
import com.briup.service.impl.CategotyServiceImpl;

@WebServlet("/FindAllCategoryServlet")
public class FindAllCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CategotyServiceImpl categotyServiceImpl = new CategotyServiceImpl();
		List<Category> list = categotyServiceImpl.findAllCategories();
		HttpSession session = request.getSession();
		session.setAttribute("list", list);
	}
}
