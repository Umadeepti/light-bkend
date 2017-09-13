package com.dominionconsulting.tito.opp.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.util.ArrayList;

import javax.persistence.EntityExistsException;
import javax.persistence.NoResultException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.dominionconsulting.tito.opp.AbstractTest;
import com.dominionconsulting.tito.opp.common.util.TitoException;
import com.dominionconsulting.tito.opp.common.util.TitoExceptionHandler;
import com.dominionconsulting.tito.opp.dto.AccountDto;
import com.dominionconsulting.tito.opp.dto.OppDetailDto;
import com.dominionconsulting.tito.opp.dto.OppDetailsDto;
import com.dominionconsulting.tito.opp.dto.OppNewDto;
import com.dominionconsulting.tito.opp.dto.OppPursueDto;
import com.dominionconsulting.tito.opp.dto.OppSummaryDto;
import com.dominionconsulting.tito.opp.dto.PersonDto;
import com.dominionconsulting.tito.opp.dto.SolutionDto;
import com.dominionconsulting.tito.opp.model.Opportunity;
import com.dominionconsulting.tito.opp.service.OppNewService;
import com.dominionconsulting.tito.opp.service.OppPursueService;

@WebAppConfiguration
@Transactional
public class OppPursueControllerTest extends AbstractTest {

	private MockMvc mvc;
	
	@Mock
	private OppPursueService service;
	
	@InjectMocks
	private OppPursueController controller;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.standaloneSetup(controller)
				.setControllerAdvice(new TitoExceptionHandler()).build();
	}

	@Test
	public void createOpp_ReceivesPostRequest_Returns201Status() throws Exception {
		OppPursueDto dtoNoId = getCreateDtoNoIdStubData();
		OppPursueDto dtoWithId = getCreateDtoWithIdStubData();
		
		when(service.create(any(OppPursueDto.class))).thenReturn(dtoWithId.getId());
		when(service.getOne(dtoWithId.getId())).thenReturn(dtoWithId);
		
		String uri = "/opps/pursue/";
		String inputJson = mapToJson(dtoNoId);
		
		MvcResult result = mvc.perform(post(uri)
				.contentType(MediaType.APPLICATION_JSON).content(inputJson))
				.andReturn();
		
		String content = result.getResponse().getContentAsString();
		int status = result.getResponse().getStatus();
		
		Assert.assertEquals("failure - expected HTTP status 201", 201, status);
		Assert.assertTrue("failure - expected HTTP response body to have a value",
				content.trim().length() > 0);
		
		OppPursueDto returnedDto = mapFromJson(content, OppPursueDto.class);
		
		Assert.assertEquals("failure - expected name attribute match",
				dtoNoId.getPursue(), returnedDto.getPursue());
	}
	
	@Test
	public void createOpp_ReceivesPostRequestAndEntityExistsException_Returns400Status()
			throws Exception {
		OppPursueDto dto = getCreateDtoNoIdStubData();
		
		when(service.create(any(OppPursueDto.class)))
				.thenThrow(new EntityExistsException());
		
		String uri = "/opps/pursue/";
		String inputJson = mapToJson(dto);
		
		MvcResult result = mvc.perform(post(uri)
				.contentType(MediaType.APPLICATION_JSON).content(inputJson))
				.andReturn();
		
		int status = result.getResponse().getStatus();
		
		Assert.assertEquals("failure - expected HTTP status 400", 400, status);
	}
	
	@Test
	public void createOpp_ReceivesPostRequestAndTitoException_Returns400Status()
			throws Exception {
		OppPursueDto dto = getCreateDtoNoIdStubData();
		
		when(service.create(any(OppPursueDto.class)))
				.thenThrow(new TitoException("Cannot Create Opportunity"));
		
		String uri = "/opps/pursue/";
		String inputJson = mapToJson(dto);
		
		MvcResult result = mvc.perform(post(uri)
				.contentType(MediaType.APPLICATION_JSON).content(inputJson))
				.andReturn();
		
		int status = result.getResponse().getStatus();
		
		Assert.assertEquals("failure - expected HTTP status 400", 400, status);
	}
	
	@Test
	public void createOpp_ReceivesPostRequestAndNoResultException_Returns400Status()
			throws Exception {
		OppPursueDto dto = getCreateDtoNoIdStubData();
		
		when(service.create(any(OppPursueDto.class))).thenReturn(0);
		when(service.getOne(0)).thenThrow(new NoResultException());
		
		String uri = "/opps/pursue/";
		String inputJson = mapToJson(dto);
		
		MvcResult result = mvc.perform(post(uri)
				.contentType(MediaType.APPLICATION_JSON).content(inputJson))
				.andReturn();
		
		int status = result.getResponse().getStatus();
		
		Assert.assertEquals("failure - expected HTTP status 404", 404, status);
	}

	private OppPursueDto getInfoDtoStubData() {
		
		OppPursueDto dto= new OppPursueDto();
		return dto;
	}
	
	private OppPursueDto getCreateDtoNoIdStubData() {
		Opportunity opp = getOppNoIdStubData();
		return new OppPursueDto(opp);
	}
	
	private OppPursueDto getCreateDtoWithIdStubData() {
		Opportunity opp = getOppWithIdStubData();
		return new OppPursueDto(opp);
	}
	private OppPursueDto getDtoWithPursueStubData() {
		Opportunity opp = getOppWithPursueStubData();
		return new OppPursueDto(opp);
	}
	
}
