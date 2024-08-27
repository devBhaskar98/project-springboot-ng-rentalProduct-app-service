package com.devproject.rentalproductservice.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devproject.rentalproductservice.RentalProductServiceApplication;

@RestController
public class AppController {
	
	@PostMapping("/restart")
	public void restart() {
		RentalProductServiceApplication.restart();
	}

}
