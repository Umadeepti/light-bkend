package com.dominionconsulting.tito.opp.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.dominionconsulting.tito.opp.AbstractTest;
import com.dominionconsulting.tito.opp.dto.OppHomeDto;
import com.dominionconsulting.tito.opp.model.Opportunity;
import com.dominionconsulting.tito.opp.repository.OpportunityRepository;

public class OppHomeServiceTest extends AbstractTest {
	
	@Mock
	private OpportunityRepository repository;
	
	@InjectMocks
	private OppHomeService service;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getAll_ReturnsList() {
		Iterable<Opportunity> opps = getOppListStubData();
		
		when(repository.findAll()).thenReturn(opps);
		
		List<OppHomeDto> list = service.getAll();
		
		Assert.assertNotNull("failure - expected not null", list);
		Assert.assertEquals("failure - expected size", 1, list.size());
	}
	
	@Test
	public void getAll_ThrowsNoResultException() {
		Exception e = null;
		
		when(repository.findAll()).thenReturn(null);
		
		try {
			service.getAll();
		} catch (Exception ex) {
			e = ex;
		}
		
		Assert.assertNotNull("failure - expected exception", e);
		Assert.assertTrue("failure - expects NoResultException",
				e instanceof NoResultException);
	}

	private List<Opportunity> getOppListStubData() {
		Opportunity opp = getOppWithIdStubData();
		List<Opportunity> list = new ArrayList<Opportunity>();
		list.add(opp);
		return list;
	}

}
