package com.dominionconsulting.tito.opp.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

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
import com.dominionconsulting.tito.opp.dto.OppBidDto;
import com.dominionconsulting.tito.opp.model.Opportunity;
import com.dominionconsulting.tito.opp.service.OppBidService;

@WebAppConfiguration
@Transactional
public class OppBidControllerTest extends AbstractTest {

	private MockMvc mvc;
	
	@Mock
	private OppBidService service;
	
	@InjectMocks
	private OppBidController controller;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.standaloneSetup(controller)
				.setControllerAdvice(new TitoExceptionHandler()).build();
	}

	@Test
	public void createOpp_ReceivesPostRequest_Returns201Status() throws Exception {
		OppBidDto dtoNoId = getCreateDtoNoIdStubData();
		OppBidDto dtoWithId = getCreateDtoWithIdStubData();
		
		when(service.create(any(OppBidDto.class))).thenReturn(dtoWithId.getId());
		when(service.getOne(dtoWithId.getId())).thenReturn(dtoWithId);
		
		String uri = "/opps/bid/";
		String inputJson = mapToJson(dtoNoId);
		
		MvcResult result = mvc.perform(post(uri)
				.contentType(MediaType.APPLICATION_JSON).content(inputJson))
				.andReturn();
		
		String content = result.getResponse().getContentAsString();
		int status = result.getResponse().getStatus();
		
		Assert.assertEquals("failure - expected HTTP status 201", 201, status);
		Assert.assertTrue("failure - expected HTTP response body to have a value",
				content.trim().length() > 0);
		
		OppBidDto returnedDto = mapFromJson(content, OppBidDto.class);
		
		Assert.assertEquals("failure - expected name attribute match",
				dtoNoId.getBid(), returnedDto.getBid());
	}
	
	@Test
	public void createOpp_ReceivesPostRequestAndEntityExistsException_Returns400Status()
			throws Exception {
		OppBidDto dto = getCreateDtoNoIdStubData();
		
		when(service.create(any(OppBidDto.class)))
				.thenThrow(new EntityExistsException());
		
		String uri = "/opps/bid/";
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
		OppBidDto dto = getCreateDtoNoIdStubData();
		
		when(service.create(any(OppBidDto.class)))
				.thenThrow(new TitoException("Cannot Create Opportunity"));
		
		String uri = "/opps/bid/";
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
		OppBidDto dto = getCreateDtoNoIdStubData();
		
		when(service.create(any(OppBidDto.class))).thenReturn(0);
		when(service.getOne(0)).thenThrow(new NoResultException());
		
		String uri = "/opps/bid/";
		String inputJson = mapToJson(dto);
		
		MvcResult result = mvc.perform(post(uri)
				.contentType(MediaType.APPLICATION_JSON).content(inputJson))
				.andReturn();
		
		int status = result.getResponse().getStatus();
		
		Assert.assertEquals("failure - expected HTTP status 404", 404, status);
	}

	private OppBidDto getInfoDtoStubData() {
		
		OppBidDto dto= new OppBidDto();
		return dto;
	}
	
	private OppBidDto getCreateDtoNoIdStubData() {
		Opportunity opp = getOppNoIdStubData();
		return new OppBidDto(opp);
	}
	
	private OppBidDto getCreateDtoWithIdStubData() {
		Opportunity opp = getOppWithIdStubData();
		return new OppBidDto(opp);
	}
	private OppBidDto getDtoWithPursueStubData() {
		Opportunity opp = getOppWithBidStubData();
		return new OppBidDto(opp);
	}
	
}
