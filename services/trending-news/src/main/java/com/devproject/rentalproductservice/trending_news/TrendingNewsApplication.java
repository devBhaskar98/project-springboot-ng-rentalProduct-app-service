package com.devproject.rentalproductservice.trending_news;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TrendingNewsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrendingNewsApplication.class, args);
	}

}
