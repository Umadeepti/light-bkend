package com.dominionconsulting.tito.opp.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

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
import com.dominionconsulting.tito.opp.common.util.TitoExceptionHandler;
import com.dominionconsulting.tito.opp.dto.OppDetailDto;
import com.dominionconsulting.tito.opp.model.Opportunity;
import com.dominionconsulting.tito.opp.service.OppDetailService;

@WebAppConfiguration
@Transactional
public class OppDetailControllerTest extends AbstractTest {
	
	private MockMvc mvc;
	
	@Mock
	private OppDetailService service;
	
	@InjectMocks
	private OppDetailController controller;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.standaloneSetup(controller)
				.setControllerAdvice(new TitoExceptionHandler()).build();
	}
	
	@Test
	public void getOpp_ReceivesGetRequest_Returns200Status() throws Exception {
		OppDetailDto dto = getDtoStubData();
		
		when(service.getOne(dto.getId())).thenReturn(dto);
		
		String uri = "/opps/detail/" + dto.getId();
		
		MvcResult result = mvc.perform(get(uri)).andReturn();
		
		String content = result.getResponse().getContentAsString();
		int status = result.getResponse().getStatus();

		Assert.assertEquals("failure - expected HTTP status 200", 200, status);
		Assert.assertTrue("failure - expected HTTP response body to have a value",
				content.trim().length() > 0);
		
		OppDetailDto returnedDto = mapFromJson(content, OppDetailDto.class);
		
		Assert.assertEquals("failure - expected id attribute match", dto.getId(),
				returnedDto.getId());
	}
	
	@Test
	public void getOpp_ReceivesGetRequest_Returns404Status() throws Exception {
		int id = 1;
		
		when(service.getOne(id)).thenThrow(new NoResultException());
		
		String uri = "/opps/detail/" + id;
		
		MvcResult result = mvc.perform(get(uri)).andReturn();
		
		int status = result.getResponse().getStatus();

		Assert.assertEquals("failure - expected HTTP status 404", 404, status);
	}
	
	@Test
	public void updateOpp_ReceivesPutRequest_Returns200Status() throws Exception {
		OppDetailDto dto = getDtoStubData();
		
		when(service.update(any(OppDetailDto.class))).thenReturn(dto);
		
		String uri = "/opps/detail/";
		String inputJson = mapToJson(dto);
		
		MvcResult result = mvc.perform(put(uri)
				.contentType(MediaType.APPLICATION_JSON).content(inputJson))
				.andReturn();
		
		String content = result.getResponse().getContentAsString();
		int status = result.getResponse().getStatus();
		
		Assert.assertEquals("failure - expected HTTP status 200", 200, status);
		Assert.assertTrue("failure - expected HTTP response body to have a value",
				content.trim().length() > 0);
		
		OppDetailDto returnedDto = mapFromJson(content, OppDetailDto.class);
		
		Assert.assertEquals("failure - expected id attribute match", dto.getId(),
				returnedDto.getId());
		Assert.assertEquals("failure - expected name attribute match",
				dto.getSummary().getName(), returnedDto.getSummary().getName());
	}
	
	@Test
	public void updateOpp_ReceivesPutRequest_Returns404Status() throws Exception {
		OppDetailDto dto = getDtoStubData();
		
		when(service.update(any(OppDetailDto.class)))
				.thenThrow(new NoResultException());
		
		String uri = "/opps/detail/";
		String inputJson = mapToJson(dto);
		
		MvcResult result = mvc.perform(put(uri)
				.contentType(MediaType.APPLICATION_JSON).content(inputJson))
				.andReturn();
		
		int status = result.getResponse().getStatus();
		
		Assert.assertEquals("failure - expected HTTP status 404", 404, status);
	}
	
	@Test
	public void createOppPartner_ReceivesPostRequest_Returns201Status()
			throws Exception {
		OppDetailDto dto = getDtoWithPartnerStubData();
		Integer oppId = dto.getId();
		Integer firmId = dto.getLandscape().getPartners().get(0).getFirm().getId();
		
		when(service.createOppPartner(oppId, firmId)).thenReturn(dto);
		
		String uri = "/opps/detail/" + oppId + "/partner/" + firmId;
		
		MvcResult result = mvc.perform(post(uri)).andReturn();
		
		String content = result.getResponse().getContentAsString();
		int status = result.getResponse().getStatus();
		
		Assert.assertEquals("failure - expected HTTP status 201", 201, status);
		Assert.assertTrue("failure - expected HTTP response body to have a value",
				content.trim().length() > 0);
		
		OppDetailDto returnedDto = mapFromJson(content, OppDetailDto.class);
		
		Assert.assertEquals("failure - expected id attribute match", oppId,
				returnedDto.getId());
		Assert.assertEquals("failure - expected firm id attribute match", firmId,
				returnedDto.getLandscape().getPartners().get(0).getFirm().getId());
	}
	
	@Test
	public void createOppPartner_ReceivesPostRequest_Returns404Status()
			throws Exception {
		Integer oppId = 1;
		Integer firmId = 1;
		
		when(service.createOppPartner(oppId, firmId))
				.thenThrow(new NoResultException());
		
		String uri = "/opps/detail/" + oppId + "/partner/" + firmId;
		
		MvcResult result = mvc.perform(post(uri)).andReturn();
		
		int status = result.getResponse().getStatus();
		
		Assert.assertEquals("failure - expected HTTP status 404", 404, status);
	}
	
	@Test
	public void createOppCompetitor_ReceivesPostRequest_Returns201Status()
			throws Exception {
		OppDetailDto dto = getDtoWithCompetitorStubData();
		Integer oppId = dto.getId();
		Integer firmId = dto.getLandscape().getCompetitors().get(0).getFirm().getId();
		
		when(service.createOppCompetitor(oppId, firmId)).thenReturn(dto);
		
		String uri = "/opps/detail/" + oppId + "/competitor/" + firmId;
		
		MvcResult result = mvc.perform(post(uri)).andReturn();
		
		String content = result.getResponse().getContentAsString();
		int status = result.getResponse().getStatus();
		
		Assert.assertEquals("failure - expected HTTP status 201", 201, status);
		Assert.assertTrue("failure - expected HTTP response body to have a value",
				content.trim().length() > 0);
		
		OppDetailDto returnedDto = mapFromJson(content, OppDetailDto.class);
		
		Assert.assertEquals("failure - expected id attribute match", oppId,
				returnedDto.getId());
		Assert.assertEquals("failure - expected firm id attribute match", firmId,
				returnedDto.getLandscape().getCompetitors().get(0).getFirm().getId());
	}
	
	@Test
	public void createOppCompetitor_ReceivesPostRequest_Returns404Status()
			throws Exception {
		Integer oppId = 1;
		Integer firmId = 1;
		
		when(service.createOppCompetitor(oppId, firmId))
				.thenThrow(new NoResultException());
		
		String uri = "/opps/detail/" + oppId + "/competitor/" + firmId;
		
		MvcResult result = mvc.perform(post(uri)).andReturn();
		
		int status = result.getResponse().getStatus();
		
		Assert.assertEquals("failure - expected HTTP status 404", 404, status);
	}
	
	@Test
	public void deleteOppPartner_ReceivesDeleteRequest_Returns200Status()
			throws Exception {
		OppDetailDto dtoNoPartner = getDtoStubData();
		Opportunity oppWithPartner = getOppWithPartnerStubData();
		Integer oppId = oppWithPartner.getId();
		Integer partnerId = oppWithPartner.getOpportunityPartners().get(0).getId();
		
		when(service.deleteOppPartner(oppId, partnerId)).thenReturn(dtoNoPartner);
		
		String uri = "/opps/detail/" + oppId + "/partner/" + partnerId;
		
		MvcResult result = mvc.perform(delete(uri)).andReturn();
		
		String content = result.getResponse().getContentAsString();
		int status = result.getResponse().getStatus();
		
		Assert.assertEquals("failure - expected HTTP status 200", 200, status);
		Assert.assertTrue("failure - expected HTTP response body to have a value",
				content.trim().length() > 0);
		
		OppDetailDto returnedDto = mapFromJson(content, OppDetailDto.class);
		
		Assert.assertEquals("failure - expected id attribute match", oppId,
				returnedDto.getId());
		Assert.assertTrue("failure - expected partner list to be empty",
				returnedDto.getLandscape().getPartners().isEmpty());
	}
	
	@Test
	public void deleteOppPartner_ReceivesDeleteRequest_Returns404Status()
			throws Exception {
		Integer oppId = 1;
		Integer partnerId = 1;
		
		when(service.deleteOppPartner(oppId, partnerId))
				.thenThrow(new NoResultException());
		
		String uri = "/opps/detail/" + oppId + "/partner/" + partnerId;
		
		MvcResult result = mvc.perform(delete(uri)).andReturn();
		
		int status = result.getResponse().getStatus();
		
		Assert.assertEquals("failure - expected HTTP status 404", 404, status);
	}
	
	@Test
	public void deleteOppCompetitor_ReceivesDeleteRequest_Returns200Status()
			throws Exception {
		OppDetailDto dtoNoCompetitor = getDtoStubData();
		Opportunity oppWithCompetitor = getOppWithCompetitorStubData();
		Integer oppId = oppWithCompetitor.getId();
		Integer competitorId =
				oppWithCompetitor.getOpportunityCompetitors().get(0).getId();
		
		when(service.deleteOppCompetitor(oppId, competitorId))
				.thenReturn(dtoNoCompetitor);
		
		String uri = "/opps/detail/" + oppId + "/competitor/" + competitorId;
		
		MvcResult result = mvc.perform(delete(uri)).andReturn();
		
		String content = result.getResponse().getContentAsString();
		int status = result.getResponse().getStatus();
		
		Assert.assertEquals("failure - expected HTTP status 200", 200, status);
		Assert.assertTrue("failure - expected HTTP response body to have a value",
				content.trim().length() > 0);
		
		OppDetailDto returnedDto = mapFromJson(content, OppDetailDto.class);
		
		Assert.assertEquals("failure - expected id attribute match", oppId,
				returnedDto.getId());
		Assert.assertTrue("failure - expected competitor list to be empty",
				returnedDto.getLandscape().getCompetitors().isEmpty());
	}
	
	@Test
	public void deleteOppCompetitor_ReceivesDeleteRequest_Returns404Status()
			throws Exception {
		Integer oppId = 1;
		Integer competitorId = 1;
		
		when(service.deleteOppCompetitor(oppId, competitorId))
				.thenThrow(new NoResultException());
		
		String uri = "/opps/detail/" + oppId + "/competitor/" + competitorId;
		
		MvcResult result = mvc.perform(delete(uri)).andReturn();
		
		int status = result.getResponse().getStatus();
		
		Assert.assertEquals("failure - expected HTTP status 404", 404, status);
	}
	
	public void createOppEvent_ReceivesPostRequest_Returns201Status() throws Exception {
		OppDetailDto dto = getDtoWithEventStubData();
		Integer oppId = dto.getId();
		
		when(service.createOppEvent(oppId)).thenReturn(dto);
		
		String uri = "/opps/detail/" + oppId + "/event/";
		
		MvcResult result = mvc.perform(post(uri)).andReturn();
		
		String content = result.getResponse().getContentAsString();
		int status = result.getResponse().getStatus();
		
		Assert.assertEquals("failure - expected HTTP status 201", 201, status);
		Assert.assertTrue("failure - expected HTTP response body to have a value",
				content.trim().length() > 0);
		
		OppDetailDto returnedDto = mapFromJson(content, OppDetailDto.class);
		
		Assert.assertEquals("failure - expected id attribute match", oppId,
				returnedDto.getId());
		Assert.assertEquals("failure - expected event size 1", 1,
				returnedDto.getCaptureActivities().getActivities().size());
	}
	
	public void createOppEvent_ReceivesPostRequest_Returns404Status() throws Exception {
		Integer oppId = 5;
		
		when(service.createOppEvent(oppId)).thenThrow(new NoResultException());
		
		String uri = "/opps/detail/" + oppId + "/event/";
		
		MvcResult result = mvc.perform(post(uri)).andReturn();
		
		int status = result.getResponse().getStatus();
		
		Assert.assertEquals("failure - expected HTTP status 404", 404, status);
	}
	
	public void deleteOppEvent_ReceivesDeleteRequest_Returns200Status() throws Exception {
		OppDetailDto dtoWithEvent = getDtoWithEventStubData();
		OppDetailDto dtoNoEvent = getDtoStubData();
		Integer oppId = dtoWithEvent.getId();
		Integer eventId =
				dtoWithEvent.getCaptureActivities().getActivities().get(0).getId();
		
		when(service.deleteOppEvent(oppId, eventId)).thenReturn(dtoNoEvent);
		
		String uri = "/opps/detail/" + oppId + "/event/" + eventId;
		
		MvcResult result = mvc.perform(delete(uri)).andReturn();
		
		String content = result.getResponse().getContentAsString();
		int status = result.getResponse().getStatus();
		
		Assert.assertEquals("failure - expected HTTP status 200", 200, status);
		Assert.assertTrue("failure - expected HTTP response body to have a value",
				content.trim().length() > 0);
		
		OppDetailDto returnedDto = mapFromJson(content, OppDetailDto.class);
		
		Assert.assertEquals("failure - expected id attribute match", oppId,
				returnedDto.getId());
		Assert.assertEquals("failure - expected event size 0", 0,
				returnedDto.getCaptureActivities().getActivities().size());
	}
	
	public void deleteOppEvent_ReceivesDeleteRequest_Returns404Status() throws Exception {
		OppDetailDto dtoWithEvent = getDtoWithEventStubData();
		Integer oppId = dtoWithEvent.getId();
		Integer eventId =
				dtoWithEvent.getCaptureActivities().getActivities().get(0).getId();
		
		when(service.deleteOppEvent(oppId, eventId)).thenThrow(new NoResultException());
		
		String uri = "/opps/detail/" + oppId + "/event/" + eventId;
		
		MvcResult result = mvc.perform(delete(uri)).andReturn();
		
		int status = result.getResponse().getStatus();
		
		Assert.assertEquals("failure - expected HTTP status 404", 404, status);
	}

	private OppDetailDto getDtoStubData() {
		Opportunity opp = getOppWithIdStubData();
		return new OppDetailDto(opp);
	}
	
	private OppDetailDto getDtoWithPartnerStubData() {
		Opportunity opp = getOppWithPartnerStubData();
		return new OppDetailDto(opp);
	}
	
	private OppDetailDto getDtoWithCompetitorStubData() {
		Opportunity opp = getOppWithCompetitorStubData();
		return new OppDetailDto(opp);
	}
	
	private OppDetailDto getDtoWithEventStubData() {
		Opportunity opp = getOppWithEventStubData();
		return new OppDetailDto(opp);
	}

}
