package com.devproject.rentalproductservice.service.imp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;

import com.devproject.rentalproductservice.entity.Comment;
import com.devproject.rentalproductservice.repository.CommentRepository;

@EnableCaching // Ensure caching is enabled during the test
class CommentServiceImplTest {

	@Mock
	private CommentRepository commentRepository;

	@InjectMocks
	private CommentServiceImpl commentService;

	private CacheManager cacheManager;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this); // Initialize mocks
		cacheManager = new ConcurrentMapCacheManager("comments"); // Initialize Cache Manager
	}

	@Test
	void testAddComment() {
		Comment comment = new Comment();
		comment.setId(1);
		comment.setComment("This is a test comment");

		when(commentRepository.save(any(Comment.class))).thenReturn(comment);

		Comment savedComment = commentService.addComment(comment);

		assertNotNull(savedComment);
		assertEquals(1, savedComment.getId());
		assertEquals("This is a test comment", savedComment.getComment());
	}

	@Test
	@Disabled
	void testGetComment() {
		Comment comment1 = new Comment();
		comment1.setId(1);
		comment1.setComment("Test Comment 1");

		Comment comment2 = new Comment();
		comment2.setId(2);
		comment2.setComment("Test Comment 2");

		when(commentRepository.findAll()).thenReturn(Arrays.asList(comment1, comment2));

		// Clear cache before calling the method
		cacheManager.getCache("comments").clear();

		// First call should go through the repository
		Set<Comment> comments = commentService.getComment();
		assertNotNull(comments);
		assertEquals(2, comments.size());

		// Second call should return the cached result, repository shouldn't be called
		Set<Comment> cachedComments = commentService.getComment();
		verify(commentRepository, times(1)).findAll(); // Ensure repository is called only once

		assertEquals(comments, cachedComments);
	}
}
