package ua.edu.lp.reliability.service.project;

import java.util.List;

import ua.edu.lp.reliability.model.project.Project;

public interface ProjectService {

	List<Project> getAll();

	Project getDetails(Long projectId);

	void create(Project project);

	void save(Project project);
	
	void remove(Project project);
}
