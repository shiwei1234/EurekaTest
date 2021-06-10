package com.eureka;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EurekaClient1ApplicationTests {
	EurekaService service;
	@Test
	void contextLoads() {
		System.out.println(service.getInfo());
	}

}
