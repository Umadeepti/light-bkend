package com.dominionconsulting.tito.opp.service;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dominionconsulting.tito.opp.common.util.TitoException;
import com.dominionconsulting.tito.opp.dto.OppPursueDto;
import com.dominionconsulting.tito.opp.model.Opportunity;
import com.dominionconsulting.tito.opp.model.OpportunityStep;
import com.dominionconsulting.tito.opp.repository.OpportunityRepository;
import com.dominionconsulting.tito.opp.repository.OpportunityStepRepository;


@Service
@Transactional
public class OppPursueService {

	@Autowired
	private OpportunityRepository oppRepository;

	@Autowired
	private OpportunityStepRepository oppStepRepository;


	public void getInfo() {}

	public Integer create(OppPursueDto dto) {
		if(dto.getId()==null){
			throw new TitoException("Cannot Create Opportunity Pursue");
		}
		Opportunity opp = new Opportunity();
		
		opp = oppRepository.findOne((Integer) dto.getId());
		opp = ((OppPursueDto) dto).toPursue(opp);
		 
		if(opp.getOpportunitySteps()!=null){
			for(OpportunityStep os:opp.getOpportunitySteps()){
				oppStepRepository.save(os);
			}

		}
		
		Opportunity createdOpp = oppRepository.save(opp);
		if (createdOpp == null) throw new TitoException("Cannot Create Opportunity Pursue Decision");
		
		
		return createdOpp.getId();
	}

	public OppPursueDto getOne(Integer id) {
		Opportunity opp = oppRepository.findOne(id);
		if (opp == null) throw new NoResultException();

		List<OpportunityStep> oppStepList=opp.getOpportunitySteps();
		if(oppStepList!= null){
			oppStepList=oppStepRepository.findByOpportunityId(id);
		}
		opp.setOpportunitySteps(oppStepList);
		return new OppPursueDto(opp);
	}
}
