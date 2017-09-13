package com.dominionconsulting.tito.opp.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

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
import com.dominionconsulting.tito.opp.dto.OppDetailsDto;
import com.dominionconsulting.tito.opp.dto.OppNewDto;
import com.dominionconsulting.tito.opp.dto.OppSummaryDto;
import com.dominionconsulting.tito.opp.dto.PersonDto;
import com.dominionconsulting.tito.opp.dto.SolutionDto;
import com.dominionconsulting.tito.opp.model.Opportunity;
import com.dominionconsulting.tito.opp.service.OppNewService;

@WebAppConfiguration
@Transactional
public class OppNewControllerTest extends AbstractTest {

	private MockMvc mvc;
	
	@Mock
	private OppNewService service;
	
	@InjectMocks
	private OppNewController controller;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.standaloneSetup(controller)
				.setControllerAdvice(new TitoExceptionHandler()).build();
	}
	
	@Test
	public void getInfo_ReceivesGetRequest_Returns200Status() throws Exception {
		OppNewDto dto = getInfoDtoStubData();
		
		when(service.getInfo()).thenReturn(dto);
		
		String uri = "/opps/new/";
		
		MvcResult result = mvc.perform(get(uri)).andReturn();
		
		String content = result.getResponse().getContentAsString();
		int status = result.getResponse().getStatus();
		
		Assert.assertEquals("failure - expected HTTP status 200", 200, status);
		Assert.assertTrue("failure - expected HTTP response body to have a value",
				content.trim().length() > 0);
	}
	
	@Test
	public void getInfo_ReceivesGetRequest_Returns404Status() throws Exception {		
		when(service.getInfo()).thenThrow(new NoResultException());
		
		String uri = "/opps/new/";
		
		MvcResult result = mvc.perform(get(uri)).andReturn();
		
		int status = result.getResponse().getStatus();
		
		Assert.assertEquals("failure - expected HTTP status 404", 404, status);
	}
	
	@Test
	public void createOpp_ReceivesPostRequest_Returns201Status() throws Exception {
		OppNewDto dtoNoId = getCreateDtoNoIdStubData();
		OppNewDto dtoWithId = getCreateDtoWithIdStubData();
		
		when(service.create(any(OppNewDto.class))).thenReturn(dtoWithId.getId());
		when(service.getOne(dtoWithId.getId())).thenReturn(dtoWithId);
		
		String uri = "/opps/new/";
		String inputJson = mapToJson(dtoNoId);
		
		MvcResult result = mvc.perform(post(uri)
				.contentType(MediaType.APPLICATION_JSON).content(inputJson))
				.andReturn();
		
		String content = result.getResponse().getContentAsString();
		int status = result.getResponse().getStatus();
		
		Assert.assertEquals("failure - expected HTTP status 201", 201, status);
		Assert.assertTrue("failure - expected HTTP response body to have a value",
				content.trim().length() > 0);
		
		OppNewDto returnedDto = mapFromJson(content, OppNewDto.class);
		
		Assert.assertEquals("failure - expected name attribute match",
				dtoNoId.getSummary().getName(), returnedDto.getSummary().getName());
	}
	
	@Test
	public void createOpp_ReceivesPostRequestAndEntityExistsException_Returns400Status()
			throws Exception {
		OppNewDto dto = getCreateDtoNoIdStubData();
		
		when(service.create(any(OppNewDto.class)))
				.thenThrow(new EntityExistsException());
		
		String uri = "/opps/new/";
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
		OppNewDto dto = getCreateDtoNoIdStubData();
		
		when(service.create(any(OppNewDto.class)))
				.thenThrow(new TitoException("Cannot Create Opportunity"));
		
		String uri = "/opps/new/";
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
		OppNewDto dto = getCreateDtoNoIdStubData();
		
		when(service.create(any(OppNewDto.class))).thenReturn(0);
		when(service.getOne(0)).thenThrow(new NoResultException());
		
		String uri = "/opps/new/";
		String inputJson = mapToJson(dto);
		
		MvcResult result = mvc.perform(post(uri)
				.contentType(MediaType.APPLICATION_JSON).content(inputJson))
				.andReturn();
		
		int status = result.getResponse().getStatus();
		
		Assert.assertEquals("failure - expected HTTP status 404", 404, status);
	}

	private OppNewDto getInfoDtoStubData() {
		OppSummaryDto summary = new OppSummaryDto();
		OppDetailsDto details = new OppDetailsDto();

		summary.setAccounts(new ArrayList<AccountDto>());
		summary.setPeople(new ArrayList<PersonDto>());
		
		details.setSolutions(new ArrayList<SolutionDto>());

		OppNewDto dto = new OppNewDto();
		dto.setSummary(summary);
		dto.setDetails(details);
		return dto;
	}
	
	private OppNewDto getCreateDtoNoIdStubData() {
		Opportunity opp = getOppNoIdStubData();
		return new OppNewDto(opp);
	}
	
	private OppNewDto getCreateDtoWithIdStubData() {
		Opportunity opp = getOppWithIdStubData();
		return new OppNewDto(opp);
	}
	
}
