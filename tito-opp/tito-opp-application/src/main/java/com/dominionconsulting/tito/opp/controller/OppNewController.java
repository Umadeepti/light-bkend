package com.dominionconsulting.tito.opp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dominionconsulting.tito.opp.dto.OppNewDto;
import com.dominionconsulting.tito.opp.service.OppNewService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/opps/new")
@Api(basePath = "/opps/new", value = "TITO Opportunities", description = "Opportunities API Endpoints", produces = "application/json")
public class OppNewController {
	
	@Autowired
	private OppNewService oppNewService;
	
	@ApiOperation(value = "Get Dropdown Info", notes = "Gets Info To Populate Dropdowns")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<OppNewDto> getInfo() {
		OppNewDto dto = oppNewService.getInfo();
		return new ResponseEntity<OppNewDto>(dto, HttpStatus.OK);
	}

	@ApiOperation(value = "Create An Opportunity", notes = "Saves A New Opportunity")
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<OppNewDto> createOpp(@RequestBody OppNewDto dto) {
		Integer id = oppNewService.create(dto);
		OppNewDto createdDto = oppNewService.getOne(id);
		return new ResponseEntity<OppNewDto>(createdDto, HttpStatus.CREATED);
	}
	
}
