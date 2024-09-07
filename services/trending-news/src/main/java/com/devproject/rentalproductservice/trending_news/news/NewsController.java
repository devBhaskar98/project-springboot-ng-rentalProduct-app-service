package com.devproject.rentalproductservice.trending_news.news;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class NewsController {

	@Autowired
	private NewsServices newsService;

	@GetMapping()
	public Optional<List<NewsResponse>> getAllNews() {
		return this.newsService.getAllNews();
	}

}
