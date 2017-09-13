package com.dominionconsulting.tito.opp.service;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import javax.persistence.NoResultException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.dominionconsulting.tito.opp.AbstractTest;
import com.dominionconsulting.tito.opp.dto.OppNotesDto;
import com.dominionconsulting.tito.opp.model.Opportunity;
import com.dominionconsulting.tito.opp.model.OpportunityCompetitor;
import com.dominionconsulting.tito.opp.model.OpportunityEvent;
import com.dominionconsulting.tito.opp.model.OpportunityPartner;
import com.dominionconsulting.tito.opp.repository.OpportunityCompetitorRepository;
import com.dominionconsulting.tito.opp.repository.OpportunityEventRepository;
import com.dominionconsulting.tito.opp.repository.OpportunityPartnerRepository;

public class OppNotesServiceTest extends AbstractTest {
	
	@Mock
	private OpportunityPartnerRepository oppPartnerRepository;
	
	@Mock
	private OpportunityCompetitorRepository oppCompetitorRepository;
	
	@Mock
	private OpportunityEventRepository oppEventRepository;
	
	@InjectMocks
	private OppNotesService service;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getPartnerNotes_ReceivesId_ReturnsOppNotesDto() {
		Opportunity opp = getOppWithPartnerStubData();
		OpportunityPartner partner = opp.getOpportunityPartners().get(0);
		
		when(oppPartnerRepository.findOne(partner.getId())).thenReturn(partner);
		
		OppNotesDto dto = service.getPartnerNotes(partner.getId());
		
		Assert.assertNotNull("failure - expected not null", dto);
		Assert.assertEquals("failure - expected text attribute match",
				partner.getDescription(), dto.getText());
	}
	
	@Test
	public void getPartnerNotes_ReceivesId_ThrowsNoResultException() {
		Opportunity opp = getOppWithPartnerStubData();
		OpportunityPartner partner = opp.getOpportunityPartners().get(0);
		Exception e = null;
		
		when(oppPartnerRepository.findOne(partner.getId())).thenReturn(null);
		
		try {
			service.getPartnerNotes(partner.getId());
		} catch (Exception ex) {
			e = ex;
		}
		
		Assert.assertNotNull("failure - expected not null", e);
		Assert.assertTrue("failure - expected NoResultException",
				e instanceof NoResultException);
	}
	
	@Test
	public void getCompetitorNotes_ReceivesId_ReturnsOppNotesDto() {
		Opportunity opp = getOppWithCompetitorStubData();
		OpportunityCompetitor competitor = opp.getOpportunityCompetitors().get(0);
		
		when(oppCompetitorRepository.findOne(competitor.getId())).thenReturn(competitor);
		
		OppNotesDto dto = service.getCompetitorNotes(competitor.getId());
		
		Assert.assertNotNull("failure - expected not null", dto);
		Assert.assertEquals("failure - expected text attribute match",
				competitor.getNote(), dto.getText());
	}
	
	@Test
	public void getCompetitorNotes_ReceivesId_ThrowsNoResultException() {
		Opportunity opp = getOppWithCompetitorStubData();
		OpportunityCompetitor competitor = opp.getOpportunityCompetitors().get(0);
		Exception e = null;
		
		when(oppCompetitorRepository.findOne(competitor.getId())).thenReturn(null);
		
		try {
			service.getCompetitorNotes(competitor.getId());
		} catch (Exception ex) {
			e = ex;
		}
		
		Assert.assertNotNull("failure - expected not null", e);
		Assert.assertTrue("failure - expected NoResultException",
				e instanceof NoResultException);
	}
	
	@Test
	public void getEventNotes_ReceivesId_ReturnsOppNotesDto() {
		Opportunity opp = getOppWithEventStubData();
		OpportunityEvent event = opp.getOpportunityEvents().get(0);
		
		when(oppEventRepository.findOne(event.getId())).thenReturn(event);
		
		OppNotesDto dto = service.getEventNotes(event.getId());
		
		Assert.assertNotNull("failure - expected not null", dto);
		Assert.assertEquals("failure - expected text attribute match",
				event.getNote(), dto.getText());
	}
	
	@Test
	public void getEventNotes_ReceivesId_ThrowsNoResultException() {
		Opportunity opp = getOppWithEventStubData();
		OpportunityEvent event = opp.getOpportunityEvents().get(0);
		Exception e = null;
		
		when(oppEventRepository.findOne(event.getId())).thenReturn(null);
		
		try {
			service.getEventNotes(event.getId());
		} catch (Exception ex) {
			e = ex;
		}
		
		Assert.assertNotNull("failure - expected not null", e);
		Assert.assertTrue("failure - expected NoResultException",
				e instanceof NoResultException);
	}
	
