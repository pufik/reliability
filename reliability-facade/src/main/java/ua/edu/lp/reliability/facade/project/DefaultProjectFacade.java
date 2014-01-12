package ua.edu.lp.reliability.facade.project;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import ua.edu.lp.reliability.facade.converter.Converter;
import ua.edu.lp.reliability.facade.dto.JiraSettingsDTO;
import ua.edu.lp.reliability.facade.dto.ProjectDTO;
import ua.edu.lp.reliability.facade.dto.SonarSettingsDTO;
import ua.edu.lp.reliability.model.annotation.spring.Facade;
import ua.edu.lp.reliability.model.jira.JiraSettings;
import ua.edu.lp.reliability.model.metric.SonarSettings;
import ua.edu.lp.reliability.model.project.Project;
import ua.edu.lp.reliability.service.project.ProjectService;

@Facade("projectFacade")
@Transactional
public class DefaultProjectFacade implements ProjectFacade {

	@Resource(name = "projectService")
	private ProjectService projectService;

	@Resource(name = "projectConverter")
	private Converter<Project, ProjectDTO> projectConverter;

	@Resource(name = "projectDTOConverter")
	private Converter<ProjectDTO, Project> projectDTOConverter;

	@Resource(name = "jiraSettingsConverter")
	private Converter<JiraSettings, JiraSettingsDTO> jiraSettingsConverter;

	@Resource(name = "sonarSettingsConverter")
	private Converter<SonarSettings, SonarSettingsDTO> sonarSettingsConverter;

	@Override
	public List<ProjectDTO> getAll() {
		List<Project> projects = projectService.getAll();

		return projectConverter.convertAll(projects);
	}

	@Override
	public ProjectDTO getProjectDetails(Long projectId) {
		Project project = projectService.getDetails(projectId);
		ProjectDTO projectDto = projectConverter.convert(project);

		if (project.getJiraSettings() != null) {
			projectDto.setJiraSettings(jiraSettingsConverter.convert(project.getJiraSettings()));
		}

		if (project.getSettings() != null) {
			projectDto.setSonarSettings(sonarSettingsConverter.convert(project.getSettings()));
		}

		return projectDto;
	}

	@Override
	public void createProject(ProjectDTO projectDto) {
		Project project = projectDTOConverter.convert(projectDto);
		projectService.create(project);
	}

	@Override
	public void removeProject(Long projectId) {
		Project project = projectService.getDetails(projectId);
		projectService.remove(project);
	}
}
