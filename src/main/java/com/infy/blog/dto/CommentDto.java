package com.infy.blog.dto;

import jakarta.validation.constraints.NotEmpty;

public class CommentDto {

	private int id;
	@NotEmpty
	private String content;
	
	private UserDto user;
//	private PostDto post;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
//	public PostDto getPost() {
//		return post;
//	}
//	public void setPost(PostDto post) {
//		this.post = post;
//	}
	
	
}
