package com.devproject.rentalproductservice;

import java.util.TimeZone;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;

import com.devproject.rentalproductservice.config.StorageProperties;

@SpringBootApplication
@EnableCaching
@EnableConfigurationProperties(StorageProperties.class)
public class RentalProductServiceApplication {

	private static ConfigurableApplicationContext context;

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
		context = SpringApplication.run(RentalProductServiceApplication.class, args);
		System.out.println("Rental Product Service is running...");
	}

	/*
	 * Restart application by closing the context and running new application in new
	 * thread
	 */
	public static void restart() {
		ApplicationArguments args = context.getBean(ApplicationArguments.class);

		Thread thread = new Thread(() -> {
			context.close();
			context = SpringApplication.run(RentalProductServiceApplication.class, args.getSourceArgs());
		});

		thread.setDaemon(false);
		thread.start();
	}
}
