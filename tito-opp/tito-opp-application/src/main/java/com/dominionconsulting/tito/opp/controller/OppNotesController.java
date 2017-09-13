package com.dominionconsulting.tito.opp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dominionconsulting.tito.opp.dto.OppNotesDto;
import com.dominionconsulting.tito.opp.service.OppNotesService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/opps/notes")
@Api(basePath = "/opps/notes", value = "TITO Opportunities", description = "Opportunities API Endpoints", produces = "application/json")
public class OppNotesController {
	
	@Autowired
	private OppNotesService oppNotesService;
	
	@ApiOperation(value = "Get Partner Notes", notes = "Retrieves Notes for a Partner on an Opportunity")
	@RequestMapping(value = "/partner/{partnerId}", method = RequestMethod.GET)
	public ResponseEntity<OppNotesDto> getPartnerNotes(@PathVariable Integer partnerId) {
		OppNotesDto dto = oppNotesService.getPartnerNotes(partnerId);
		return new ResponseEntity<OppNotesDto>(dto, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Get Competitor Notes", notes = "Retrieves Notes for a Competitor on an Opportunity")
	@RequestMapping(value = "/competitor/{competitorId}", method = RequestMethod.GET)
	public ResponseEntity<OppNotesDto> getCompetitorNotes(
			@PathVariable Integer competitorId) {
		OppNotesDto dto = oppNotesService.getCompetitorNotes(competitorId);
		return new ResponseEntity<OppNotesDto>(dto, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Get Event Notes", notes = "Retrieves Notes for an Event on an Opportunity")
	@RequestMapping(value = "/event/{eventId}", method = RequestMethod.GET)
	public ResponseEntity<OppNotesDto> getEventNotes(@PathVariable Integer eventId) {
		OppNotesDto dto = oppNotesService.getEventNotes(eventId);
		return new ResponseEntity<OppNotesDto>(dto, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Update Partner Notes", notes = "Updates Notes for a Partner on an Opportunity")
	@RequestMapping(value = "/partner/{partnerId}", method = RequestMethod.PUT)
	public ResponseEntity<OppNotesDto> updatePartnerNotes(
			@PathVariable Integer partnerId, @RequestBody OppNotesDto dto) {
		OppNotesDto returnedDto = oppNotesService.updatePartnerNotes(partnerId, dto);
		return new ResponseEntity<OppNotesDto>(returnedDto, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Update Competitor Notes", notes = "Updates Notes for a Competitor on an Opportunity")
	@RequestMapping(value = "/competitor/{competitorId}", method = RequestMethod.PUT)
	public ResponseEntity<OppNotesDto> updateCompetitorNotes(
			@PathVariable Integer competitorId, @RequestBody OppNotesDto dto) {
		OppNotesDto returnedDto =
				oppNotesService.updateCompetitorNotes(competitorId, dto);
		return new ResponseEntity<OppNotesDto>(returnedDto, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Update Event Notes", notes = "Updates Notes for an Event on an Opportunity")
	@RequestMapping(value = "/event/{eventId}", method = RequestMethod.PUT)
	public ResponseEntity<OppNotesDto> updateEventNotes(@PathVariable Integer eventId,
			@RequestBody OppNotesDto dto) {
		OppNotesDto returnedDto = oppNotesService.updateEventNotes(eventId, dto);
		return new ResponseEntity<OppNotesDto>(returnedDto, HttpStatus.OK);
	}

}
