package com.dominionconsulting.tito.opp.service;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dominionconsulting.tito.opp.dto.OppNotesDto;
import com.dominionconsulting.tito.opp.model.OpportunityCompetitor;
import com.dominionconsulting.tito.opp.model.OpportunityEvent;
import com.dominionconsulting.tito.opp.model.OpportunityPartner;
import com.dominionconsulting.tito.opp.repository.OpportunityCompetitorRepository;
import com.dominionconsulting.tito.opp.repository.OpportunityEventRepository;
import com.dominionconsulting.tito.opp.repository.OpportunityPartnerRepository;

@Service
@Transactional
public class OppNotesService {
	
	@Autowired
	private OpportunityPartnerRepository oppPartnerRepository;
	
	@Autowired
	private OpportunityCompetitorRepository oppCompetitorRepository;
	
	@Autowired
	private OpportunityEventRepository oppEventRepository;

	public OppNotesDto getPartnerNotes(Integer partnerId) {
		OpportunityPartner partner = oppPartnerRepository.findOne(partnerId);
		if (partner == null) throw new NoResultException();
		return new OppNotesDto(partner.getDescription());
	}

	public OppNotesDto getCompetitorNotes(Integer competitorId) {
		OpportunityCompetitor competitor = oppCompetitorRepository.findOne(competitorId);
		if (competitor == null) throw new NoResultException();
		return new OppNotesDto(competitor.getNote());
	}

	public OppNotesDto getEventNotes(Integer eventId) {
		OpportunityEvent event = oppEventRepository.findOne(eventId);
		if (event == null) throw new NoResultException();
		return new OppNotesDto(event.getNote());
	}

	public OppNotesDto updatePartnerNotes(Integer partnerId, OppNotesDto dto) {
		OpportunityPartner partner = oppPartnerRepository.findOne(partnerId);
		if (partner == null) throw new NoResultException();
		partner.setDescription(dto.getText());
		OpportunityPartner updatedPartner = oppPartnerRepository.save(partner);
		return new OppNotesDto(updatedPartner.getDescription());
	}

	public OppNotesDto updateCompetitorNotes(Integer competitorId, OppNotesDto dto) {
		OpportunityCompetitor competitor = oppCompetitorRepository.findOne(competitorId);
		if (competitor == null) throw new NoResultException();
		competitor.setNote(dto.getText());
		OpportunityCompetitor updatedCompetitor =
				oppCompetitorRepository.save(competitor);
		return new OppNotesDto(updatedCompetitor.getNote());
	}

	public OppNotesDto updateEventNotes(Integer eventId, OppNotesDto dto) {
		OpportunityEvent event = oppEventRepository.findOne(eventId);
		if (event == null) throw new NoResultException();
		event.setNote(dto.getText());
		OpportunityEvent updatedEvent = oppEventRepository.save(event);
		return new OppNotesDto(updatedEvent.getNote());
	}

}
