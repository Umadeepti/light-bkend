package com.dominionconsulting.tito.opp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dominionconsulting.tito.opp.common.util.Milestone;
import com.dominionconsulting.tito.opp.common.util.ResponseType;
import com.dominionconsulting.tito.opp.dto.AccountDto;
import com.dominionconsulting.tito.opp.dto.ClientDto;
import com.dominionconsulting.tito.opp.dto.FirmDto;
import com.dominionconsulting.tito.opp.dto.OppCompetitorDto;
import com.dominionconsulting.tito.opp.dto.OppDetailDto;
import com.dominionconsulting.tito.opp.dto.OppEventDto;
import com.dominionconsulting.tito.opp.dto.OppIncumbentDto;
import com.dominionconsulting.tito.opp.dto.OppNoteDto;
import com.dominionconsulting.tito.opp.dto.OppPartnerDto;
import com.dominionconsulting.tito.opp.dto.OppTimelineDto;
import com.dominionconsulting.tito.opp.dto.PersonDto;
import com.dominionconsulting.tito.opp.dto.SolutionDto;
import com.dominionconsulting.tito.opp.dto.SubClientDto;
import com.dominionconsulting.tito.opp.model.Account;
import com.dominionconsulting.tito.opp.model.Client;
import com.dominionconsulting.tito.opp.model.Firm;
import com.dominionconsulting.tito.opp.model.Opportunity;
import com.dominionconsulting.tito.opp.model.OpportunityCompetitor;
import com.dominionconsulting.tito.opp.model.OpportunityEvent;
import com.dominionconsulting.tito.opp.model.OpportunityIncumbent;
import com.dominionconsulting.tito.opp.model.OpportunityNote;
import com.dominionconsulting.tito.opp.model.OpportunityPartner;
import com.dominionconsulting.tito.opp.model.OpportunitySolution;
import com.dominionconsulting.tito.opp.model.OpportunityTimeline;
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

@Service
@Transactional
public class OppDetailService {

	@Autowired
	private OpportunityRepository oppRepository;

	@Autowired
	private SubClientRepository subClientRepository;

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private SolutionRepository solutionRepository;

	@Autowired
	private FirmRepository firmRepository;

	@Autowired
	private OpportunityTimelineRepository oppTimelineRepository;

	@Autowired
	private OpportunityNoteRepository oppNoteRepository;
	
	@Autowired
	private OpportunitySolutionRepository oppSolutionRepository;

	@Autowired
	private OpportunityPartnerRepository oppPartnerRepository;

	@Autowired
	private OpportunityCompetitorRepository oppCompetitorRepository;
	
	@Autowired
	private OpportunityIncumbentRepository oppIncumbentRepository;
	
	@Autowired
	private OpportunityEventRepository oppEventRepository;

	public OppDetailDto getOne(Integer id) {
		Opportunity opp = oppRepository.findOne(id);
		if (opp == null) throw new NoResultException();
		updateEvents(opp);
		OppDetailDto dto = new OppDetailDto(opp);
		dto.getSummary().setDefinedLists();
		
		List<AccountDto> accountDtos = new ArrayList<AccountDto>();
		List<ClientDto> clientDtos = new ArrayList<ClientDto>();
		List<SubClientDto> subClientDtos = new ArrayList<SubClientDto>();
		Iterable<SubClient> subClients = subClientRepository.findAll();
		if (subClients == null) throw new NoResultException();
		for (SubClient subClient : subClients) {
			if (subClient.getClientBean() == null) {
				subClientDtos.add(new SubClientDto(subClient));
			}
		}
		if (!subClientDtos.isEmpty()) {
			ClientDto clientDto = new ClientDto();
			clientDto.setSubClients(subClientDtos);
			clientDtos.add(clientDto);
		}
		Iterable<Client> clients = clientRepository.findAll();
		if (clients == null) throw new NoResultException();
		for (Client client : clients) {
			if (client.getAccountBean() == null) {
				clientDtos.add(new ClientDto(client));
			}
		}
		if (!clientDtos.isEmpty()) {
			AccountDto accountDto = new AccountDto();
			accountDto.setClients(clientDtos);
			accountDtos.add(accountDto);
		}
		Iterable<Account> accounts = accountRepository.findAll();
		if (accounts == null) throw new NoResultException();
		for (Account account : accounts) {
			accountDtos.add(new AccountDto(account));
		}
		dto.getSummary().setAccounts(accountDtos);

		Iterable<Person> people = personRepository.findAll();
		if (people == null) throw new NoResultException();
		List<PersonDto> personDtos = new ArrayList<PersonDto>();
		for (Person person : people) {
			personDtos.add(new PersonDto(person));
		}
		dto.getSummary().setPeople(personDtos);

		Iterable<Solution> solutions = solutionRepository.findAll();
		if (solutions == null) throw new NoResultException();
		List<SolutionDto> solutionDtos = new ArrayList<SolutionDto>();
		for (Solution solution : solutions) {
			solutionDtos.add(new SolutionDto(solution));
		}
		dto.getDetails().setSolutions(solutionDtos);


		Iterable<Firm> firms = firmRepository.findAll();
		if (firms == null) throw new NoResultException();
		List<FirmDto> firmDtos = new ArrayList<FirmDto>();
		for (Firm firm : firms) {
			firmDtos.add(new FirmDto(firm));
		}
		dto.getLandscape().setFirms(firmDtos);

		return dto;
	}

