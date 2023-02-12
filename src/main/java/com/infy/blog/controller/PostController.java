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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.infy.blog.dto.PostDto;
import com.infy.blog.service.PostService;
import com.infy.blog.utils.ApiResponse;
import com.infy.blog.utils.PostResponse;

@RestController
@RequestMapping("/apis")
public class PostController {
	
	@Autowired
	private PostService postService;

//	create
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, 
			@PathVariable Integer userId,@PathVariable Integer categoryId){
		PostDto createdPost =  this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createdPost,HttpStatus.CREATED);
	}
	
//	update
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@PathVariable Integer postId, @RequestBody PostDto postDto){
		PostDto updatedPost = this.postService.updatePost(postId, postDto);
		return new ResponseEntity<PostDto>(updatedPost,HttpStatus.ACCEPTED);
	}
	
	
//	delete
	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<ApiResponse> deletePostById(@PathVariable Integer postId){
		this.postService.deletePost(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post Deleted successfully",true),HttpStatus.OK);
	}
	
//	get a single
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
		return ResponseEntity.ok(this.postService.getPostById(postId));
	}
	
//	get all
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value = "pageNumber", defaultValue = "0",required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
			@RequestParam(value = "sortBy",defaultValue = "id",required = false) String sortBy,
			@RequestParam(value = "sortDir",defaultValue = "asc",required = false) String sortDir){
		PostResponse response = this.postService.getAllPost(pageNumber, pageSize,sortBy,sortDir);
		return ResponseEntity.ok(response);
	}
	
//	get posts by user id
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId){
		List<PostDto> posts = this.postService.getPostsByUserId(userId);
		return ResponseEntity.ok(posts);
	}
	
//	get posts by category id
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId){
		List<PostDto> posts = this.postService.getPostsByCategoryId(categoryId);
		return ResponseEntity.ok(posts);
	}
	
//	search a post by keyword
	@GetMapping("/posts/search")
	public ResponseEntity<List<PostDto>> searchPostByKeyword(@RequestParam(value = "keyword")String key){
		List<PostDto> result = this.postService.searchPost(key);
		return ResponseEntity.ok(result);
	}
	
	
	
}
