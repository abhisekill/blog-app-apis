package com.infy.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infy.blog.entity.User;

public interface UserRepo extends JpaRepository<User, Integer>{

}
