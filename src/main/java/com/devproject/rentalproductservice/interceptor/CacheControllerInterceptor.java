package com.devproject.rentalproductservice.interceptor;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CacheControllerInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// Proceed with the request (no changes here)
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// Set the Cache-Control and Expires headers after handler executes
		response.setHeader(HttpHeaders.CACHE_CONTROL, "max-age=60, public");
		response.setHeader(HttpHeaders.EXPIRES, String.valueOf(System.currentTimeMillis() + 60 * 1000));

		// Log to check if headers are set
		System.out.println("Cache-Control: " + response.getHeader(HttpHeaders.CACHE_CONTROL));
		System.out.println("Expires: " + response.getHeader(HttpHeaders.EXPIRES));
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		response.setHeader(HttpHeaders.CACHE_CONTROL, "max-age=60, public");
		response.setHeader(HttpHeaders.EXPIRES, String.valueOf(System.currentTimeMillis() + 60 * 1000));
	}
}
