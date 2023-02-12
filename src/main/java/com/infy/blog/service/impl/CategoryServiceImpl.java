package com.infy.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.blog.dto.CategoryDto;
import com.infy.blog.entity.Category;
import com.infy.blog.exception.ResourceNotFoundException;
import com.infy.blog.repository.CategoryRepo;
import com.infy.blog.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	private Category dtoToCategory(CategoryDto categoryDto) {
		return this.modelMapper.map(categoryDto, Category.class);
	}
	
	private CategoryDto categoryToDto(Category category) {
		return this.modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category = dtoToCategory(categoryDto);
		Category savedCategory = this.categoryRepo.save(category);
		return categoryToDto(savedCategory);
	}

	@Override
	public CategoryDto updateCategory(Integer id, CategoryDto categoryDto) {
		Category category = this.categoryRepo.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Category", "id", id));
		
		category.setTitle(categoryDto.getTitle());
		category.setDesc(categoryDto.getDesc());
		Category updatedCategory = this.categoryRepo.save(category);
		
		return categoryToDto(updatedCategory);
	}

	@Override
	public void deleteCategory(Integer id) {
		Category category = this.categoryRepo.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Category", "id", id));
		
		this.categoryRepo.delete(category);
	}

	@Override
	public CategoryDto getCategoryById(Integer id) {
		Category category = this.categoryRepo.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Category", "id", id));
		return categoryToDto(category);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> categories= this.categoryRepo.findAll();
	 	List<CategoryDto> categoryDtos = categories.stream()
	 			.map((c)-> categoryToDto(c)).collect(Collectors.toList());
		return categoryDtos;
	}

}
