package com.dominionconsulting.tito.opp.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import com.dominionconsulting.tito.opp.dto.OppNotesDto;
import com.dominionconsulting.tito.opp.model.Opportunity;
import com.dominionconsulting.tito.opp.model.OpportunityCompetitor;
import com.dominionconsulting.tito.opp.model.OpportunityEvent;
import com.dominionconsulting.tito.opp.model.OpportunityPartner;
import com.dominionconsulting.tito.opp.service.OppNotesService;

@WebAppConfiguration
@Transactional
public class OppNotesControllerTest extends AbstractTest {

	private MockMvc mvc;
	
	@Mock
	private OppNotesService service;
	
	@InjectMocks
	private OppNotesController controller;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.standaloneSetup(controller)
				.setControllerAdvice(new TitoExceptionHandler()).build();
	}
	
	@Test
	public void getPartnerNotes_ReceivesGetRequest_Returns200Status() throws Exception {
		Opportunity opp = getOppWithPartnerStubData();
		OpportunityPartner partner = opp.getOpportunityPartners().get(0);
		OppNotesDto dto = new OppNotesDto(partner.getDescription());
		
		when(service.getPartnerNotes(partner.getId())).thenReturn(dto);
		
		String uri = "/opps/notes/partner/" + partner.getId();
		
		MvcResult result = mvc.perform(get(uri)).andReturn();
		
		String content = result.getResponse().getContentAsString();
		int status = result.getResponse().getStatus();
		
		Assert.assertEquals("failure - expected HTTP status 200", 200, status);
		Assert.assertTrue("failure - expected HTTP response body to have a value",
				content.trim().length() > 0);
		
		OppNotesDto returnedDto = mapFromJson(content, OppNotesDto.class);
		
		Assert.assertEquals("failure - expected text attribute match", dto.getText(),
				returnedDto.getText());
	}
	
	@Test
	public void getPartnerNotes_ReceivesGetRequest_Returns404Status() throws Exception {
		Integer partnerId = 5;
		
		when(service.getPartnerNotes(partnerId)).thenThrow(new NoResultException());
		
		String uri = "/opps/notes/partner/" + partnerId;
		
		MvcResult result = mvc.perform(get(uri)).andReturn();
		
		int status = result.getResponse().getStatus();
		
		Assert.assertEquals("failure - expected HTTP status 404", 404, status);
	}
	
	@Test
	public void getCompetitorNotes_ReceivesGetRequest_Returns200Status()
			throws Exception {
		Opportunity opp = getOppWithCompetitorStubData();
		OpportunityCompetitor competitor = opp.getOpportunityCompetitors().get(0);
		OppNotesDto dto = new OppNotesDto(competitor.getNote());
		
		when(service.getCompetitorNotes(competitor.getId())).thenReturn(dto);
		
		String uri = "/opps/notes/competitor/" + competitor.getId();
		
		MvcResult result = mvc.perform(get(uri)).andReturn();
		
		String content = result.getResponse().getContentAsString();
		int status = result.getResponse().getStatus();
		
		Assert.assertEquals("failure - expected HTTP status 200", 200, status);
		Assert.assertTrue("failure - expected HTTP response body to have a value",
				content.trim().length() > 0);
		
		OppNotesDto returnedDto = mapFromJson(content, OppNotesDto.class);
		
		Assert.assertEquals("failure - expected text attribute match", dto.getText(),
				returnedDto.getText());
	}
	
	@Test
	public void getCompetitorNotes_ReceivesGetRequest_Returns404Status()
			throws Exception {
		Integer competitorId = 5;
		
		when(service.getCompetitorNotes(competitorId))
				.thenThrow(new NoResultException());
		
		String uri = "/opps/notes/competitor/" + competitorId;
		
		MvcResult result = mvc.perform(get(uri)).andReturn();
		
		int status = result.getResponse().getStatus();
		
		Assert.assertEquals("failure - expected HTTP status 404", 404, status);
	}
	
	@Test
	public void getEventNotes_ReceivesGetRequest_Returns200Status() throws Exception {
		Opportunity opp = getOppWithEventStubData();
		OpportunityEvent event = opp.getOpportunityEvents().get(0);
		OppNotesDto dto = new OppNotesDto(event.getNote());
		
		when(service.getEventNotes(event.getId())).thenReturn(dto);
		
		String uri = "/opps/notes/event/" + event.getId();
		
		MvcResult result = mvc.perform(get(uri)).andReturn();
		
		String content = result.getResponse().getContentAsString();
		int status = result.getResponse().getStatus();
		
		Assert.assertEquals("failure - expected HTTP status 200", 200, status);
		Assert.assertTrue("failure - expected HTTP response body to have a value",
				content.trim().length() > 0);
		
		OppNotesDto returnedDto = mapFromJson(content, OppNotesDto.class);
		
		Assert.assertEquals("failure - expected text attribute match", dto.getText(),
				returnedDto.getText());
	}
	
	@Test
	public void getEventNotes_ReceivesGetRequest_Returns404Status() throws Exception {
		Integer eventId = 5;
		
		when(service.getEventNotes(eventId)).thenThrow(new NoResultException());
		
		String uri = "/opps/notes/event/" + eventId;
		
		MvcResult result = mvc.perform(get(uri)).andReturn();
		
		int status = result.getResponse().getStatus();
		
		Assert.assertEquals("failure - expected HTTP status 404", 404, status);
	}
	
	@Test
	public void updatePartnerNotes_ReceivesGetRequest_Returns200Status()
			throws Exception {
		Opportunity opp = getOppWithPartnerStubData();
		OpportunityPartner partner = opp.getOpportunityPartners().get(0);
		OppNotesDto dto = new OppNotesDto(partner.getDescription());
		
		when(service.updatePartnerNotes(any(Integer.class), any(OppNotesDto.class)))
				.thenReturn(dto);
		
		String uri = "/opps/notes/partner/" + partner.getId();
		String inputJson = mapToJson(dto);
		
		MvcResult result = mvc.perform(put(uri)
				.contentType(MediaType.APPLICATION_JSON).content(inputJson))
				.andReturn();
		
		String content = result.getResponse().getContentAsString();
		int status = result.getResponse().getStatus();
		
		Assert.assertEquals("failure - expected HTTP status 200", 200, status);
		Assert.assertTrue("failure - expected HTTP response body to have a value",
				content.trim().length() > 0);
		
		OppNotesDto returnedDto = mapFromJson(content, OppNotesDto.class);
		
		Assert.assertEquals("failure - expected text attribute match", dto.getText(),
				returnedDto.getText());
	}
	
	@Test
	public void updatePartnerNotes_ReceivesGetRequest_Returns404Status()
			throws Exception {
		Opportunity opp = getOppWithPartnerStubData();
		OpportunityPartner partner = opp.getOpportunityPartners().get(0);
		OppNotesDto dto = new OppNotesDto(partner.getDescription());
		
		when(service.updatePartnerNotes(any(Integer.class), any(OppNotesDto.class)))
				.thenThrow(new NoResultException());
		
		String uri = "/opps/notes/partner/" + partner.getId();
		String inputJson = mapToJson(dto);
		
		MvcResult result = mvc.perform(put(uri)
				.contentType(MediaType.APPLICATION_JSON).content(inputJson))
				.andReturn();
		
		int status = result.getResponse().getStatus();
		
		Assert.assertEquals("failure - expected HTTP status 404", 404, status);
	}
	
	@Test
	public void updateCompetitorNotes_ReceivesGetRequest_Returns200Status()
			throws Exception {
		Opportunity opp = getOppWithCompetitorStubData();
		OpportunityCompetitor competitor = opp.getOpportunityCompetitors().get(0);
		OppNotesDto dto = new OppNotesDto(competitor.getNote());
		
		when(service.updateCompetitorNotes(any(Integer.class), any(OppNotesDto.class)))
				.thenReturn(dto);
		
		String uri = "/opps/notes/competitor/" + competitor.getId();
		String inputJson = mapToJson(dto);
		
		MvcResult result = mvc.perform(put(uri)
				.contentType(MediaType.APPLICATION_JSON).content(inputJson))
				.andReturn();
		
		String content = result.getResponse().getContentAsString();
		int status = result.getResponse().getStatus();
		
		Assert.assertEquals("failure - expected HTTP status 200", 200, status);
		Assert.assertTrue("failure - expected HTTP response body to have a value",
				content.trim().length() > 0);
		
		OppNotesDto returnedDto = mapFromJson(content, OppNotesDto.class);
		
		Assert.assertEquals("failure - expected text attribute match", dto.getText(),
				returnedDto.getText());
	}
	
	@Test
	public void updateCompetitorNotes_ReceivesGetRequest_Returns404Status()
			throws Exception {
		Opportunity opp = getOppWithCompetitorStubData();
		OpportunityCompetitor competitor = opp.getOpportunityCompetitors().get(0);
		OppNotesDto dto = new OppNotesDto(competitor.getNote());
		
		when(service.updateCompetitorNotes(any(Integer.class), any(OppNotesDto.class)))
				.thenThrow(new NoResultException());
		
		String uri = "/opps/notes/competitor/" + competitor.getId();
		String inputJson = mapToJson(dto);
		
		MvcResult result = mvc.perform(put(uri)
				.contentType(MediaType.APPLICATION_JSON).content(inputJson))
				.andReturn();
		
		int status = result.getResponse().getStatus();
		
		Assert.assertEquals("failure - expected HTTP status 404", 404, status);
	}
	
	@Test
	public void updateEventNotes_ReceivesGetRequest_Returns200Status()
			throws Exception {
		Opportunity opp = getOppWithEventStubData();
		OpportunityEvent event = opp.getOpportunityEvents().get(0);
		OppNotesDto dto = new OppNotesDto(event.getNote());
		
		when(service.updateEventNotes(any(Integer.class), any(OppNotesDto.class)))
				.thenReturn(dto);
		
		String uri = "/opps/notes/event/" + event.getId();
		String inputJson = mapToJson(dto);
		
		MvcResult result = mvc.perform(put(uri)
				.contentType(MediaType.APPLICATION_JSON).content(inputJson))
				.andReturn();
		
		String content = result.getResponse().getContentAsString();
		int status = result.getResponse().getStatus();
		
		Assert.assertEquals("failure - expected HTTP status 200", 200, status);
		Assert.assertTrue("failure - expected HTTP response body to have a value",
				content.trim().length() > 0);
		
		OppNotesDto returnedDto = mapFromJson(content, OppNotesDto.class);
		
		Assert.assertEquals("failure - expected text attribute match", dto.getText(),
				returnedDto.getText());
	}
	
	@Test
	public void updateEventNotes_ReceivesGetRequest_Returns404Status()
			throws Exception {
		Opportunity opp = getOppWithEventStubData();
		OpportunityEvent event = opp.getOpportunityEvents().get(0);
		OppNotesDto dto = new OppNotesDto(event.getNote());
		
		when(service.updateEventNotes(any(Integer.class), any(OppNotesDto.class)))
				.thenThrow(new NoResultException());
		
		String uri = "/opps/notes/event/" + event.getId();
		String inputJson = mapToJson(dto);
		
		MvcResult result = mvc.perform(put(uri)
				.contentType(MediaType.APPLICATION_JSON).content(inputJson))
				.andReturn();
		
		int status = result.getResponse().getStatus();
		
		Assert.assertEquals("failure - expected HTTP status 404", 404, status);
	}
	
}
