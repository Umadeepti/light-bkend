package com.dominionconsulting.tito.opp.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dominionconsulting.tito.opp.model.Opportunity;
import com.dominionconsulting.tito.opp.model.OpportunityNote;
import com.dominionconsulting.tito.opp.model.OpportunitySolution;

public class OppDetailsDto  {
	
	private String description;

	private Integer contractValueTotal;

	private Integer contractValueExpected;
	
	private Integer annualValue;
	
	private Boolean evenDistribution;
	
	private OppNoteDto note;
	
	private Date projectStartDate;

	private Date projectEndDate;
	
	private List<OppSolutionDto> existingOppSolutions;
	
	private List<OppSolutionDto> addedOppSolutions;
	
	private List<OppSolutionDto> removedOppSolutions;
	
	private List<SolutionDto> solutions;
	
	private List<SolutionDto> selectedSolutions;

	public OppDetailsDto() {}
	
	public OppDetailsDto(Opportunity opp) {
		description = opp.getDescription();
		contractValueTotal = opp.getContractValueTotal();
		contractValueExpected = opp.getContractValueExpected();
		annualValue = opp.getContractAnnualValue();
		evenDistribution = opp.getContractEvenDistribution();
		
		List<OpportunityNote> oppNotes = opp.getOpportunityNotes();
		if (oppNotes != null && !oppNotes.isEmpty()) {
			note = new OppNoteDto(oppNotes.get(0));
		}
		
		projectStartDate = opp.getProjectStartDate();
		projectEndDate = opp.getProjectEndDate();
		
		List<OpportunitySolution> opportunitySolutions = opp.getOpportunitySolutions();
		if (opportunitySolutions != null) {
			selectedSolutions = new ArrayList<SolutionDto>();
			for (OpportunitySolution solution : opportunitySolutions) {
				selectedSolutions.add(new SolutionDto(solution.getSolutionBean()));
			}
		}
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getContractValueTotal() {
		return contractValueTotal;
	}

	public void setContractValueTotal(Integer contractValueTotal) {
		this.contractValueTotal = contractValueTotal;
	}

	public Integer getContractValueExpected() {
		return contractValueExpected;
	}

	public void setContractValueExpected(Integer contractValueExpected) {
		this.contractValueExpected = contractValueExpected;
	}

	public Integer getAnnualValue() {
		return annualValue;
	}

	public void setAnnualValue(Integer annualValue) {
		this.annualValue = annualValue;
	}

	public Boolean getEvenDistribution() {
		return evenDistribution;
	}

	public void setEvenDistribution(Boolean evenDistribution) {
		this.evenDistribution = evenDistribution;
	}

	public OppNoteDto getNote() {
		return note;
	}

	public void setNote(OppNoteDto note) {
		this.note = note;
	}

	public Date getProjectStartDate() {
		return projectStartDate;
	}

	public void setProjectStartDate(Date projectStartDate) {
		this.projectStartDate = projectStartDate;
	}

	public Date getProjectEndDate() {
		return projectEndDate;
	}

	public void setProjectEndDate(Date projectEndDate) {
		this.projectEndDate = projectEndDate;
	}

	public List<OppSolutionDto> getExistingOppSolutions() {
		return existingOppSolutions;
	}

	public void setExistingOppSolutions(List<OppSolutionDto> existingOppSolutions) {
		this.existingOppSolutions = existingOppSolutions;
	}

	public List<OppSolutionDto> getAddedOppSolutions() {
		return addedOppSolutions;
	}

	public void setAddedOppSolutions(List<OppSolutionDto> addedOppSolutions) {
		this.addedOppSolutions = addedOppSolutions;
	}

	public List<OppSolutionDto> getRemovedOppSolutions() {
		return removedOppSolutions;
	}

	public void setRemovedOppSolutions(List<OppSolutionDto> removedOppSolutions) {
		this.removedOppSolutions = removedOppSolutions;
	}

	public List<SolutionDto> getSolutions() {
		return solutions;
	}

	public void setSolutions(List<SolutionDto> solutions) {
		this.solutions = solutions;
	}

	public List<SolutionDto> getSelectedSolutions() {
		return selectedSolutions;
	}

	public void setSelectedSolutions(List<SolutionDto> selectedSolutions) {
		this.selectedSolutions = selectedSolutions;
	}

	public void toOpportunity(Opportunity opp) {
		opp.setDescription(description);
		opp.setContractValueTotal(contractValueTotal);
		opp.setContractValueExpected(contractValueExpected);
		opp.setContractAnnualValue(annualValue);
		opp.setContractEvenDistribution(evenDistribution);
		opp.setProjectStartDate(projectStartDate);
		opp.setProjectEndDate(projectEndDate);
	}
}
