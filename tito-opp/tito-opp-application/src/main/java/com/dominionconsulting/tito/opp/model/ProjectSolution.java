package com.dominionconsulting.tito.opp.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the project_solutions database table.
 * 
 */
@Entity
@Table(name="project_solutions")
@NamedQuery(name="ProjectSolution.findAll", query="SELECT p FROM ProjectSolution p")
public class ProjectSolution implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Integer id;

	//bi-directional many-to-one association to Project
	@ManyToOne
	@JoinColumn(name="project", nullable=false)
	private Project projectBean;

	//bi-directional many-to-one association to Solution
	@ManyToOne
	@JoinColumn(name="solution", nullable=false)
	private Solution solutionBean;

	public ProjectSolution() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Project getProjectBean() {
		return this.projectBean;
	}

	public void setProjectBean(Project projectBean) {
		this.projectBean = projectBean;
	}

	public Solution getSolutionBean() {
		return this.solutionBean;
	}

	public void setSolutionBean(Solution solutionBean) {
		this.solutionBean = solutionBean;
	}

}