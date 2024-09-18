package com.devproject.rentalproductservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = { "logging.level.org.springframework=ERROR", "logging.level.root=ERROR" })
class RentalProductServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
