package com.briup.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.briup.bean.Book;
import com.briup.dao.BookDao;
import com.briup.service.IBookService;

public class BookServiceImpl implements IBookService{

	@Override
	public List<Book> findAllBooks() {
		try {
			InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
			SqlSessionFactory build = new SqlSessionFactoryBuilder().build(in);
			SqlSession session = build.openSession();
			BookDao dao = session.getMapper(BookDao.class);
			
			List<Book> books = dao.findAllBooks();
			return books;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
		
	}

	@Override
	public Book findBookById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
