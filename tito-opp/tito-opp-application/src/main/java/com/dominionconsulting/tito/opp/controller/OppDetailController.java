package com.dominionconsulting.tito.opp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.dominionconsulting.tito.opp.dto.OppDetailDto;
import com.dominionconsulting.tito.opp.service.OppDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/opps/detail")
@Api(basePath = "/opps/detail", value = "TITO Opportunities", description = "Opportunities API Endpoints", produces = "application/json")
public class OppDetailController {

	@Autowired
	private OppDetailService oppDetailService;
	
	@ApiOperation(value = "Get An Opportunity Detail", notes = "Returns HttpStatus.NOT_FOUND if not Found")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<OppDetailDto> getOpp(@PathVariable Integer id) {
		OppDetailDto dto =  oppDetailService.getOne(id);
		return new ResponseEntity<OppDetailDto>(dto, HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "Update Opportunity", notes = "Updates An Opportunity")
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public ResponseEntity<OppDetailDto> updateOpp(@RequestBody OppDetailDto dto) {
		OppDetailDto returnedDto = oppDetailService.update(dto);
		return new ResponseEntity<OppDetailDto>(returnedDto, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Add An Opportunity Partner", notes = "Adds An Opportunity Partner")
	@RequestMapping(value = "/{oppId}/partner/{firmId}", method = RequestMethod.POST)
	public ResponseEntity<OppDetailDto> createOppPartner(@PathVariable Integer oppId,
			@PathVariable Integer firmId) {
		OppDetailDto returnedDto = oppDetailService.createOppPartner(oppId, firmId);
		return new ResponseEntity<OppDetailDto>(returnedDto, HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Add An Opportunity Competitor", notes = "Adds An Opportunity Competitor")
	@RequestMapping(value = "/{oppId}/competitor/{firmId}", method = RequestMethod.POST)
	public ResponseEntity<OppDetailDto> createOppCompetitor(@PathVariable Integer oppId,
			@PathVariable Integer firmId) {
		OppDetailDto returnedDto = oppDetailService.createOppCompetitor(oppId, firmId);
		return new ResponseEntity<OppDetailDto>(returnedDto, HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Delete An Opportunity Partner", notes = "Deletes An Opportunity Partner")
	@RequestMapping(value = "/{oppId}/partner/{partnerId}",
			method = RequestMethod.DELETE)
	public ResponseEntity<OppDetailDto> deleteOppPartner(@PathVariable Integer oppId,
			@PathVariable Integer partnerId) {
		OppDetailDto returnedDto = oppDetailService.deleteOppPartner(oppId, partnerId);
		return new ResponseEntity<OppDetailDto>(returnedDto, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Delete An Opportunity Competitor", notes = "Deletes An Opportunity Competitor")
	@RequestMapping(value = "/{oppId}/competitor/{competitorId}",
			method = RequestMethod.DELETE)
	public ResponseEntity<OppDetailDto> deleteOppCompetitor(@PathVariable Integer oppId,
			@PathVariable Integer competitorId) {
		OppDetailDto returnedDto =
				oppDetailService.deleteOppCompetitor(oppId, competitorId);
		return new ResponseEntity<OppDetailDto>(returnedDto, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Add An Opportunity Event", notes = "Adds An Opportunity Event")
	@RequestMapping(value = "/{oppId}/event/", method = RequestMethod.POST)
	public ResponseEntity<OppDetailDto> createOppEvent(@PathVariable Integer oppId) {
		OppDetailDto returnedDto = oppDetailService.createOppEvent(oppId);
		return new ResponseEntity<OppDetailDto>(returnedDto, HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Delete An Opportunity Event", notes = "Deletes An Opportunity Event")
	@RequestMapping(value = "/{oppId}/event/{eventId}", method = RequestMethod.DELETE)
	public ResponseEntity<OppDetailDto> deleteOppEvent(@PathVariable Integer oppId,
			@PathVariable Integer eventId) {
		OppDetailDto returnedDto = oppDetailService.deleteOppEvent(oppId, eventId);
		return new ResponseEntity<OppDetailDto>(returnedDto, HttpStatus.OK);
	}
	
}
