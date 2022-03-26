package edu.fra.uas.project.dto;

//ProjectDataTransferObject object that carries data between processes.

public class ProjectDto {

	private String projectName;
	private int projectYear;
	private String projectAutorName;
	private String projectAutorLastName;
	private String date;

		//Constructor
	private ProjectDto(String projectName, int projectYear, String projectAutorName, String projectAutorLastName,
			String date) {
		super();
		this.projectName = projectName;
		this.projectYear = projectYear;
		this.projectAutorName = projectAutorName;
		this.projectAutorLastName = projectAutorLastName;
		this.date = date;
	}

	
	//GETTER SETTER
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public int getProjectYear() {
		return projectYear;
	}
	public void setProjectYear(int projectYear) {
		this.projectYear = projectYear;
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}


	
	
	
}
