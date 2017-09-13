package com.dominionconsulting.tito.opp.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dominionconsulting.tito.opp.common.util.OppComparator;
import com.dominionconsulting.tito.opp.dto.OppHomeDto;
import com.dominionconsulting.tito.opp.model.Opportunity;
import com.dominionconsulting.tito.opp.repository.OpportunityRepository;

@Service
@Transactional
public class OppHomeService {

	@Autowired
	private OpportunityRepository oppRepository;
	
	public List<OppHomeDto> getAll() {
		List<OppHomeDto> list = new ArrayList<OppHomeDto>();
		Iterable<Opportunity> opps = oppRepository.findAll();
		if (opps == null) throw new NoResultException();
		for (Opportunity opp : opps) {
			list.add(new OppHomeDto(opp));
		}
		list.sort(new OppComparator());
		return list;
	}

}