	public OppDetailDto update(OppDetailDto dto) {
		if (dto.getId() == null) throw new NoResultException();
		Opportunity opp = oppRepository.findOne(dto.getId());
		if (opp == null) throw new NoResultException();
		dto.toOpportunity(opp);
		
		Opportunity updatedOpp = oppRepository.save(opp);

		OppTimelineDto proposalDueDto = dto.getSummary().getProposalDueDate();
		if (proposalDueDto != null) {
			OpportunityTimeline proposalDue;
			if (proposalDueDto.getId() == null) {
				proposalDue = new OpportunityTimeline();
				proposalDueDto.toOpportunityTimeline(proposalDue);
				proposalDue.setMilestone(Milestone.PROPOSAL_DUE.getText());
				proposalDue.setResponseType(ResponseType.RFP.getText());
				proposalDue.setOpportunity(updatedOpp);
			} else {
				proposalDue =
						oppTimelineRepository.findOne(proposalDueDto.getId());
				proposalDueDto.toOpportunityTimeline(proposalDue);
			}
			oppTimelineRepository.save(proposalDue);
		}

		OppTimelineDto estAwardDto = dto.getSummary().getEstAwardDate();
		if (estAwardDto != null) {
			OpportunityTimeline estAward;
			if (estAwardDto.getId() == null) {
				estAward = new OpportunityTimeline();
				estAwardDto.toOpportunityTimeline(estAward);
				estAward.setMilestone(Milestone.EST_AWARD.getText());
				estAward.setResponseType(ResponseType.RFP.getText());
				estAward.setOpportunity(updatedOpp);
			} else {
				estAward =
						oppTimelineRepository.findOne(estAwardDto.getId());
				estAwardDto.toOpportunityTimeline(estAward);
			}
			oppTimelineRepository.save(estAward);
		}

		OppNoteDto noteDto = dto.getDetails().getNote();
		if (noteDto != null) {
			OpportunityNote note;
			if (noteDto.getId() == null) {
				note = new OpportunityNote();
				noteDto.toOpportunityNote(note);
				note.setCreateDate(new Date());
				note.setOpportunity(updatedOpp);
			} else {
				note = oppNoteRepository.findOne(noteDto.getId());
				noteDto.toOpportunityNote(note);
			}
			oppNoteRepository.save(note);
		}
		
		List<OpportunitySolution> existingOppSolutions = oppSolutionRepository.findByOpportunity(opp);
		
		if(dto.getDetails().getSelectedSolutions() != null) {
			//add selected solutions
			for(SolutionDto selectedSolution : dto.getDetails().getSelectedSolutions()) {
				boolean solutionExists = false;
				if(existingOppSolutions!=null) {
					for(OpportunitySolution existingOppSolution : existingOppSolutions) {
						if(existingOppSolution.getSolutionBean().getId().equals(selectedSolution.getId())) {
							solutionExists = true;
							break;
						}
					}
				}
				
				if(!solutionExists) {
					OpportunitySolution newOppSolution = new OpportunitySolution();
					newOppSolution.setOpportunity(opp);
					newOppSolution.setSolutionBean(selectedSolution.toSolution());
					oppSolutionRepository.save(newOppSolution);
				}
			}
		}
		
		if(existingOppSolutions!=null && existingOppSolutions.size()>0) {
			//remove unselected solutions
			for(OpportunitySolution existingOppSolution : existingOppSolutions) { 
				boolean solutionSelected = false;
				for(SolutionDto selectedSolution : dto.getDetails().getSelectedSolutions()) {
					if(selectedSolution.getId().equals(existingOppSolution.getSolutionBean().getId())) {
						solutionSelected = true;
						break;
					}
				}
				
				if(!solutionSelected) {
					oppSolutionRepository.delete(existingOppSolution.getId());
				}
				
			}
		}
		
		List<OppPartnerDto> partnerDtos = dto.getLandscape().getPartners();
		if (partnerDtos != null) {
			for (OppPartnerDto partnerDto : partnerDtos) {
				OpportunityPartner partner =
						oppPartnerRepository.findOne(partnerDto.getId());
				partnerDto.toOpportunityPartner(partner);
				oppPartnerRepository.save(partner);
			}
		}

		List<OppCompetitorDto> competitorDtos = dto.getLandscape().getCompetitors();
		if (competitorDtos != null) {
			for (OppCompetitorDto competitorDto : competitorDtos) {
				OpportunityCompetitor competitor =
						oppCompetitorRepository.findOne(competitorDto.getId());
				competitorDto.toOpportunityCompetitor(competitor);
				oppCompetitorRepository.save(competitor);
			}
		}
		
		List<OppIncumbentDto> addedIncumbentDtos =
				dto.getLandscape().getAddedIncumbents();
		if (addedIncumbentDtos != null) {
			for (OppIncumbentDto addedIncumbentDto : addedIncumbentDtos) {
				OpportunityIncumbent addedIncumbent = new OpportunityIncumbent();
				addedIncumbentDto.toOpportunityIncumbent(addedIncumbent);
				addedIncumbent.setOpportunity(updatedOpp);
				oppIncumbentRepository.save(addedIncumbent);
			}
		}
		
		List<OppIncumbentDto> removedIncumbentDtos =
				dto.getLandscape().getRemovedIncumbents();
		if (removedIncumbentDtos != null) {
			for (OppIncumbentDto removedIncumbentDto : removedIncumbentDtos) {
				oppIncumbentRepository.deleteByFirmBeanAndOpportunity(removedIncumbentDto.getFirm().toFirm(), opp);
			}
		}
		
		List<OppEventDto> eventDtos = dto.getCaptureActivities().getActivities();
		if (eventDtos != null) {
			
			for (OppEventDto eventDto : eventDtos) {
				OpportunityEvent event=null;
				if(eventDto.getId()!=null){
					int eventId=eventDto.getId();
					event= oppEventRepository.findOne(eventId);
				}
				if(event==null){
					event = new OpportunityEvent() ;
				}
				eventDto.toOpportunityEvent(event);
				Opportunity oppor= new Opportunity(dto.getId());
				event.setOpportunity(oppor);
				oppEventRepository.save(event);
				
			}
			updateEvents(opp);
		}

		SubClient subClientBean = updatedOpp.getSubClientBean();
		if (subClientBean != null) {
			SubClient subClient =
					subClientRepository.findOne(subClientBean.getId());
			updatedOpp.setSubClientBean(subClient);
		}
		
		Person manager = updatedOpp.getManager();
		if (manager != null) {
			Person owner = personRepository.findOne(manager.getId());
			updatedOpp.setManager(owner);
		}
		
		List<OpportunityTimeline> timelines =
				oppTimelineRepository.findByOpportunityId(updatedOpp.getId());
		updatedOpp.setOpportunityTimelines(timelines);
		
		List<OpportunityNote> notes =
				oppNoteRepository.findByOpportunityId(updatedOpp.getId());
		if (notes != null) {
			for (OpportunityNote note : notes) {
				Integer creatorId = note.getCreator().getId();
				Person creator = personRepository.findOne(creatorId);
				note.setCreator(creator);
			}
		}
		updatedOpp.setOpportunityNotes(notes);
		
		Firm primingPartner = updatedOpp.getPrimingPartner();
		if (primingPartner != null) {
			Firm firm = firmRepository.findOne(primingPartner.getId());
			updatedOpp.setPrimingPartner(firm);
		}
		
		List<OpportunityIncumbent> oppIncumbents =
				oppIncumbentRepository.findByOpportunityId(updatedOpp.getId());
		if (oppIncumbents != null) {
			for (OpportunityIncumbent oppIncumbent : oppIncumbents) {
				Integer firmId = oppIncumbent.getFirmBean().getId();
				Firm firm = firmRepository.findOne(firmId);
				oppIncumbent.setFirmBean(firm);
			}
		}
		updatedOpp.setOpportunityIncumbents(oppIncumbents);
		
		OppDetailDto returnDetailDto = new OppDetailDto(updatedOpp);
		returnDetailDto.getSummary().setAccounts(dto.getSummary().getAccounts());
		returnDetailDto.getSummary().setPeople(dto.getSummary().getPeople());
		returnDetailDto.getDetails().setSolutions(dto.getDetails().getSolutions());
		returnDetailDto.getLandscape().setFirms(dto.getLandscape().getFirms());
		
		return returnDetailDto;
	}

