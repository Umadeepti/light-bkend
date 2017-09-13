package com.dominionconsulting.tito.opp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.dominionconsulting.tito.opp.common.util.EventStatus;
import com.dominionconsulting.tito.opp.model.Firm;
import com.dominionconsulting.tito.opp.model.Opportunity;
import com.dominionconsulting.tito.opp.model.OpportunityCompetitor;
import com.dominionconsulting.tito.opp.model.OpportunityEvent;
import com.dominionconsulting.tito.opp.model.OpportunityNote;
import com.dominionconsulting.tito.opp.model.OpportunityPartner;
import com.dominionconsulting.tito.opp.model.OpportunityStep;
import com.dominionconsulting.tito.opp.model.SubClient;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OppApplication.class)
public abstract class AbstractTest {
	
	protected String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(obj);
	}

	protected <T> T mapFromJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json, clazz);
	}
	
	protected SubClient getSubClientStubData() {
		SubClient subClient = new SubClient();
		subClient.setId(1);
		subClient.setName("Sub-Client Name Test");
		return subClient;
	}
	
	protected List<SubClient> getSubClientListStubData() {
		List<SubClient> subClients = new ArrayList<SubClient>();
		subClients.add(getSubClientStubData());
		return subClients;
	}
	
	protected Firm getFirmStubData() {
		Firm firm = new Firm();
		firm.setId(1);
		firm.setName("Firm Name Test");
		return firm;
	}
	
	protected Opportunity getOppNoIdStubData() {
		Opportunity opp = new Opportunity();
		opp.setName("Opp Name Test");
		opp.setSubClientBean(getSubClientStubData());
		return opp;
	}
	
	protected Opportunity getOppWithIdStubData() {
		Opportunity opp = getOppNoIdStubData();
		opp.setId(1);
		return opp;
	}
	
	protected Opportunity getOppWithPartnerStubData() {
		Opportunity opp = getOppWithIdStubData();
		Firm firm = getFirmStubData();
		
		OpportunityPartner partner = new OpportunityPartner();
		partner.setId(1);
		partner.setFirmBean(firm);
		partner.setOpportunity(opp);
		
		List<OpportunityPartner> partners = new ArrayList<OpportunityPartner>();
		partners.add(partner);
		opp.setOpportunityPartners(partners);
		
		return opp;
	}
	
	protected Opportunity getOppWithCompetitorStubData() {
		Opportunity opp = getOppWithIdStubData();
		Firm firm = getFirmStubData();
		
		OpportunityCompetitor competitor = new OpportunityCompetitor();
		competitor.setId(1);
		competitor.setFirmBean(firm);
		competitor.setOpportunity(opp);
		
		List<OpportunityCompetitor> competitors =
				new ArrayList<OpportunityCompetitor>();
		competitors.add(competitor);
		opp.setOpportunityCompetitors(competitors);
		
		return opp;
	}
	
	protected Opportunity getOppWithEventStubData() {
		Opportunity opp = getOppWithIdStubData();
		
		OpportunityEvent event = new OpportunityEvent();
		event.setId(1);
		event.setEvent("OpportunityEvent Event Test");
		event.setOpportunity(opp);
		event.setStatus(EventStatus.COMPLETE.getValue());
		
		List<OpportunityEvent> events = new ArrayList<OpportunityEvent>();
		events.add(event);
		opp.setOpportunityEvents(events);
		
		return opp;
	}
	
	protected Opportunity getOppWithPursueStubData() {
		Opportunity opp = getOppWithIdStubData();
		
		
		OpportunityNote oppNote = new OpportunityNote();
		
		oppNote.setId(1);
		oppNote.setCreateDate(new Date());
		oppNote.setTopic("General");
		oppNote.setNote("Testing");
		
		
		List<OpportunityNote> oppNotesList=new ArrayList<OpportunityNote>();
		  oppNotesList.add(oppNote);
		  opp.setOpportunityNotes(oppNotesList);
		  
		  	opp.setSupportingPastPerformance(true);
			opp.setPriceToWin(false);
			opp.setTeamAssembled(true);
			
			OpportunityStep oppStep = new OpportunityStep();
			oppStep.setDecision("Yes");
			
			List<OpportunityStep> oppStepList=new ArrayList<OpportunityStep>();
			oppStepList.add(oppStep);
			opp.setOpportunitySteps(oppStepList);
			
		return opp;
	}
	protected Opportunity getOppWithBidStubData() {
		Opportunity opp = getOppWithIdStubData();
		
			opp.setCrediblePrime(true);
			opp.setPriceToWin(false);
			opp.setTeamAssembled(false);
			opp.setBpBudgetAmount(1221);
			OpportunityStep oppStep = new OpportunityStep();
			oppStep.setNote("Testing");
			oppStep.setDecision("Yes");
			oppStep.setName("Bid Decision");
			oppStep.setReviewDate(new Date());
			
			List<OpportunityStep> oppStepList=new ArrayList<OpportunityStep>();
			oppStepList.add(oppStep);
			opp.setOpportunitySteps(oppStepList);
			
		return opp;
	}
}
