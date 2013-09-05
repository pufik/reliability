package ua.edu.lp.reliability.facade.project;

import java.util.List;

import javax.annotation.Resource;

import ua.edu.lp.reliability.facade.converter.Converter;
import ua.edu.lp.reliability.facade.dto.ProjectDTO;
import ua.edu.lp.reliability.model.annotation.spring.Facade;
import ua.edu.lp.reliability.model.project.Project;
import ua.edu.lp.reliability.service.project.ProjectService;

@Facade("projectFacade")
public class DefaultProjectFacade implements ProjectFacade {

	@Resource(name = "projectService")
	private ProjectService projectService;

	@Resource(name = "projectConverter")
	private Converter<Project, ProjectDTO> projectConverter;

	@Override
	public List<ProjectDTO> getAll() {
		List<Project> projects = projectService.getAll();

		return projectConverter.convertAll(projects);
	}

	@Override
	public ProjectDTO getProjectDetails(Long projectId) {
		Project project = projectService.getDetails(projectId);

		return projectConverter.convert(project);
	}
}
