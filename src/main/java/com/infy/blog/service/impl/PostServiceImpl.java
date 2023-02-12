package com.infy.blog.service.impl;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.infy.blog.dto.PostDto;
import com.infy.blog.entity.Category;
import com.infy.blog.entity.Post;
import com.infy.blog.entity.User;
import com.infy.blog.exception.ResourceNotFoundException;
import com.infy.blog.repository.CategoryRepo;
import com.infy.blog.repository.PostRepo;
import com.infy.blog.repository.UserRepo;
import com.infy.blog.service.PostService;
import com.infy.blog.utils.PostResponse;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	private Post toPost(PostDto postDto) {
		return this.modelMapper.map(postDto, Post.class);
	}
	
	private PostDto toDto(Post post) {
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "id", categoryId));
		
		Post post = toPost(postDto);
		post.setImageUrl("default.png");
		post.setCategory(category);
		post.setUser(user);
		post.setDate(new Date());
		
		Post savedPost = this.postRepo.save(post);
		return toDto(savedPost);
	}

	@Override
	public PostDto updatePost(Integer postId, PostDto postDto) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(()->new ResourceNotFoundException("Post", "id", postId));
		
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageUrl(postDto.getImageUrl());
		
		Post updatedPost = this.postRepo.save(post);
		return toDto(updatedPost);
	}

	@Override
	public void deletePost(Integer postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(()->new ResourceNotFoundException("Post", "id", postId));
		this.postRepo.delete(post);
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(()->new ResourceNotFoundException("Post", "id", postId));
		return toDto(post);
	}
	
//	get all posts

	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir) {
		PostResponse response = new PostResponse();
		
		Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
				
		Pageable p = PageRequest.of(pageNumber, pageSize,sort);
		Page<Post> postsPage = this.postRepo.findAll(p);
		List<Post> posts = postsPage.getContent();
		List<PostDto> postDtos = posts.stream().map((post)->toDto(post)).collect(Collectors.toList());
		
		response.setContent(postDtos);
		response.setPageNumber(postsPage.getNumber());
		response.setPageSize(postsPage.getSize());
		response.setTotalElements(postsPage.getTotalElements());
		response.setTotalPages(postsPage.getTotalPages());
		response.setLastPage(postsPage.isLast());
		
		return response;
	}

//	get all posts by a user 
	
	@Override
	public List<PostDto> getPostsByUserId(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(()->new ResourceNotFoundException("user", "id", userId));
		List<Post> posts=  this.postRepo.findByUser(user);
		List<PostDto> postDtos = posts.stream().map((post)->toDto(post)).collect(Collectors.toList());
		return postDtos;
	}
	
//	get all posts inside a category
	@Override
	public List<PostDto> getPostsByCategoryId(Integer categoryId) {
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(()->new ResourceNotFoundException("Category", "id", categoryId));
		List<Post> posts=  this.postRepo.findByCategory(category);
		List<PostDto> postDtos = posts.stream().map((post)->toDto(post)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> searchPost(String keyword) {
		List<Post> posts = this.postRepo.findByTitleContaining(keyword);
		List<PostDto> postDtos = posts.stream().map((post)->toDto(post)).collect(Collectors.toList());
		return postDtos;
	}

}
