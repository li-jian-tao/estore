package com.briup.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.briup.bean.Book;

@WebServlet("/viewBookServlet")
public class ViewBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sid = request.getParameter("id");
		int id = Integer.parseInt(sid);
		ServletContext servletContext = request.getServletContext();
		HttpSession session = request.getSession();
		
		List<Book> books = (List<Book>) servletContext.getAttribute("books");
		Book book = books.get(id-1);
		
		
		session.setAttribute("book", book);
		request.getRequestDispatcher("viewBook.jsp").forward(request, response);
	}
}
