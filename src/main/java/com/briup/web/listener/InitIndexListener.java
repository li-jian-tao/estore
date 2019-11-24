package com.briup.web.listener;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.briup.bean.Book;
import com.briup.bean.Category;
import com.briup.service.impl.BookServiceImpl;
import com.briup.service.impl.CategotyServiceImpl;

/**
 * Application Lifecycle Listener implementation class InitIndexListener
 *
 */
@WebListener
public class InitIndexListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		CategotyServiceImpl categotyServiceImpl = new CategotyServiceImpl();
		try {
			List<Category> categories = categotyServiceImpl.findAllCategories();
			
			BookServiceImpl bookServiceImpl = new BookServiceImpl();
			List<Book> books = bookServiceImpl.findAllBooks();
			
			ServletContext servletContext = sce.getServletContext();
			servletContext.setAttribute("categories", categories);
			servletContext.setAttribute("books", books);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	

}
