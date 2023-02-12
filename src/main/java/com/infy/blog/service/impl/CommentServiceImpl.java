package com.infy.blog.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.blog.dto.CommentDto;
import com.infy.blog.entity.Comment;
import com.infy.blog.entity.Post;
import com.infy.blog.entity.User;
import com.infy.blog.exception.ResourceNotFoundException;
import com.infy.blog.repository.CommentRepo;
import com.infy.blog.repository.PostRepo;
import com.infy.blog.repository.UserRepo;
import com.infy.blog.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer userId, Integer postId) {
		User user = this.userRepo.findById(userId)
					.orElseThrow(()->new ResourceNotFoundException("User", "id", userId));
		Post post = this.postRepo.findById(postId)
				.orElseThrow(()->new ResourceNotFoundException("Post", "id", postId));
		Comment comment = toComment(commentDto);
		comment.setUser(user);
		comment.setPost(post);
		Comment savedComment = this.commentRepo.save(comment);
		return toDto(savedComment);
	}

	@Override
	public void deleteComment(Integer id) {
		Comment comment = this.commentRepo.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Comment", "id", id));
		this.commentRepo.delete(comment);
	}
	
	private CommentDto toDto(Comment comment) {
		return this.modelMapper.map(comment, CommentDto.class);
	}
	
	private Comment toComment(CommentDto commentDto) {
		return this.modelMapper.map(commentDto, Comment.class);
	}

}
