package ua.edu.lp.reliability.facade.project;

import java.util.List;

import ua.edu.lp.reliability.facade.dto.ProjectDTO;

public interface ProjectFacade {
	
	List<ProjectDTO> getAll();
	
	ProjectDTO getProjectDetails(Long projectId);
}
