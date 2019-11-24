package com.briup.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.briup.bean.Category;
import com.briup.dao.CategoryDao;
import com.briup.service.ICategotyService;

public class CategotyServiceImpl implements ICategotyService{

	@Override
	public List<Category> findAllCategories() throws IOException {
		InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory build = new SqlSessionFactoryBuilder().build(in);
		SqlSession session = build.openSession();
		CategoryDao dao = session.getMapper(CategoryDao.class);
		List<Category> list = dao.findAllCategories();
		
		return list;
	}

}

