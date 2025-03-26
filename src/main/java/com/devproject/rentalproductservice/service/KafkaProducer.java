package com.devproject.rentalproductservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public void sendMessageToRentalTopic(String message) {
		kafkaTemplate.send("RentalProductTopic", message);
	}

};