package edu.fra.uas.service.project;


import java.util.List;



import edu.fra.uas.model.project.Project;

//interface is used to handle later changes on methods easier and faster.Dependency Injection and IOC can be realized.
public interface ProjectService {

	
	List<Project>getAllProject();
	void saveProject(Project project);
	Project getProjectById(long id);
	void deleteProjectById(long id);
	List<Project> findByKeyword(String keyword);
	long count();
	List<Project> getProjectNameAndCount();
	String currentDate();
	
	
	
	
	
}
