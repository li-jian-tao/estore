package com.briup.service;

import java.io.IOException;
import java.util.List;

import com.briup.bean.Category;

public interface ICategotyService {
	
	public List<Category> findAllCategories() throws IOException;
	
}
