package com.devproject.rentalproductservice.service.imp;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devproject.rentalproductservice.entity.Comment;
import com.devproject.rentalproductservice.repository.CommentRepository;
import com.devproject.rentalproductservice.service.CommentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentRepository;

	@Override
	public Comment addComment(Comment comment) {
		return this.commentRepository.save(comment);
	}

	@Override
//	@Cacheable(value = "comments", key = "#root.methodName")
	public Set<Comment> getComment() {
		return new LinkedHashSet<>(this.commentRepository.findAll());
	}

}
