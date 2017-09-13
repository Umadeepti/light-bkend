package com.dominionconsulting.tito.opp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dominionconsulting.tito.opp.dto.OppBidDto;
import com.dominionconsulting.tito.opp.service.OppBidService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/opps/bid")
@Api(basePath = "/opps/bid", value = "TITO Opportunities", description = " Bid Opportunities API Endpoints", produces = "application/json")
public class OppBidController {
	
	@Autowired
	private OppBidService oppBidService;
	
	@ApiOperation(value = "Get Bid Opportunity Info", notes = "Gets Info for Opportunity Bid/ Do not Bid decision")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<OppBidDto> getInfo(@PathVariable Integer id) {
		OppBidDto dto = oppBidService.getOne(id);
		return new ResponseEntity<OppBidDto>(dto, HttpStatus.OK);
	}

	@ApiOperation(value = "Create An Opportunity Bid", notes = "Saves An Opportunity Bid Decision")
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<OppBidDto> createOpp(@RequestBody OppBidDto dto) {
		Integer id = oppBidService.create(dto);
		OppBidDto createdDto = oppBidService.getOne(id);
		return new ResponseEntity<OppBidDto>(createdDto, HttpStatus.CREATED);
	}
}
