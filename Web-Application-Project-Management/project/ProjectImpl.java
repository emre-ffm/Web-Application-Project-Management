package edu.fra.uas.service.project;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.fra.uas.model.project.Project;
import edu.fra.uas.repository.project.ProjectRepository;

@Component
public class ProjectImpl implements ProjectService {
	@Autowired
	private ProjectRepository projectRepository;

	@Override
	public List<Project> getAllProject() {

		return projectRepository.findAll();
	}

	@Override
	public void saveProject(Project project) {
		this.projectRepository.save(project);

	}

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

	@Override
	public void deleteProjectById(long id) {
	this.projectRepository.deleteById(id);
	}

	
	

}
