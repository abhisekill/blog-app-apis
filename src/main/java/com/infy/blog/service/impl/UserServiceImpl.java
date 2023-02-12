package com.infy.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.blog.dto.UserDto;
import com.infy.blog.entity.User;
import com.infy.blog.exception.ResourceNotFoundException;
import com.infy.blog.repository.UserRepo;
import com.infy.blog.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = this.userRepo.findAll();
		List<UserDto> userDtos = users.stream()
				.map(user -> userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	

	@Override
	public UserDto getUserById(Integer id) {
		User user = this.userRepo.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("User","id",id));
		return userToDto(user);
	}

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = dtoToUser(userDto);
		User savedUser = this.userRepo.save(user);
		return userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(Integer id, UserDto userDto) {
		User user = this.userRepo.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("User","id",id));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
	 	User updatedUser = this.userRepo.save(user);
		return userToDto(updatedUser);
	}

	@Override
	public void deleteUserById(Integer id) {
		User user = this.userRepo.findById(id)
						.orElseThrow(()->new ResourceNotFoundException("User","id",id));
		this.userRepo.delete(user);;
	}
	
	public UserDto userToDto(User user) {
//		UserDto userDto = new UserDto();
//
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
//		
//		return userDto;
		
		UserDto userDto = this.modelMapper.map(user,UserDto.class);
		return userDto;
	}
	
	public User dtoToUser(UserDto userDto) {
//		User user = new User();
//		
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
//		
//		return user;
		
		User user = this.modelMapper.map(userDto, User.class);
		return user;
	}
}
