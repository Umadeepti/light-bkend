package com.dominionconsulting.tito.auth.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import org.junit.Before;
import org.junit.Test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
public class IndexControllerTest {
	private MockMvc mockMvc;
	
	@InjectMocks
	private IndexController indexController;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);		
		this.mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
	}
	
	@Test
	public void index_NoParameters_Gives200Status() throws Exception {
		mockMvc.perform(get("/"))
	           	.andExpect(status().isOk());
	}
}
