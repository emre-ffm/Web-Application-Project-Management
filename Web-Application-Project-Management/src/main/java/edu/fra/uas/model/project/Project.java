package edu.fra.uas.model.project;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//Model Attributes
@Entity // shows that is a Entity for Database
@Table(name = "Project") // Table name
public class Project {

	@Id // Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Count so that the id is unique
	private Long id;

	@Column(name = "Projekt_Name") // Columnname individually
	private String projectName;
	@Column(name = "Projekt_Jahr")
	private String projectYear;
	@Column(name = "Name")
	private String projectAutorName;
	@Column(name = "Nachname")
	private String projectAutorLastName;
	@Column(name="Datum")
	private String date;
	
	public String getDate() {
		
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

//Wanted to use this to count manually it has no function here,maybe can used later.
	private Long count;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProjectName() {
		return projectName;
	}
	
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectAutorName() {
		return projectAutorName;
	}
	public void setProjectAutorName(String projectAutorName) {
		this.projectAutorName = projectAutorName;
	}
	public String getProjectAutorLastName() {
		return projectAutorLastName;
	}
	public void setProjectAutorLastName(String projectAutorLastName) {
		this.projectAutorLastName = projectAutorLastName;
	}
	public String getProjectYear() {
		return projectYear;
	}
	public void setProjectYear(String projectJahr) {
		this.projectYear = projectJahr;
	}
	public Long getCount() {
		
		
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	

@Override 
	public String toString() {
		return "Project [projectName=" + projectName + ", projectYear=" + projectYear + "]";
	}
	
	
	
}
