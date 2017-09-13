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

public class OppNewServiceTest extends AbstractTest {
	
	@Mock
	private SubClientRepository subClientRepository;
	
	@Mock
	private ClientRepository clientRepository;
	
	@Mock
	private AccountRepository accountRepository;
	
	@Mock
	private PersonRepository personRepository;
	
	@Mock
	private SolutionRepository solutionRepository;
	
	@Mock
	private OpportunityRepository oppRepository;
	
	@Mock
	private OpportunityTimelineRepository oppTimelineRepository;
	
	@Mock
	private OpportunityNoteRepository oppNoteRepository;
	
	@Mock
	private OpportunitySolutionRepository oppSolutionRepository;
	
	@InjectMocks
	private OppNewService service;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getInfo_ReturnsOppNewDto() {
		List<SubClient> subClients = getSubClientListStubData();
		
		when(subClientRepository.findAll()).thenReturn(subClients);
		when(clientRepository.findAll()).thenReturn(new ArrayList<Client>());
		when(accountRepository.findAll()).thenReturn(new ArrayList<Account>());
		when(personRepository.findAll()).thenReturn(new ArrayList<Person>());
		when(solutionRepository.findAll()).thenReturn(new ArrayList<Solution>());
		
		OppNewDto dto = service.getInfo();
		
		Assert.assertNotNull("failure - expected not null", dto);
		Assert.assertEquals("failure - expected id attribute match",
				Long.parseLong(subClients.get(0).getId().toString()),
				Long.parseLong(dto.getSummary().getAccounts().get(0).getClients().get(0)
						.getSubClients().get(0).getId().toString()));
	}
	
	@Test
	public void getInfo_ReceivesNoSubClients_ThrowsNoResultException() {
		Exception e = null;
		
		when(subClientRepository.findAll()).thenReturn(null);
		
		try {
			service.getInfo();
		} catch (Exception ex) {
			e = ex;
		}
		
		Assert.assertNotNull("failure - expected exception", e);
		Assert.assertTrue("failure - expects NoResultException",
				e instanceof NoResultException);
	}
	
	@Test
	public void getInfo_ReceivesNoClients_ThrowsNoResultException() {
		Exception e = null;
		List<SubClient> subClients = getSubClientListStubData();
		
		when(subClientRepository.findAll()).thenReturn(subClients);
		when(clientRepository.findAll()).thenReturn(null);
		
		try {
			service.getInfo();
		} catch (Exception ex) {
			e = ex;
		}
		
		Assert.assertNotNull("failure - expected exception", e);
		Assert.assertTrue("failure - expects NoResultException",
				e instanceof NoResultException);
	}
	
	@Test
	public void getInfo_ReceivesNoAccounts_ThrowsNoResultException() {
		Exception e = null;
		List<SubClient> subClients = getSubClientListStubData();
		
		when(subClientRepository.findAll()).thenReturn(subClients);
		when(clientRepository.findAll()).thenReturn(new ArrayList<Client>());
		when(accountRepository.findAll()).thenReturn(null);
		
		try {
			service.getInfo();
		} catch (Exception ex) {
			e = ex;
		}
		
		Assert.assertNotNull("failure - expected exception", e);
		Assert.assertTrue("failure - expects NoResultException",
				e instanceof NoResultException);
	}
	
	@Test
	public void getInfo_ReceivesNoPeople_ThrowsNoResultException() {
		Exception e = null;
		List<SubClient> subClients = getSubClientListStubData();
		
		when(subClientRepository.findAll()).thenReturn(subClients);
		when(clientRepository.findAll()).thenReturn(new ArrayList<Client>());
		when(accountRepository.findAll()).thenReturn(new ArrayList<Account>());
		when(personRepository.findAll()).thenReturn(null);
		
		try {
			service.getInfo();
		} catch (Exception ex) {
			e = ex;
		}
		
		Assert.assertNotNull("failure - expected exception", e);
		Assert.assertTrue("failure - expects NoResultException",
				e instanceof NoResultException);
	}
	
	@Test
	public void getInfo_ReceivesNoSolutions_ThrowsNoResultException() {
		Exception e = null;
		List<SubClient> subClients = getSubClientListStubData();
		
		when(subClientRepository.findAll()).thenReturn(subClients);
		when(clientRepository.findAll()).thenReturn(new ArrayList<Client>());
		when(accountRepository.findAll()).thenReturn(new ArrayList<Account>());
		when(personRepository.findAll()).thenReturn(new ArrayList<Person>());
		when(solutionRepository.findAll()).thenReturn(null);
		
		try {
			service.getInfo();
		} catch (Exception ex) {
			e = ex;
		}
		
		Assert.assertNotNull("failure - expected exception", e);
		Assert.assertTrue("failure - expects NoResultException",
				e instanceof NoResultException);
	}
	
	@Test
	public void create_ReceivesOppNewDto_ReturnsOppNewDto() {
		Opportunity opp = getOppWithIdStubData();
		OppNewDto dto = getDtoNoIdStubData();
		
		when(oppRepository.save(any(Opportunity.class))).thenReturn(opp);
		
		Integer id = service.create(dto);
		
		Assert.assertNotNull("failure - expected not null", id);
		Assert.assertEquals("failure - expected id attribute match", opp.getId(), id);
	}
	
	@Test
	public void create_ReceivesOppNewDto_ThrowsEntityExistsException() {
		Exception e = null;
		OppNewDto dto = getDtoWithIdStubData();
		
		try {
			service.create(dto);
		} catch (Exception ex) {
			e = ex;
		}
		
		Assert.assertNotNull("failure - expected exception", e);
		Assert.assertTrue("failure - expects EntityExistsException",
				e instanceof EntityExistsException);
	}
	
	@Test
	public void create_ReceivesOppNewDto_ThrowsTitoException() {
		Exception e = null;
		OppNewDto dto = getDtoNoIdStubData();
		
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
	public void getOne_ReceivesId_ReturnsOppNewDto() {
		Opportunity opp = getOppWithIdStubData();
		SubClient subClient = getSubClientStubData();
		
		when(oppRepository.findOne(opp.getId())).thenReturn(opp);
		when(subClientRepository.findOne(opp.getSubClientBean().getId()))
				.thenReturn(subClient);
		when(oppTimelineRepository.findByOpportunityId(opp.getId()))
				.thenReturn(new ArrayList<OpportunityTimeline>());
		when(oppNoteRepository.findByOpportunityId(opp.getId()))
				.thenReturn(new ArrayList<OpportunityNote>());
		when(oppSolutionRepository.findByOpportunityId(opp.getId()))
				.thenReturn(new ArrayList<OpportunitySolution>());
		
		OppNewDto dto = service.getOne(opp.getId());
		
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
	
	private OppNewDto getDtoNoIdStubData() {
		Opportunity opp = getOppNoIdStubData();
		return new OppNewDto(opp);
	}
	
	private OppNewDto getDtoWithIdStubData() {
		Opportunity opp = getOppWithIdStubData();
		return new OppNewDto(opp);
	}

}
