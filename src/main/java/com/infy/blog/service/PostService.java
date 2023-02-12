package com.infy.blog.service;

import java.util.List;

import com.infy.blog.dto.PostDto;
import com.infy.blog.utils.PostResponse;

public interface PostService {
//	create
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	
//	update
	PostDto updatePost(Integer postId, PostDto postDto);
	
//	delete
	void deletePost(Integer postId);
	
//	get a single post
	PostDto getPostById(Integer postId);
	
//	get all posts
	PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy,String sortDir);
	
//	get all posts by user
	List<PostDto> getPostsByUserId(Integer userId);
	
//	get all posts by category
	List<PostDto> getPostsByCategoryId(Integer categoryId);
	
//	search a post using a keyword
	List<PostDto> searchPost(String keyword);
}
