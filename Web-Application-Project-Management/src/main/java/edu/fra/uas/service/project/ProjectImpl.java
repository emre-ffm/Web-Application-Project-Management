package edu.fra.uas.service.project;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import edu.fra.uas.model.project.Project;

import edu.fra.uas.repository.project.ProjectRepository;

@Component
public class ProjectImpl implements ProjectService {

	// import to use the ProjectRepository
	@Autowired
	private ProjectRepository projectRepository;

	// findall Projects in Db
	@Override
	public List<Project> getAllProject() {

		return projectRepository.findAll();
	}

	//save the Project in to DB
	@Override
	public void saveProject(Project project) {
		this.projectRepository.save(project);

	}
	//FindbyId if the Project is not found cause null send error message
	@Override
	public Project getProjectById(long id) {
		Optional<Project> optional = projectRepository.findById(id);
		Project project = null;
		if (optional.isPresent()) {
			project = optional.get();

		} else {
			throw new RuntimeException("Project not found for id:" + id);
		}
		return project;
	}
	//Delete Project by id
	@Override
	public void deleteProjectById(long id) {
		this.projectRepository.deleteById(id);
	}

	//search by keyword and use the findByKeyword(Query) method from Repository
	@Override
	public List<Project> findByKeyword(String keyword) {

		return projectRepository.findByKeyword(keyword);
	}
 
	//this was the try to count manually without Query
	@Override
	public long count() {

		long a = projectRepository.count();
		return a;
	}
 
	//Filter and get the Name and Count with Query
	@Override
	public List<Project> getProjectNameAndCount() {

		return projectRepository.getProjectNameAndCount();
	}

	//timestamp the current Date and select which Format should be used.
	@Override
	public String currentDate() {

		String timeStamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());

		return timeStamp;
	}

}
