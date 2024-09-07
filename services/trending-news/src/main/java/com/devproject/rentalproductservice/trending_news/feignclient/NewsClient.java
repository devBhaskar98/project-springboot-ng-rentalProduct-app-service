package com.devproject.rentalproductservice.trending_news.feignclient;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.devproject.rentalproductservice.trending_news.news.NewsResponse;

@FeignClient(name = "trending-news-service", url = "http://localhost:9091")
public interface NewsClient {

	@GetMapping("/trending")
	Optional<List<NewsResponse>> getAllNews();
}
