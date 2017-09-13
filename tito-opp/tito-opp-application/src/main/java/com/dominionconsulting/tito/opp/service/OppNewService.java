package com.dominionconsulting.tito.opp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dominionconsulting.tito.opp.common.util.Milestone;
import com.dominionconsulting.tito.opp.common.util.ResponseType;
import com.dominionconsulting.tito.opp.common.util.TitoException;
import com.dominionconsulting.tito.opp.dto.AccountDto;
import com.dominionconsulting.tito.opp.dto.ClientDto;
import com.dominionconsulting.tito.opp.dto.OppDetailsDto;
import com.dominionconsulting.tito.opp.dto.OppNewDto;
import com.dominionconsulting.tito.opp.dto.OppNoteDto;
import com.dominionconsulting.tito.opp.dto.OppSolutionDto;
import com.dominionconsulting.tito.opp.dto.OppSummaryDto;
import com.dominionconsulting.tito.opp.dto.OppTimelineDto;
import com.dominionconsulting.tito.opp.dto.PersonDto;
import com.dominionconsulting.tito.opp.dto.SolutionDto;
import com.dominionconsulting.tito.opp.dto.SubClientDto;
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

@Service
@Transactional
public class OppNewService {

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
	private OpportunityRepository oppRepository;

	@Autowired
	private OpportunityTimelineRepository oppTimelineRepository;

	@Autowired
	private OpportunityNoteRepository oppNoteRepository;

	@Autowired
	private OpportunitySolutionRepository oppSolutionRepository;

	public OppNewDto getInfo() {
		OppSummaryDto summary = new OppSummaryDto();
		summary.setDefinedLists();
		OppDetailsDto details = new OppDetailsDto();

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
		summary.setAccounts(accountDtos);

		Iterable<Person> people = personRepository.findAll();
		if (people == null) throw new NoResultException();
		List<PersonDto> personDtos = new ArrayList<PersonDto>();
		for (Person person : people) {
			personDtos.add(new PersonDto(person));
		}
		summary.setPeople(personDtos);

		Iterable<Solution> solutions = solutionRepository.findAll();
		if (solutions == null) throw new NoResultException();
		List<SolutionDto> solutionDtos = new ArrayList<SolutionDto>();
		for (Solution solution : solutions) {
			solutionDtos.add(new SolutionDto(solution));
		}
		details.setSolutions(solutionDtos);

		OppNewDto dto = new OppNewDto();
		dto.setSummary(summary);
		dto.setDetails(details);
		return dto;
	}

	public Integer create(OppNewDto dto) {
		if (dto.getId() != null) throw new EntityExistsException();
		Opportunity opp = dto.toOpportunity();
		Opportunity createdOpp = oppRepository.save(opp);
		if (createdOpp == null) throw new TitoException("Cannot Create Opportunity");

		OppTimelineDto proposalDue = dto.getSummary().getProposalDueDate();
		if (proposalDue != null) {
			OpportunityTimeline proposalDueTimeline = new OpportunityTimeline();
			proposalDue.toOpportunityTimeline(proposalDueTimeline);
			proposalDueTimeline.setMilestone(Milestone.PROPOSAL_DUE.getText());
			proposalDueTimeline.setResponseType(ResponseType.RFP.getText());
			proposalDueTimeline.setOpportunity(createdOpp);
			oppTimelineRepository.save(proposalDueTimeline);
		}

		OppTimelineDto estAward = dto.getSummary().getEstAwardDate();
		if (estAward != null) {
			OpportunityTimeline awardTimeline = new OpportunityTimeline();
			estAward.toOpportunityTimeline(awardTimeline);
			awardTimeline.setMilestone(Milestone.EST_AWARD.getText());
			awardTimeline.setResponseType(ResponseType.RFP.getText());
			awardTimeline.setOpportunity(createdOpp);
			oppTimelineRepository.save(awardTimeline);
		}

		OppNoteDto oppNoteDto = dto.getDetails().getNote();
		if (oppNoteDto != null) {
			OpportunityNote oppNote = new OpportunityNote();
			oppNoteDto.toOpportunityNote(oppNote);
			oppNote.setCreateDate(new Date());
			oppNote.setOpportunity(createdOpp);
			oppNoteRepository.save(oppNote);
		}

		List<OppSolutionDto> oppSolutionDtos = dto.getDetails().getAddedOppSolutions();
		if (oppSolutionDtos != null) {
			for (OppSolutionDto oppSolutionDto : oppSolutionDtos) {
				OpportunitySolution oppSolution = new OpportunitySolution();
				oppSolutionDto.toOpportunitySolution(oppSolution);
				oppSolution.setOpportunity(createdOpp);
				oppSolutionRepository.save(oppSolution);
			}
		}

		return createdOpp.getId();
	}

	public OppNewDto getOne(Integer id) {
		Opportunity opp = oppRepository.findOne(id);
		if (opp == null) throw new NoResultException();

		SubClient subClientBean = opp.getSubClientBean();
		if (subClientBean != null) {
			SubClient subClient =
					subClientRepository.findOne(subClientBean.getId());
			opp.setSubClientBean(subClient);
		}

		Person manager = opp.getManager();
		if (manager != null) {
			Person owner = personRepository.findOne(manager.getId());
			opp.setManager(owner);
		}

		List<OpportunityTimeline> timelines =
				oppTimelineRepository.findByOpportunityId(id);
		opp.setOpportunityTimelines(timelines);

		List<OpportunityNote> notes = oppNoteRepository.findByOpportunityId(id);
		if (notes != null) {
			for (OpportunityNote note : notes) {
				Integer creatorId = note.getCreator().getId();
				Person creator = personRepository.findOne(creatorId);
				note.setCreator(creator);
			}
		}
		opp.setOpportunityNotes(notes);

		List<OpportunitySolution> oppSolutions =
				oppSolutionRepository.findByOpportunityId(id);
		if (oppSolutions != null) {
			for (OpportunitySolution oppSolution : oppSolutions) {
				Integer solutionId = oppSolution.getSolutionBean().getId();
				Solution solution = solutionRepository.findOne(solutionId);
				oppSolution.setSolutionBean(solution);
			}
		}
		opp.setOpportunitySolutions(oppSolutions);

		return new OppNewDto(opp);
	}

}
