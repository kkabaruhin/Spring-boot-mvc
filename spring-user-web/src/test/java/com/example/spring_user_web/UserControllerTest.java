package com.example.spring_user_web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.spring_user_web.service.UserService;
import com.example.spring_user_web.web.controller.UserController;
import com.example.spring_user_web.web.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UserController.class)
class UserControllerTest {

	@MockitoBean
	UserService userService;

	@Autowired
	MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void allUsersTest() throws Exception {
		mockMvc.perform(get("/ui/users")).andExpect(status().isOk());
	}

	@Test
	void showAddFormTest() throws Exception {
		mockMvc.perform(get("/ui/users/add")).andExpect(status().isOk());
	}

	@Test
	void addUserTest() throws Exception {
		UserDto userDto = new UserDto();
		String json = objectMapper.writeValueAsString(userDto);
		mockMvc.perform(post("/ui/users/add")
						.contentType(String.valueOf(MediaType.APPLICATION_JSON))
						.content(json))
				.andExpect(status().isOk());
	}

	@Test
	void deleteUserTest() throws Exception {
		mockMvc.perform(get("/ui/users/delete/{id}")).andExpect(status().isOk());
	}

	@Test
	void showUpdateFormTest() throws Exception {
		mockMvc.perform(get("/ui/users/update/{id}")).andExpect(status().isOk());
	}

	@Test
	void updateUserTest() throws Exception {
		UserDto userDto = new UserDto();
		String json = objectMapper.writeValueAsString(userDto);
		mockMvc.perform(post("/ui/users/update")
						.contentType(String.valueOf(MediaType.APPLICATION_JSON))
						.content(json))
				.andExpect(status().isOk());
	}

}
