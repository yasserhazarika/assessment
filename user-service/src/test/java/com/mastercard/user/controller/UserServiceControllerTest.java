package com.mastercard.user.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.mastercard.user.service.UserService;
import com.mastercard.user.service.UserServiceProd;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(profiles = "prod")
@PropertySource("classpath:/application-test.properties")
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
class UserServiceControllerTest {

	@Autowired
	private UserService userService;

	@Autowired
	private Environment environment;

	@Test
	void testSpringProfiles() {
		Assertions.assertEquals("prod", environment.getActiveProfiles()[0]);
		Assertions.assertTrue(userService instanceof UserServiceProd);
	}

}