	public OppDetailDto createOppPartner(Integer oppId, Integer firmId) {
		Opportunity opportunity = new Opportunity();
		opportunity.setId(oppId);

		Firm firm = new Firm();
		firm.setId(firmId);

		OpportunityPartner partner = new OpportunityPartner();
		partner.setOpportunity(opportunity);
		partner.setFirmBean(firm);

		oppPartnerRepository.save(partner);

		Opportunity opp = oppRepository.findOne(oppId);
		if (opp == null) throw new NoResultException();
		updatePartners(opp);
		return new OppDetailDto(opp);
	}

	public OppDetailDto createOppCompetitor(Integer oppId, Integer firmId) {
		Opportunity opportunity = new Opportunity();
		opportunity.setId(oppId);

		Firm firm = new Firm();
		firm.setId(firmId);

		OpportunityCompetitor competitor = new OpportunityCompetitor();
		competitor.setOpportunity(opportunity);
		competitor.setFirmBean(firm);

		oppCompetitorRepository.save(competitor);

		Opportunity opp = oppRepository.findOne(oppId);
		if (opp == null) throw new NoResultException();
		updateCompetitors(opp);
		return new OppDetailDto(opp);
	}

	public OppDetailDto deleteOppPartner(Integer oppId, Integer partnerId) {
		oppPartnerRepository.delete(partnerId);

		Opportunity opp = oppRepository.findOne(oppId);
		if (opp == null) throw new NoResultException();
		updatePartners(opp);
		return new OppDetailDto(opp);
	}

