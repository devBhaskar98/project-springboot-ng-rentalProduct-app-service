package com.devproject.rentalproductservice.trending_news.feignclient;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BusinessException extends RuntimeException {

	public BusinessException(String message) {
		super(message);
	}
}
