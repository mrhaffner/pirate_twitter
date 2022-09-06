package com.piritter.api;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.piritter.api.controller.AuthController;


@SpringBootTest
class ApiApplicationTests {
	@Autowired
	private AuthController authController;

	@Test
	void contextLoads() {
		assertThat(authController).isNotNull();
	}

}
