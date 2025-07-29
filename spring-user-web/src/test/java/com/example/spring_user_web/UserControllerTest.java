package com.example.spring_user_web;

import com.example.spring_user_web.service.UserService;
import com.example.spring_user_web.web.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UserController.class)
class UserControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockitoBean
	UserService userService;

	@Test
	void contextLoads() {
		//Error: Could not find or load main class worker.org.gradle.process.internal.worker.GradleWorkerMain
	}

}
