package com.dominionconsulting.tito.opp.dto;

import java.util.ArrayList;
import java.util.List;

import com.dominionconsulting.tito.opp.common.util.AwardType;
import com.dominionconsulting.tito.opp.common.util.Milestone;
import com.dominionconsulting.tito.opp.common.util.Stage;
import com.dominionconsulting.tito.opp.common.util.Status;
import com.dominionconsulting.tito.opp.model.Account;
import com.dominionconsulting.tito.opp.model.Client;
import com.dominionconsulting.tito.opp.model.Opportunity;
import com.dominionconsulting.tito.opp.model.OpportunityTimeline;
import com.dominionconsulting.tito.opp.model.Person;
import com.dominionconsulting.tito.opp.model.SubClient;

public class OppSummaryDto {
	
	private String name;
	
	private AccountDto account;
	
	private ClientDto client;
	
	private SubClientDto subClient;
	
	private List<AccountDto> accounts;
	
	private Boolean prime;
	
	private OppTimelineDto proposalDueDate;
	
	private OppTimelineDto estAwardDate;
	
	private Short winProbability;
	
	private PersonDto owner;
	
	private List<PersonDto> people;

	private String stage;
	
	private List<String> stages;
	
	private String status;
	
	private List<String> statuses;
	
	private String awardType;
	
	private List<String> awardTypes;
	
	private String govWinId;
	
	private String govWinLink;
	
	private String fboLink;
	
	public OppSummaryDto() {
		setDefinedLists();
	}

	public OppSummaryDto(Opportunity opp) {		
		name = opp.getName();
		
		SubClient subClientBean = opp.getSubClientBean();
		if (subClientBean != null) {
			subClient = new SubClientDto(subClientBean);
			Client clientBean = subClientBean.getClientBean();
			if (clientBean != null) {
				client = new ClientDto(clientBean);
				Account accountBean = clientBean.getAccountBean();
				if (accountBean != null) {
					account = new AccountDto(accountBean);
				}
			}
		}
		
		setPrime(opp.getCrediblePrime());
		
		List<OpportunityTimeline> timelines = opp.getOpportunityTimelines();
		if (timelines != null) {
			for (OpportunityTimeline timeline : timelines) {
				String milestone = timeline.getMilestone();
				if (milestone.equals(Milestone.PROPOSAL_DUE.getText()))
					proposalDueDate = new OppTimelineDto(timeline);
				else if (milestone.equals(Milestone.EST_AWARD.getText()))
					estAwardDate = new OppTimelineDto(timeline);
			}
		}
		
		winProbability = opp.getWinProbability();
		
		Person manager = opp.getManager();
		if (manager != null) {
			owner = new PersonDto(manager);
		}
		
		stage = opp.getStage();
		status = opp.getStatus();
		awardType = opp.getAwardType();		
		govWinId = opp.getGovWinId();
		govWinLink = opp.getGovWinLink();
		fboLink = opp.getFboLink();
	}
	
	public void setDefinedLists() {
		stages = new ArrayList<String>();
		stages.add(Stage.QUALIFYING.getText());
		stages.add(Stage.QUALIFIED.getText());
		stages.add(Stage.PROPOSAL_REVIEW.getText());
		stages.add(Stage.PROTESTED_PENDING.getText());
		
		statuses = new ArrayList<String>();
		statuses.add(Status.OPEN.getText());
		statuses.add(Status.PROTESTED.getText());
		statuses.add(Status.WON.getText());
		statuses.add(Status.LOST_ON_PRICE.getText());
		statuses.add(Status.LOST_ON_TECHNICAL.getText());
		
		awardTypes = new ArrayList<String>();
		awardTypes.add(AwardType.FIRM_FIXED_PRICE.getText());
		awardTypes.add(AwardType.TIME_AND_MATERIALS.getText());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AccountDto getAccount() {
		return account;
	}

	public void setAccount(AccountDto account) {
		this.account = account;
	}

	public ClientDto getClient() {
		return client;
	}

	public void setClient(ClientDto client) {
		this.client = client;
	}

	public SubClientDto getSubClient() {
		return subClient;
	}

	public void setSubClient(SubClientDto subClient) {
		this.subClient = subClient;
	}

	public List<AccountDto> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<AccountDto> accounts) {
		this.accounts = accounts;
	}

	public Boolean getPrime() {
		return prime;
	}

	public void setPrime(Boolean prime) {
		this.prime = prime;
	}

	public OppTimelineDto getProposalDueDate() {
		return proposalDueDate;
	}

	public void setProposalDueDate(OppTimelineDto proposalDueDate) {
		this.proposalDueDate = proposalDueDate;
	}

	public OppTimelineDto getEstAwardDate() {
		return estAwardDate;
	}

	public void setEstAwardDate(OppTimelineDto estAwardDate) {
		this.estAwardDate = estAwardDate;
	}

	public Short getWinProbability() {
		return winProbability;
	}

	public void setWinProbability(Short winProbability) {
		this.winProbability = winProbability;
	}

	public PersonDto getOwner() {
		return owner;
	}

	public void setOwner(PersonDto owner) {
		this.owner = owner;
	}

	public List<PersonDto> getPeople() {
		return people;
	}

	public void setPeople(List<PersonDto> people) {
		this.people = people;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public List<String> getStages() {
		return stages;
	}

	public void setStages(List<String> stages) {
		this.stages = stages;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<String> getStatuses() {
		return statuses;
	}

	public void setStatuses(List<String> statuses) {
		this.statuses = statuses;
	}

	public String getAwardType() {
		return awardType;
	}

	public void setAwardType(String awardType) {
		this.awardType = awardType;
	}

	public List<String> getAwardTypes() {
		return awardTypes;
	}

	public void setAwardTypes(List<String> awardTypes) {
		this.awardTypes = awardTypes;
	}

	public String getGovWinId() {
		return govWinId;
	}

	public void setGovWinId(String govWinId) {
		this.govWinId = govWinId;
	}

	public String getGovWinLink() {
		return govWinLink;
	}

	public void setGovWinLink(String govWinLink) {
		this.govWinLink = govWinLink;
	}

	public String getFboLink() {
		return fboLink;
	}

	public void setFboLink(String fboLink) {
		this.fboLink = fboLink;
	}

	public void toOpportunity(Opportunity opp) {
		opp.setName(name);
		if (subClient != null) {
			opp.setSubClientBean(subClient.toSubClient());
		} else {
			opp.setSubClientBean(null);
		}
		opp.setCrediblePrime(prime);
		opp.setWinProbability(winProbability);
		if (owner != null) {
			opp.setManager(owner.toPerson());
		} else {
			opp.setManager(null);
		}
		opp.setStage(stage);
		opp.setStatus(status);
		opp.setAwardType(awardType);
		opp.setGovWinId(govWinId);
		opp.setGovWinLink(govWinLink);
		opp.setFboLink(fboLink);
	}

}
