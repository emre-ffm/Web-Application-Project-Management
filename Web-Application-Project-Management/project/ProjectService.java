package edu.fra.uas.service.project;

import java.util.List;

import edu.fra.uas.model.project.Project;

public interface ProjectService {

	
	List<Project>getAllProject();
	void saveProject(Project project);
	Project getProjectById(long id);
	void deleteProjectById(long id);
}
