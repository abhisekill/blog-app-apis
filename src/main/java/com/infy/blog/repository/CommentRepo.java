package com.infy.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infy.blog.entity.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>{

}
