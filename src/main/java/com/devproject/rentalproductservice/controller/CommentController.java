package com.devproject.rentalproductservice.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.devproject.rentalproductservice.entity.Comment;
import com.devproject.rentalproductservice.service.CommentService;

@RestController
@RequestMapping("/comment/v1")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", methods = { RequestMethod.GET,
		RequestMethod.POST })
public class CommentController {

	@Autowired
	private CommentService commentService;

	// get all Comments
	@GetMapping("/")
	public ResponseEntity<Set<Comment>> getComments() {
		Set<Comment> comments = commentService.getComment();

		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CACHE_CONTROL, "max-age=3600"); // Cache for 1 hour

		return new ResponseEntity<>(comments, headers, HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<Comment> addComment(@RequestBody Comment comment) {
		Comment commentResult = this.commentService.addComment(comment);
		return ResponseEntity.ok(commentResult);
	}

}
