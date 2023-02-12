package com.infy.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infy.blog.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