	@Test
	public void updatePartnerNotes_ReceivesId_ReturnsOppNotesDto() {
		Opportunity opp = getOppWithPartnerStubData();
		OpportunityPartner partner = opp.getOpportunityPartners().get(0);
		OppNotesDto dto = new OppNotesDto(partner.getDescription());
		
		when(oppPartnerRepository.findOne(partner.getId())).thenReturn(partner);
		when(oppPartnerRepository.save(any(OpportunityPartner.class)))
				.thenReturn(partner);
		
		OppNotesDto returnedDto = service.updatePartnerNotes(partner.getId(), dto);
		
		Assert.assertNotNull("failure - expected not null", returnedDto);
		Assert.assertEquals("failure - expected text attribute match",
				dto.getText(), returnedDto.getText());
	}
	
	@Test
	public void updatePartnerNotes_ReceivesId_ThrowsNoResultException() {
		Opportunity opp = getOppWithPartnerStubData();
		OpportunityPartner partner = opp.getOpportunityPartners().get(0);
		OppNotesDto dto = new OppNotesDto(partner.getDescription());
		Exception e = null;
		
		when(oppPartnerRepository.findOne(partner.getId())).thenReturn(null);
		
		try {
			service.updatePartnerNotes(partner.getId(), dto);
		} catch (Exception ex) {
			e = ex;
		}
		
		Assert.assertNotNull("failure - expected not null", e);
		Assert.assertTrue("failure - expected NoResultException",
				e instanceof NoResultException);
	}
	
	@Test
	public void updateCompetitorNotes_ReceivesId_ReturnsOppNotesDto() {
		Opportunity opp = getOppWithCompetitorStubData();
		OpportunityCompetitor competitor = opp.getOpportunityCompetitors().get(0);
		OppNotesDto dto = new OppNotesDto(competitor.getNote());
		
		when(oppCompetitorRepository.findOne(competitor.getId()))
				.thenReturn(competitor);
		when(oppCompetitorRepository.save(any(OpportunityCompetitor.class)))
				.thenReturn(competitor);
		
		OppNotesDto returnedDto =
				service.updateCompetitorNotes(competitor.getId(), dto);
		
		Assert.assertNotNull("failure - expected not null", returnedDto);
		Assert.assertEquals("failure - expected text attribute match",
				dto.getText(), returnedDto.getText());
	}
	
	@Test
	public void updateCompetitorNotes_ReceivesId_ThrowsNoResultException() {
		Opportunity opp = getOppWithCompetitorStubData();
		OpportunityCompetitor competitor = opp.getOpportunityCompetitors().get(0);
		OppNotesDto dto = new OppNotesDto(competitor.getNote());
		Exception e = null;
		
		when(oppCompetitorRepository.findOne(competitor.getId())).thenReturn(null);
		
		try {
			service.updateCompetitorNotes(competitor.getId(), dto);
		} catch (Exception ex) {
			e = ex;
		}
		
		Assert.assertNotNull("failure - expected not null", e);
		Assert.assertTrue("failure - expected NoResultException",
				e instanceof NoResultException);
	}
	
	@Test
	public void updateEventNotes_ReceivesId_ReturnsOppNotesDto() {
		Opportunity opp = getOppWithEventStubData();
		OpportunityEvent event = opp.getOpportunityEvents().get(0);
		OppNotesDto dto = new OppNotesDto(event.getNote());
		
		when(oppEventRepository.findOne(event.getId())).thenReturn(event);
		when(oppEventRepository.save(any(OpportunityEvent.class))).thenReturn(event);
		
		OppNotesDto returnedDto = service.updateEventNotes(event.getId(), dto);
		
		Assert.assertNotNull("failure - expected not null", returnedDto);
		Assert.assertEquals("failure - expected text attribute match",
				dto.getText(), returnedDto.getText());
	}
	
	@Test
	public void updateEventNotes_ReceivesId_ThrowsNoResultException() {
		Opportunity opp = getOppWithEventStubData();
		OpportunityEvent event = opp.getOpportunityEvents().get(0);
		OppNotesDto dto = new OppNotesDto(event.getNote());
		Exception e = null;
		
		when(oppEventRepository.findOne(event.getId())).thenReturn(null);
		
		try {
			service.updateEventNotes(event.getId(), dto);
		} catch (Exception ex) {
			e = ex;
		}
		
		Assert.assertNotNull("failure - expected not null", e);
		Assert.assertTrue("failure - expected NoResultException",
				e instanceof NoResultException);
	}
	
}
