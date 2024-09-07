package com.devproject.rentalproductservice.trending_news.news;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devproject.rentalproductservice.trending_news.feignclient.BusinessException;
import com.devproject.rentalproductservice.trending_news.feignclient.NewsClient;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NewsServices {

	@Autowired
	private final NewsClient newsClient;

	public Optional<List<NewsResponse>> getAllNews() {
		var news = this.newsClient.getAllNews().orElseThrow(
				() -> new BusinessException("Cannot create order:: No customer exists with the provided ID"));

		return Optional.ofNullable(news);
	}

}
