package com.devproject.rentalproductservice.service;

import java.util.Set;

import com.devproject.rentalproductservice.entity.Comment;

public interface CommentService {

	public Comment addComment(Comment comment);

	public Set<Comment> getComment();

}
