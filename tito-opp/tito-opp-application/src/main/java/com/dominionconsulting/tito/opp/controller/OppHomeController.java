package com.dominionconsulting.tito.opp.controller;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dominionconsulting.tito.opp.common.util.LogMessage;
import com.dominionconsulting.tito.opp.dto.OppHomeDto;
import com.dominionconsulting.tito.opp.service.OppHomeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/opps/home")
@Api(basePath = "/opps/home", value = "TITO Opportunities", description = "Opportunities API Endpoints", produces = "application/json")
public class OppHomeController {

	@Autowired
	private OppHomeService oppHomeService;
	
	private static final Logger logger = LogManager.getLogger("OppHome");
	
	private static final String baseUri = "/opps/home";
		
	@ApiOperation(value = "Get List of Opportunities", notes = "Retrieves a List of All Opportunities in Database")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<OppHomeDto>> getAllOpps() throws Exception {
		String service = "getAllOpportunities";
		String action = "get";
		String uri = baseUri + "/";
		logger.info(LogMessage.invoked(service, action, uri));
		long start = System.currentTimeMillis();
		
		List<OppHomeDto> dtos =  oppHomeService.getAll();
		
		long end = System.currentTimeMillis();
		logger.info(LogMessage.completed(service, action, uri, end - start));
		
		return new ResponseEntity<List<OppHomeDto>>(dtos, HttpStatus.OK);
	}
	
}
