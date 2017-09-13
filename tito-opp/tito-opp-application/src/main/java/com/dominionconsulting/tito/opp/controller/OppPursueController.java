package com.dominionconsulting.tito.opp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dominionconsulting.tito.opp.dto.OppNewDto;
import com.dominionconsulting.tito.opp.dto.OppPursueDto;
import com.dominionconsulting.tito.opp.service.OppNewService;
import com.dominionconsulting.tito.opp.service.OppPursueService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/opps/pursue")
@Api(basePath = "/opps/pursue", value = "TITO Opportunities", description = " Pursue Opportunities API Endpoints", produces = "application/json")
public class OppPursueController {
	
	@Autowired
	private OppPursueService oppPursueService;
	
	@ApiOperation(value = "Get Pursue Opportunity Info", notes = "Gets Info for Opportunity Pursue/ Do not Pursue decision")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<OppPursueDto> getInfo(@PathVariable Integer id) {
		OppPursueDto dto = oppPursueService.getOne(id);
		return new ResponseEntity<OppPursueDto>(dto, HttpStatus.OK);
	}

	@ApiOperation(value = "Create An Opportunity Pursue", notes = "Saves An Opportunity Pursue Decision")
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<OppPursueDto> createOpp(@RequestBody OppPursueDto dto) {
		Integer id = oppPursueService.create(dto);
		OppPursueDto createdDto = oppPursueService.getOne(id);
		return new ResponseEntity<OppPursueDto>(createdDto, HttpStatus.CREATED);
	}
	
}
