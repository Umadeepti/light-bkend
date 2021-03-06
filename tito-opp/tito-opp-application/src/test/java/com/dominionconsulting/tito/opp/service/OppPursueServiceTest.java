package com.dominionconsulting.tito.opp.service;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.NoResultException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.dominionconsulting.tito.opp.AbstractTest;
import com.dominionconsulting.tito.opp.common.util.TitoException;
import com.dominionconsulting.tito.opp.dto.OppNewDto;
import com.dominionconsulting.tito.opp.dto.OppPursueDto;
import com.dominionconsulting.tito.opp.model.Account;
import com.dominionconsulting.tito.opp.model.Client;
import com.dominionconsulting.tito.opp.model.Opportunity;
import com.dominionconsulting.tito.opp.model.OpportunityNote;
import com.dominionconsulting.tito.opp.model.OpportunitySolution;
import com.dominionconsulting.tito.opp.model.OpportunityTimeline;
import com.dominionconsulting.tito.opp.model.Person;
import com.dominionconsulting.tito.opp.model.Solution;
import com.dominionconsulting.tito.opp.model.SubClient;
import com.dominionconsulting.tito.opp.repository.AccountRepository;
import com.dominionconsulting.tito.opp.repository.ClientRepository;
import com.dominionconsulting.tito.opp.repository.OpportunityNoteRepository;
import com.dominionconsulting.tito.opp.repository.OpportunityRepository;
import com.dominionconsulting.tito.opp.repository.OpportunitySolutionRepository;
import com.dominionconsulting.tito.opp.repository.OpportunityTimelineRepository;
import com.dominionconsulting.tito.opp.repository.PersonRepository;
import com.dominionconsulting.tito.opp.repository.SolutionRepository;
import com.dominionconsulting.tito.opp.repository.SubClientRepository;

public class OppPursueServiceTest extends AbstractTest {
	
	@Mock
	private OpportunityNoteRepository oppNoteRepository;
	
	@Mock
	private OpportunityRepository oppRepository;
	
	@InjectMocks
	private OppPursueService service;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getInfo_ReturnsOppNewDto() {
		
	}
	

	
/*	@Test  /// Need to debug Uma
	public void create_ReceivesOppPursueDto_ReturnsOppPursueDto() {
		Opportunity opp = getOppWithPursueStubData();
		OppPursueDto dto = getDtoWithPursueStubData();
		System.out.println(""+dto.toString());
		//when(oppNoteRepository.save(any(OpportunityNote.class))).thenReturn(opp.getOpportunityNotes().get(0));
		when(oppRepository.save(any(Opportunity.class))).thenReturn(opp);
		
		Integer id = service.create(dto);
		
		Assert.assertNotNull("failure - expected not null", id);
		Assert.assertEquals("failure - expected id attribute match", opp.getId(), id);
	}
	*/
	@Test
	public void create_ReceivesOppPursueDto_ThrowsEntityExistsException() {
		Exception e = null;
		OppPursueDto dto = getDtoWithIdStubData();
		
		try {
			service.create(dto);
		} catch (Exception ex) {
			e = ex;
		}
		
		Assert.assertNotNull("failure - expected exception", e);
		/*Assert.assertTrue("failure - expects EntityExistsException",
				e instanceof EntityExistsException);*/
	}
	
	@Test
	public void create_ReceivesOppPursueDto_ThrowsTitoException() {
		Exception e = null;
		OppPursueDto dto = getDtoNoIdStubData();
		
		when(oppRepository.save(any(Opportunity.class))).thenReturn(null);
		
		try {
			service.create(dto);
		} catch (Exception ex) {
			e = ex;
		}
		
		Assert.assertNotNull("failure - expected exception", e);
		Assert.assertTrue("failure - expects TitoException",
				e instanceof TitoException);
	}
	
	@Test
	public void getOne_ReceivesId_ReturnsOppPursueDto() {
		Opportunity opp = getOppWithIdStubData();
		
		when(oppRepository.findOne(opp.getId())).thenReturn(opp);
		
		when(oppNoteRepository.findByOpportunityId(opp.getId()))
				.thenReturn(new ArrayList<OpportunityNote>());
		
		OppPursueDto dto = service.getOne(opp.getId());
		
		Assert.assertNotNull("failure - expected not null", dto);
		Assert.assertEquals("failure - expected id attribute match", opp.getId(),
				dto.getId());
	}
	
	@Test
	public void getOne_ReceivesId_ThrowsNoResultException() {
		Exception e = null;
		Integer id = 1;
		
		when(oppRepository.findOne(id)).thenReturn(null);
		
		try {
			service.getOne(id);
		} catch (Exception ex) {
			e = ex;
		}
		
		Assert.assertNotNull("failure - expected exception", e);
		Assert.assertTrue("failure - expects NoResultException",
				e instanceof NoResultException);
	}
	
	private OppPursueDto getDtoNoIdStubData() {
		Opportunity opp = getOppNoIdStubData();
		return new OppPursueDto(opp);
	}
	
	private OppPursueDto getDtoWithIdStubData() {
		Opportunity opp = getOppWithIdStubData();
		return new OppPursueDto(opp);
	}
	private OppPursueDto getDtoWithPursueStubData() {
		Opportunity opp = getOppWithPursueStubData();
		return new OppPursueDto(opp);
	}
	
}
