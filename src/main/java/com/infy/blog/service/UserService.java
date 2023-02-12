package com.infy.blog.service;

import java.util.List;

import com.infy.blog.dto.UserDto;

public interface UserService {
	
	List<UserDto> getAllUsers();
	
	UserDto getUserById(Integer id);
	
	UserDto createUser(UserDto user);
	
	UserDto updateUser(Integer id, UserDto user);
	
	void deleteUserById(Integer id);
}
