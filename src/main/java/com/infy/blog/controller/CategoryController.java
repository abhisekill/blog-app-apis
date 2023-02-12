package com.infy.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infy.blog.dto.CategoryDto;
import com.infy.blog.service.CategoryService;
import com.infy.blog.utils.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/apis/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
//	get all
	@GetMapping
	public ResponseEntity<List<CategoryDto>> getAllCategory(){
		return ResponseEntity.ok(this.categoryService.getAllCategory());
	}
	
//	get single
	@GetMapping("/{id}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable("id") Integer id){
		return ResponseEntity.ok(this.categoryService.getCategoryById(id));
	}
	
//	create
	@PostMapping
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
		CategoryDto cat = this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(cat,HttpStatus.CREATED);
	}
	
//	update
	@PutMapping("/{id}")
	public ResponseEntity<CategoryDto> updateCategory(@PathVariable Integer id,@Valid @RequestBody CategoryDto categoryDto){
		CategoryDto updatedDto = this.categoryService.updateCategory(id, categoryDto);
		return ResponseEntity.ok(updatedDto);
	}
	
	
//	delete
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("id") Integer id){
		this.categoryService.deleteCategory(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted successfully",true),HttpStatus.OK);
	}
	
}
