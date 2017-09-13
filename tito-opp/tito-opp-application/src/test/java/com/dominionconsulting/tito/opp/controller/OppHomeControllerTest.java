package com.dominionconsulting.tito.opp.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.dominionconsulting.tito.opp.AbstractTest;
import com.dominionconsulting.tito.opp.common.util.TitoExceptionHandler;
import com.dominionconsulting.tito.opp.dto.OppHomeDto;
import com.dominionconsulting.tito.opp.model.Opportunity;
import com.dominionconsulting.tito.opp.service.OppHomeService;

@WebAppConfiguration
@Transactional
public class OppHomeControllerTest extends AbstractTest {

	private MockMvc mvc;

	@Mock
	private OppHomeService service;

	@InjectMocks
	private OppHomeController controller;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.standaloneSetup(controller)
				.setControllerAdvice(new TitoExceptionHandler()).build();
	}

	@Test
	public void getAllOpps_ReceivesGetRequest_Returns200Status() throws Exception {
		List<OppHomeDto> list = getDtoListStubData();

		when(service.getAll()).thenReturn(list);

		String uri = "/opps/home/";

		MvcResult result = mvc.perform(get(uri)).andReturn();

		String content = result.getResponse().getContentAsString();
		int status = result.getResponse().getStatus();

		Assert.assertEquals("failure - expected HTTP status 200", 200, status);
		Assert.assertTrue("failure - expected HTTP response body to have a value",
				content.trim().length() > 0);
	}
	
	@Test
	public void getAllOpps_ReceivesGetRequest_Returns404Status() throws Exception {
		when(service.getAll()).thenThrow(new NoResultException());

		String uri = "/opps/home/";

		MvcResult result = mvc.perform(get(uri)).andReturn();

		int status = result.getResponse().getStatus();

		Assert.assertEquals("failure - expected HTTP status 404", 404, status);
	}
	
	private List<OppHomeDto> getDtoListStubData() {
		Opportunity opp = getOppWithIdStubData();
		List<OppHomeDto> list = new ArrayList<OppHomeDto>();
		list.add(new OppHomeDto(opp));
		return list;
	}
	
}
