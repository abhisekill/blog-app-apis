package com.infy.blog.service;

import java.util.List;

import com.infy.blog.dto.CategoryDto;

public interface CategoryService {

//	create
	CategoryDto createCategory(CategoryDto categoryDto);
	
//	update
	CategoryDto updateCategory(Integer id, CategoryDto categoryDto);
	
//	delete
	void deleteCategory(Integer id);
	
//	get
	CategoryDto getCategoryById(Integer id);
	
//	get all
	List<CategoryDto> getAllCategory();
}
