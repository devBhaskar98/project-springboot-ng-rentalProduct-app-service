package com.devproject.rentalproductservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.devproject.rentalproductservice.interceptor.CacheControllerInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	private final CacheControllerInterceptor cacheControlInterceptor;

	public WebConfig(CacheControllerInterceptor cacheControlInterceptor) {
		this.cacheControlInterceptor = cacheControlInterceptor;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// Register the CacheControlInterceptor to apply to all endpoints
		registry.addInterceptor(cacheControlInterceptor).addPathPatterns("/**");
	}
}
