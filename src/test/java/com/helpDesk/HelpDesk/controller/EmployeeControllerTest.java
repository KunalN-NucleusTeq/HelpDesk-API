package com.helpDesk.HelpDesk.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.helpDesk.HelpDesk.Dto.SignInDto;
import com.helpDesk.HelpDesk.service.Impl.EmployeeServiceimpl;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {
	
	@Mock
	private EmployeeServiceimpl employeeServiceimpl;
	
	@InjectMocks
	private EmployeeController employeeController;
	
	@Autowired
	private MockMvc mockMvc;
	
	private SignInDto signDto;
	
	 @BeforeEach
	   public void setup(){
		signDto = new SignInDto(1,"email","password","hello");
	   mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
	   }
	 
	 @Test
	 public void PostMappingOfProduct()throws Exception{
		 ObjectMapper objectMapper = new ObjectMapper();
		 String inputJSON = objectMapper.writeValueAsString(signDto); 
	    when(employeeServiceimpl.employeeRegistation(any())).thenReturn(signDto);
	    mockMvc.perform(post("/employee/addEmployee").
	            contentType(MediaType.APPLICATION_JSON).
	            content(inputJSON)).
	            andExpect(status().isCreated());
	    verify(employeeServiceimpl,times(1)).employeeRegistation(any());
	 }
	 @Test
	 public void PostMappingOfProductNegative()throws Exception{
		 ObjectMapper objectMapper = new ObjectMapper();
		 String inputJSON = objectMapper.writeValueAsString(null); 
		 mockMvc.perform(post("/employee/addEmployee").
	            contentType(MediaType.APPLICATION_JSON).
	            content(inputJSON)).
	            andExpect(status().isBadRequest()).andReturn();
	 }
	 
	   @AfterEach
	   void tearDown() {
		signDto = null;
	}
	
}
