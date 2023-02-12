package com.infy.blog.service;

import com.infy.blog.dto.CommentDto;

public interface CommentService {
//	create
	CommentDto createComment(CommentDto commentDto, Integer userId, Integer postId);
	
//	delete
	void deleteComment(Integer id);
}
