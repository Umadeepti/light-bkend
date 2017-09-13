package com.dominionconsulting.tito.opp.service;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.dominionconsulting.tito.opp.AbstractTest;
import com.dominionconsulting.tito.opp.dto.OppDetailDto;
import com.dominionconsulting.tito.opp.model.Account;
import com.dominionconsulting.tito.opp.model.Client;
import com.dominionconsulting.tito.opp.model.Firm;
import com.dominionconsulting.tito.opp.model.Opportunity;
import com.dominionconsulting.tito.opp.model.OpportunityCompetitor;
import com.dominionconsulting.tito.opp.model.OpportunityEvent;
import com.dominionconsulting.tito.opp.model.OpportunityPartner;
import com.dominionconsulting.tito.opp.model.OpportunitySolution;
import com.dominionconsulting.tito.opp.model.Person;
import com.dominionconsulting.tito.opp.model.Solution;
import com.dominionconsulting.tito.opp.model.SubClient;
import com.dominionconsulting.tito.opp.repository.AccountRepository;
import com.dominionconsulting.tito.opp.repository.ClientRepository;
import com.dominionconsulting.tito.opp.repository.FirmRepository;
import com.dominionconsulting.tito.opp.repository.OpportunityCompetitorRepository;
import com.dominionconsulting.tito.opp.repository.OpportunityEventRepository;
import com.dominionconsulting.tito.opp.repository.OpportunityIncumbentRepository;
import com.dominionconsulting.tito.opp.repository.OpportunityNoteRepository;
import com.dominionconsulting.tito.opp.repository.OpportunityPartnerRepository;
import com.dominionconsulting.tito.opp.repository.OpportunityRepository;
import com.dominionconsulting.tito.opp.repository.OpportunitySolutionRepository;
import com.dominionconsulting.tito.opp.repository.OpportunityTimelineRepository;
import com.dominionconsulting.tito.opp.repository.PersonRepository;
import com.dominionconsulting.tito.opp.repository.SolutionRepository;
import com.dominionconsulting.tito.opp.repository.SubClientRepository;

public class OppDetailServiceTest extends AbstractTest {

	@Mock
	private OpportunityRepository oppRepository;

	@Mock
	private SubClientRepository subClientRepository;

	@Mock
	private ClientRepository clientRepository;

	@Mock
	private AccountRepository accountRepository;

	@Mock
	private PersonRepository personRepository;

	@Mock
	private OpportunityTimelineRepository oppTimelineRepository;

	@Mock
	private OpportunityNoteRepository oppNoteRepository;

	@Mock
	private OpportunitySolutionRepository oppSolutionRepository;

	@Mock
	private SolutionRepository solutionRepository;

	@Mock
	private FirmRepository firmRepository;

	@Mock
	private OpportunityPartnerRepository oppPartnerRepository;

	@Mock
	private OpportunityCompetitorRepository oppCompetitorRepository;
	
	@Mock
	private OpportunityIncumbentRepository oppIncumbentRepository;
	
	@Mock
	private OpportunityEventRepository oppEventRepository;

	@InjectMocks
	private OppDetailService service;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getOne_ReceivesId_ReturnsOppDetailDto() {
		Opportunity opp = getOppWithIdStubData();
		List<SubClient> subClients = getSubClientListStubData();

		when(oppRepository.findOne(opp.getId())).thenReturn(opp);
		when(subClientRepository.findAll()).thenReturn(subClients);
		when(clientRepository.findAll()).thenReturn(new ArrayList<Client>());
		when(accountRepository.findAll()).thenReturn(new ArrayList<Account>());
		when(personRepository.findAll()).thenReturn(new ArrayList<Person>());
		when(solutionRepository.findAll()).thenReturn(new ArrayList<Solution>());
		when(firmRepository.findAll()).thenReturn(new ArrayList<Firm>());

		OppDetailDto dto = service.getOne(opp.getId());

		Assert.assertNotNull("failure - expected not null", dto);
		Assert.assertEquals("failure - expected id attribute match", opp.getId(),
				dto.getId());
	}