	public OppDetailDto deleteOppCompetitor(Integer oppId, Integer competitorId) {
		oppCompetitorRepository.delete(competitorId);

		Opportunity opp = oppRepository.findOne(oppId);
		if (opp == null) throw new NoResultException();
		updateCompetitors(opp);
		return new OppDetailDto(opp);
	}
	
	public OppDetailDto createOppEvent(Integer oppId) {
		Opportunity opportunity = new Opportunity();
		opportunity.setId(oppId);
		
		OpportunityEvent event = new OpportunityEvent();
		event.setEvent(" ");
		event.setOpportunity(opportunity);
		
		oppEventRepository.save(event);
		List<OpportunityEvent> eventList =(List<OpportunityEvent>) oppEventRepository.findAll();
		Opportunity opp = oppRepository.findOne(oppId);
		if (opp == null) throw new NoResultException();
		return new OppDetailDto(opp);
	}
	
	public OppDetailDto deleteOppEvent(Integer oppId, Integer eventId) {
		oppEventRepository.findAll();
		oppEventRepository.delete(eventId);
		
		Opportunity opp = oppRepository.findOne(oppId);
		if (opp == null) throw new NoResultException();
		updateEvents(opp);
		return new OppDetailDto(opp);
	}

	private void updatePartners(Opportunity opp) {
		List<OpportunityPartner> partners =
				oppPartnerRepository.findByOpportunityId(opp.getId());
		if (partners == null) throw new NoResultException();
		for (OpportunityPartner partner : partners) {
			Integer firmId = partner.getFirmBean().getId();
			Firm firm = firmRepository.findOne(firmId);
			partner.setFirmBean(firm);
		}
		opp.setOpportunityPartners(partners);
	}
	
	private void updateCompetitors(Opportunity opp) {
		List<OpportunityCompetitor> competitors =
				oppCompetitorRepository.findByOpportunityId(opp.getId());
		if (competitors == null) throw new NoResultException();
		for (OpportunityCompetitor competitor : competitors) {
			Integer firmId = competitor.getFirmBean().getId();
			Firm firm = firmRepository.findOne(firmId);
			competitor.setFirmBean(firm);
		}
		opp.setOpportunityCompetitors(competitors);
	}
	
	private void updateEvents(Opportunity opp) {
		List<OpportunityEvent> events =
				oppEventRepository.findByOpportunityId(opp.getId());
		if (events == null) throw new NoResultException();
		opp.setOpportunityEvents(events);
	}

}
