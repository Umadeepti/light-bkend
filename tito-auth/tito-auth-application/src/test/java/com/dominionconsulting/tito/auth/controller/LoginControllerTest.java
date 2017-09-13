package com.dominionconsulting.tito.auth.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.saml.metadata.MetadataManager;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
public class LoginControllerTest {
	private MockMvc mockMvc;
	
	@Mock
	private MetadataManager mm;
	
	@InjectMocks
	private LoginController loginController;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);		
		this.mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
	}
	
	//test
}