	@Test
	public void getOne_ReceivesIdNoOpp_ThrowsNoResultException() {
		int id = 1;
		Exception e = null;

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

	@Test
	public void getOne_ReceivesIdNoSubClients_ThrowsNoResultException() {
		Opportunity opp = getOppWithIdStubData();
		Exception e = null;

		when(oppRepository.findOne(opp.getId())).thenReturn(opp);
		when(subClientRepository.findAll()).thenReturn(null);

		try {
			service.getOne(opp.getId());
		} catch (Exception ex) {
			e = ex;
		}

		Assert.assertNotNull("failure - expected exception", e);
		Assert.assertTrue("failure - expects NoResultException",
				e instanceof NoResultException);
	}

	@Test
	public void getOne_ReceivesIdNoClients_ThrowsNoResultException() {
		Opportunity opp = getOppWithIdStubData();
		List<SubClient> subClients = getSubClientListStubData();
		Exception e = null;

		when(oppRepository.findOne(opp.getId())).thenReturn(opp);
		when(subClientRepository.findAll()).thenReturn(subClients);
		when(clientRepository.findAll()).thenReturn(null);

		try {
			service.getOne(opp.getId());
		} catch (Exception ex) {
			e = ex;
		}

		Assert.assertNotNull("failure - expected exception", e);
		Assert.assertTrue("failure - expects NoResultException",
				e instanceof NoResultException);
	}

	@Test
	public void getOne_ReceivesIdNoAccounts_ThrowsNoResultException() {
		Opportunity opp = getOppWithIdStubData();
		List<SubClient> subClients = getSubClientListStubData();
		Exception e = null;

		when(oppRepository.findOne(opp.getId())).thenReturn(opp);
		when(subClientRepository.findAll()).thenReturn(subClients);
		when(clientRepository.findAll()).thenReturn(new ArrayList<Client>());
		when(accountRepository.findAll()).thenReturn(null);

		try {
			service.getOne(opp.getId());
		} catch (Exception ex) {
			e = ex;
		}

		Assert.assertNotNull("failure - expected exception", e);
		Assert.assertTrue("failure - expects NoResultException",
				e instanceof NoResultException);
	}

	@Test
	public void getOne_ReceivesIdNoPeople_ThrowsNoResultException() {
		Opportunity opp = getOppWithIdStubData();
		List<SubClient> subClients = getSubClientListStubData();
		Exception e = null;

		when(oppRepository.findOne(opp.getId())).thenReturn(opp);
		when(subClientRepository.findAll()).thenReturn(subClients);
		when(clientRepository.findAll()).thenReturn(new ArrayList<Client>());
		when(accountRepository.findAll()).thenReturn(new ArrayList<Account>());
		when(personRepository.findAll()).thenReturn(null);

		try {
			service.getOne(opp.getId());
		} catch (Exception ex) {
			e = ex;
		}

		Assert.assertNotNull("failure - expected exception", e);
		Assert.assertTrue("failure - expects NoResultException",
				e instanceof NoResultException);
	}

	@Test
	public void getOne_ReceivesIdNoSolutions_ThrowsNoResultException() {
		Opportunity opp = getOppWithIdStubData();
		List<SubClient> subClients = getSubClientListStubData();
		Exception e = null;

		when(oppRepository.findOne(opp.getId())).thenReturn(opp);
		when(subClientRepository.findAll()).thenReturn(subClients);
		when(clientRepository.findAll()).thenReturn(new ArrayList<Client>());
		when(accountRepository.findAll()).thenReturn(new ArrayList<Account>());
		when(personRepository.findAll()).thenReturn(new ArrayList<Person>());
		when(solutionRepository.findAll()).thenReturn(null);

		try {
			service.getOne(opp.getId());
		} catch (Exception ex) {
			e = ex;
		}

		Assert.assertNotNull("failure - expected exception", e);
		Assert.assertTrue("failure - expects NoResultException",
				e instanceof NoResultException);
	}

	@Test
	public void getOne_ReceivesIdNoFirms_ThrowsNoResultException() {
		Opportunity opp = getOppWithIdStubData();
		List<SubClient> subClients = getSubClientListStubData();
		Exception e = null;

		when(oppRepository.findOne(opp.getId())).thenReturn(opp);
		when(subClientRepository.findAll()).thenReturn(subClients);
		when(clientRepository.findAll()).thenReturn(new ArrayList<Client>());
		when(accountRepository.findAll()).thenReturn(new ArrayList<Account>());
		when(personRepository.findAll()).thenReturn(new ArrayList<Person>());
		when(solutionRepository.findAll()).thenReturn(new ArrayList<Solution>());
		when(firmRepository.findAll()).thenReturn(null);

		try {
			service.getOne(opp.getId());
		} catch (Exception ex) {
			e = ex;
		}

		Assert.assertNotNull("failure - expected exception", e);
		Assert.assertTrue("failure - expects NoResultException",
				e instanceof NoResultException);
	}

	@Test
	public void update_ReceivesOppDetailDto_ReturnsOppDetailDto() {
		Opportunity opp = getOppWithIdStubData();
		OppDetailDto dto = new OppDetailDto(opp);
		
		when(oppRepository.findOne(dto.getId())).thenReturn(opp);
		when(oppRepository.save(any(Opportunity.class))).thenReturn(opp);
		when(subClientRepository.findOne(opp.getSubClientBean().getId()))
				.thenReturn(opp.getSubClientBean());
		when(oppTimelineRepository.findByOpportunityId(opp.getId()))
				.thenReturn(opp.getOpportunityTimelines());
		when(oppNoteRepository.findByOpportunityId(opp.getId()))
				.thenReturn(opp.getOpportunityNotes());
		when(oppSolutionRepository.findByOpportunityId(opp.getId()))
				.thenReturn(opp.getOpportunitySolutions());
		when(oppIncumbentRepository.findByOpportunityId(opp.getId()))
				.thenReturn(opp.getOpportunityIncumbents());

		OppDetailDto returnedDto = service.update(dto);

		Assert.assertNotNull("failure - expected not null", returnedDto);
		Assert.assertEquals("failure - expected id attribute match", dto.getId(),
				returnedDto.getId());
		Assert.assertEquals("failure - expected name attribute match",
				dto.getSummary().getName(), returnedDto.getSummary().getName());
	}

	@Test
	public void update_ReceivesOppDetailDtoNoId_ThrowsNoResultException() {
		Opportunity opp = getOppNoIdStubData();
		OppDetailDto dto = new OppDetailDto(opp);
		Exception e = null;

		try {
			service.update(dto);
		} catch(Exception ex) {
			e = ex;
		}

		Assert.assertNotNull("failure - expected not null", e);
		Assert.assertTrue("failure - expected NoResultException",
				e instanceof NoResultException);
	}

	@Test
	public void update_ReceivesOppDetailDtoNoOpp_ThrowsNoResultException() {
		Opportunity opp = getOppWithIdStubData();
		OppDetailDto dto = new OppDetailDto(opp);
		Exception e = null;

		when(oppRepository.findOne(dto.getId())).thenReturn(null);

		try {
			service.update(dto);
		} catch(Exception ex) {
			e = ex;
		}

		Assert.assertNotNull("failure - expected not null", e);
		Assert.assertTrue("failure - expected NoResultException",
				e instanceof NoResultException);
	}

	@Test
	public void createOppPartner_ReceivesIds_ReturnsOppDetailDto() {
		Opportunity opp = getOppWithPartnerStubData();
		Firm firm = getFirmStubData();

		when(oppPartnerRepository.save(any(OpportunityPartner.class)))
				.thenReturn(opp.getOpportunityPartners().get(0));
		when(oppRepository.findOne(opp.getId())).thenReturn(opp);
		when(oppPartnerRepository.findByOpportunityId(opp.getId()))
				.thenReturn(opp.getOpportunityPartners());
		when(firmRepository.findOne(firm.getId())).thenReturn(firm);

		OppDetailDto dto = service.createOppPartner(opp.getId(), firm.getId());

		Assert.assertNotNull("failure - expected not null", dto);
		Assert.assertEquals("failure - expected id attribute match", opp.getId(),
				dto.getId());
		Assert.assertEquals("failure - expected firm id attribute match",
				(Integer)firm.getId(),
				dto.getLandscape().getPartners().get(0).getFirm().getId());
	}

	@Test
	public void createOppPartner_ReceivesIdsNoOpp_ThrowsNoResultException() {
		Opportunity opp = getOppWithPartnerStubData();
		Firm firm = getFirmStubData();
		Exception e = null;

		when(oppPartnerRepository.save(any(OpportunityPartner.class)))
			.thenReturn(opp.getOpportunityPartners().get(0));
		when(oppRepository.findOne(opp.getId())).thenReturn(null);

		try {
			service.createOppPartner(opp.getId(), firm.getId());
		} catch (Exception ex) {
			e = ex;
		}

		Assert.assertNotNull("failure - expected not null", e);
		Assert.assertTrue("failure - expects NoResultException",
				e instanceof NoResultException);
	}

	@Test
	public void createOppPartner_ReceivesIdsNoPartners_ThrowsNoResultException() {
		Opportunity opp = getOppWithPartnerStubData();
		Firm firm = getFirmStubData();
		Exception e = null;

		when(oppPartnerRepository.save(any(OpportunityPartner.class)))
				.thenReturn(opp.getOpportunityPartners().get(0));
		when(oppRepository.findOne(opp.getId())).thenReturn(opp);
		when(oppPartnerRepository.findByOpportunityId(opp.getId())).thenReturn(null);

		try {
			service.createOppPartner(opp.getId(), firm.getId());
		} catch (Exception ex) {
			e = ex;
		}

		Assert.assertNotNull("failure - expected not null", e);
		Assert.assertTrue("failure - expects NoResultException",
				e instanceof NoResultException);
	}
	
	@Test
	public void createOppCompetitor_ReceivesIds_ReturnsOppDetailDto() {
		Opportunity opp = getOppWithCompetitorStubData();
		Firm firm = getFirmStubData();

		when(oppCompetitorRepository.save(any(OpportunityCompetitor.class)))
				.thenReturn(opp.getOpportunityCompetitors().get(0));
		when(oppRepository.findOne(opp.getId())).thenReturn(opp);
		when(oppCompetitorRepository.findByOpportunityId(opp.getId()))
				.thenReturn(opp.getOpportunityCompetitors());
		when(firmRepository.findOne(firm.getId())).thenReturn(firm);

		OppDetailDto dto = service.createOppCompetitor(opp.getId(), firm.getId());

		Assert.assertNotNull("failure - expected not null", dto);
		Assert.assertEquals("failure - expected id attribute match", opp.getId(),
				dto.getId());
		Assert.assertEquals("failure - expected firm id attribute match",
				(Integer)firm.getId(),
				dto.getLandscape().getCompetitors().get(0).getFirm().getId());
	}

	@Test
	public void createOppCompetitor_ReceivesIdsNoOpp_ThrowsNoResultException() {
		Opportunity opp = getOppWithCompetitorStubData();
		Firm firm = getFirmStubData();
		Exception e = null;

		when(oppCompetitorRepository.save(any(OpportunityCompetitor.class)))
				.thenReturn(opp.getOpportunityCompetitors().get(0));
		when(oppRepository.findOne(opp.getId())).thenReturn(null);

		try {
			service.createOppCompetitor(opp.getId(), firm.getId());
		} catch (Exception ex) {
			e = ex;
		}

		Assert.assertNotNull("failure - expected not null", e);
		Assert.assertTrue("failure - expects NoResultException",
				e instanceof NoResultException);
	}

	@Test
	public void createOppCompetitor_ReceivesIdsNoCompetitors_ThrowsNoResultException() {
		Opportunity opp = getOppWithCompetitorStubData();
		Firm firm = getFirmStubData();
		Exception e = null;

		when(oppCompetitorRepository.save(any(OpportunityCompetitor.class)))
				.thenReturn(opp.getOpportunityCompetitors().get(0));
		when(oppRepository.findOne(opp.getId())).thenReturn(opp);
		when(oppCompetitorRepository.findByOpportunityId(opp.getId())).thenReturn(null);

		try {
			service.createOppCompetitor(opp.getId(), firm.getId());
		} catch (Exception ex) {
			e = ex;
		}

		Assert.assertNotNull("failure - expected not null", e);
		Assert.assertTrue("failure - expects NoResultException",
				e instanceof NoResultException);
	}
	
	@Test
	public void deleteOppPartner_ReceivesIds_ReturnsOppDetailDto() {
		Opportunity oppWithPartner = getOppWithPartnerStubData();
		Opportunity oppNoPartner = getOppWithIdStubData();
		Integer oppId = oppWithPartner.getId();
		Integer partnerId = oppWithPartner.getOpportunityPartners().get(0).getId();

		Mockito.doNothing().when(oppPartnerRepository).delete(partnerId);
		when(oppRepository.findOne(oppId)).thenReturn(oppNoPartner);
		when(oppPartnerRepository.findByOpportunityId(oppId))
				.thenReturn(new ArrayList<OpportunityPartner>());

		OppDetailDto dto = service.deleteOppPartner(oppId, partnerId);

		Assert.assertNotNull("failure - expected not null", dto);
		Assert.assertEquals("failure - expected id attribute match", oppId,
				dto.getId());
		Assert.assertTrue("failure - expected partner list to be empty",
				dto.getLandscape().getPartners().isEmpty());
	}

	@Test
	public void deleteOppPartner_ReceivesIdsNoOpp_ThrowsNoResultException() {
		Opportunity oppWithPartner = getOppWithPartnerStubData();
		Integer oppId = oppWithPartner.getId();
		Integer partnerId = oppWithPartner.getOpportunityPartners().get(0).getId();
		Exception e = null;

		Mockito.doNothing().when(oppPartnerRepository).delete(partnerId);
		when(oppRepository.findOne(oppId)).thenReturn(null);

		try {
			service.deleteOppPartner(oppId, partnerId);
		} catch (Exception ex) {
			e = ex;
		}

		Assert.assertNotNull("failure - expected not null", e);
		Assert.assertTrue("failure - expected NoResultException",
				e instanceof NoResultException);
	}

	@Test
	public void deleteOppPartner_ReceivesIdsNoPartners_ThrowsNoResultException() {
		Opportunity oppWithPartner = getOppWithPartnerStubData();
		Opportunity oppNoPartner = getOppWithIdStubData();
		Integer oppId = oppWithPartner.getId();
		Integer partnerId = oppWithPartner.getOpportunityPartners().get(0).getId();
		Exception e = null;

		Mockito.doNothing().when(oppPartnerRepository).delete(partnerId);
		when(oppRepository.findOne(oppId)).thenReturn(oppNoPartner);
		when(oppPartnerRepository.findByOpportunityId(oppId)).thenReturn(null);

		try {
			service.deleteOppPartner(oppId, partnerId);
		} catch (Exception ex) {
			e = ex;
		}

		Assert.assertNotNull("failure - expected not null", e);
		Assert.assertTrue("failure - expected NoResultException",
				e instanceof NoResultException);
	}

	@Test
	public void deleteOppCompetitor_ReceivesIds_ReturnsOppDetailDto() {
		Opportunity oppWithCompetitor = getOppWithCompetitorStubData();
		Opportunity oppNoCompetitor = getOppWithIdStubData();
		Integer oppId = oppWithCompetitor.getId();
		Integer competitorId =
				oppWithCompetitor.getOpportunityCompetitors().get(0).getId();

		Mockito.doNothing().when(oppCompetitorRepository).delete(competitorId);
		when(oppRepository.findOne(oppId)).thenReturn(oppNoCompetitor);
		when(oppCompetitorRepository.findByOpportunityId(oppId))
				.thenReturn(new ArrayList<OpportunityCompetitor>());

		OppDetailDto dto = service.deleteOppCompetitor(oppId, competitorId);

		Assert.assertNotNull("failure - expected not null", dto);
		Assert.assertEquals("failure - expected id attribute match", oppId,
				dto.getId());
		Assert.assertTrue("failure - expected competitor list to be empty",
				dto.getLandscape().getCompetitors().isEmpty());
	}

	@Test
	public void deleteOppCompetitor_ReceivesIdsNoOpp_ThrowsNoResultException() {
		Opportunity oppWithCompetitor = getOppWithCompetitorStubData();
		Integer oppId = oppWithCompetitor.getId();
		Integer competitorId =
				oppWithCompetitor.getOpportunityCompetitors().get(0).getId();
		Exception e = null;

		Mockito.doNothing().when(oppCompetitorRepository).delete(competitorId);
		when(oppRepository.findOne(oppId)).thenReturn(null);

		try {
			service.deleteOppCompetitor(oppId, competitorId);
		} catch(Exception ex) {
			e = ex;
		}

		Assert.assertNotNull("failure - expected not null", e);
		Assert.assertTrue("failure - expected NoResultException",
				e instanceof NoResultException);
	}

	@Test
	public void deleteOppCompetitor_ReceivesIdsNoCompetitors_ThrowsNoResultException() {
		Opportunity oppWithCompetitor = getOppWithCompetitorStubData();
		Opportunity oppNoCompetitor = getOppWithIdStubData();
		Integer oppId = oppWithCompetitor.getId();
		Integer competitorId =
				oppWithCompetitor.getOpportunityCompetitors().get(0).getId();
		Exception e = null;

		Mockito.doNothing().when(oppCompetitorRepository).delete(competitorId);
		when(oppRepository.findOne(oppId)).thenReturn(oppNoCompetitor);
		when(oppCompetitorRepository.findByOpportunityId(oppId)).thenReturn(null);

		try {
			service.deleteOppCompetitor(oppId, competitorId);
		} catch(Exception ex) {
			e = ex;
		}

		Assert.assertNotNull("failure - expected not null", e);
		Assert.assertTrue("failure - expected NoResultException",
				e instanceof NoResultException);
	}
	
	@Test
	public void createOppEvent_ReceivesId_ReturnsOppDetailDto() {
		Opportunity opp = getOppWithEventStubData();

		when(oppEventRepository.save(any(OpportunityEvent.class)))
				.thenReturn(opp.getOpportunityEvents().get(0));
		when(oppRepository.findOne(opp.getId())).thenReturn(opp);

		OppDetailDto dto = service.createOppEvent(opp.getId());

		Assert.assertNotNull("failure - expected not null", dto);
		Assert.assertEquals("failure - expected id attribute match", opp.getId(),
				dto.getId());
		Assert.assertEquals("failure - expected event list size 1", 1,
				(int)dto.getCaptureActivities().getActivities().size());
	}
	
	@Test
	public void createOppEvent_ReceivesId_ThrowsNoResultException() {
		Opportunity opp = getOppWithEventStubData();
		Exception e = null;

		when(oppEventRepository.save(any(OpportunityEvent.class)))
				.thenReturn(opp.getOpportunityEvents().get(0));
		when(oppRepository.findOne(opp.getId())).thenReturn(null);

		try {
			service.createOppEvent(opp.getId());
		} catch (Exception ex) {
			e = ex;
		}

		Assert.assertNotNull("failure - expected not null", e);
		Assert.assertTrue("failure - expected NoResultException",
				e instanceof NoResultException);
	}
	
	@Test
	public void deleteOppEvent_ReceivesIds_ReturnsOppDetailDto() {
		Opportunity opp = getOppWithEventStubData();
		Integer eventId = opp.getOpportunityEvents().get(0).getId();

		Mockito.doNothing().when(oppEventRepository).delete(eventId);
		when(oppRepository.findOne(opp.getId())).thenReturn(opp);
		when(oppEventRepository.findByOpportunityId(opp.getId()))
				.thenReturn(new ArrayList<OpportunityEvent>());

		OppDetailDto dto = service.deleteOppEvent(opp.getId(), eventId);

		Assert.assertNotNull("failure - expected not null", dto);
		Assert.assertEquals("failure - expected id attribute match", opp.getId(),
				dto.getId());
		Assert.assertEquals("failure - expected event list size 0", 0,
				(int)dto.getCaptureActivities().getActivities().size());
	}
	
	@Test
	public void deleteOppEvent_ReceivesIdsNoEvents_ReturnsOppDetailDto() {
		Opportunity opp = getOppWithEventStubData();
		Integer eventId = opp.getOpportunityEvents().get(0).getId();
		Exception e = null;

		Mockito.doNothing().when(oppEventRepository).delete(eventId);
		when(oppRepository.findOne(opp.getId())).thenReturn(opp);
		when(oppEventRepository.findByOpportunityId(opp.getId())).thenReturn(null);

		try {
			service.deleteOppEvent(opp.getId(), eventId);
		} catch (Exception ex) {
			e = ex;
		}

		Assert.assertNotNull("failure - expected not null", e);
		Assert.assertTrue("failure - expected NoResultException",
				e instanceof NoResultException);
	}
	
	@Test
	public void deleteOppEvent_ReceivesIdsNoOpp_ReturnsOppDetailDto() {
		Opportunity opp = getOppWithEventStubData();
		Integer eventId = opp.getOpportunityEvents().get(0).getId();
		Exception e = null;

		Mockito.doNothing().when(oppEventRepository).delete(eventId);
		when(oppRepository.findOne(opp.getId())).thenReturn(null);

		try {
			service.deleteOppEvent(opp.getId(), eventId);
		} catch (Exception ex) {
			e = ex;
		}

		Assert.assertNotNull("failure - expected not null", e);
		Assert.assertTrue("failure - expected NoResultException",
				e instanceof NoResultException);
	}

}
