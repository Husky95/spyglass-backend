package com.skillstorm.spyglass.controller;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class GoalControllerTest {
	
	@Autowired
	private MockMvc mockMvc;	
		@Test
	    @DisplayName("GET /goals/all/{username}")
	    void findAll() throws Exception {
	        mockMvc.perform(MockMvcRequestBuilders
	        		.get("/goals/all/user1"));
	               
	    }
		@Test
	    @DisplayName("POST /goals")
	    void create() throws Exception {
	        mockMvc.perform(MockMvcRequestBuilders
	        		.post("/goals"));
	               
	    } 
		@Test
	    @DisplayName("PUT /goals/{id]")
	    void update() throws Exception {
	        mockMvc.perform(MockMvcRequestBuilders
	        		.put("/goals/1"));
	               
	    } 
		@Test
	    @DisplayName("PUT /goals/deposit/{id]")
	    void updateCurrentAmount() throws Exception {
	        mockMvc.perform(MockMvcRequestBuilders
	        		.put("/goals/deposit/1"));
	               
	    } 
		@Test
	    @DisplayName("DELETE /goals/{id]")
	    void delete() throws Exception {
	        mockMvc.perform(MockMvcRequestBuilders
	        		.put("/goals/1"));
	               
	    } 
		
}
