package com.infy.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.infy.blog.dto.CommentDto;
import com.infy.blog.service.CommentService;
import com.infy.blog.utils.ApiResponse;

@RestController
@RequestMapping("/apis")
public class CommentController {
	@Autowired
	private CommentService commentService;
	
//create 
	@PostMapping("/posts/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,
			@RequestParam(value = "user",required = true) Integer userId,
			@PathVariable("postId") Integer postId){
		
		CommentDto createdComment = this.commentService.createComment(commentDto, userId, postId);
		return new ResponseEntity<CommentDto>(createdComment,HttpStatus.CREATED);
	}
	
//	delete
	@DeleteMapping("/comments/{id}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer id){
		this.commentService.deleteComment(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("comment deleted successfully",true),HttpStatus.OK);
	}
	
}
