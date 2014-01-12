package ua.edu.lp.reliability.web.controller;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ua.edu.lp.reliability.facade.dto.JiraSettingsDTO;
import ua.edu.lp.reliability.facade.dto.ProjectDTO;
import ua.edu.lp.reliability.facade.dto.SonarSettingsDTO;
import ua.edu.lp.reliability.facade.dto.message.MessageDTO;
import ua.edu.lp.reliability.facade.dto.message.MessageType;
import ua.edu.lp.reliability.facade.project.ProjectFacade;
import ua.edu.lp.reliability.mail.service.EmailService;
import ua.edu.lp.reliability.model.email.Email;
import ua.edu.lp.reliability.web.util.View;

@Controller
@RequestMapping("/project")
public class ProjectController extends BaseController {

	private static final Logger LOG = LoggerFactory.getLogger(ProjectController.class);

	@Resource(name = "projectFacade")
	private ProjectFacade projectFacade;

	@Resource(name = "emailService")
	private EmailService emailService;

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String getProjectPage() {
		return View.PROJECT_PAGE;
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public @ResponseBody
	List<ProjectDTO> getAllProjects() {
		return projectFacade.getAll();
	}

	@RequestMapping(value = "/info/{projectId}", method = RequestMethod.GET)
	public @ResponseBody
	ProjectDTO getProjectInfo(@PathVariable(value = "projectId") Long projectId) {
		return projectFacade.getProjectDetails(projectId);
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody
	MessageDTO createProject(@RequestBody ProjectDTO project) {
		LOG.info("Start process project create request:" + project.getName());
		project.setCreateDate(new Date());

		projectFacade.createProject(project);

		return new MessageDTO(MessageType.INFO, "Project created");
	}

	@RequestMapping(value = "/remove/{projectId}", method = RequestMethod.DELETE)
	public @ResponseBody
	MessageDTO removeProject(@PathVariable("projectId") Long projectId) {
		LOG.info("Start process request to remove project ID: " + projectId);

		projectFacade.removeProject(projectId);

		return new MessageDTO(MessageType.INFO, "Project removed");
	}

	@RequestMapping(value = "/settings/jira/{projectId}", method = RequestMethod.POST)
	public @ResponseBody
	MessageDTO saveJiraSettings(@PathVariable("projectId") Long projectId, @RequestBody JiraSettingsDTO jiraSettings) {
		LOG.info("Save Jira settings for project id: " + projectId);

		return new MessageDTO(MessageType.INFO, "Jira coneection settings saved");
	}

	@RequestMapping(value = "/settings/sonar/{projectId}", method = RequestMethod.POST)
	public @ResponseBody
	MessageDTO saveSonarSettings(@PathVariable("projectId") Long projectId, @RequestBody SonarSettingsDTO jiraSettings) {
		LOG.info("Save Sonar settings for project id: " + projectId);

		return new MessageDTO(MessageType.INFO, "Sonar coneection settings saved");
	}

	@RequestMapping(value = "/mail", method = RequestMethod.GET)
	public @ResponseBody
	MessageDTO sendEmail() {
		// TODO: TMP stuff, should be removed
		Email email = new Email();
		email.setSender("iurii@i.com");
		email.setRecipients(Collections.singletonList("pufik536@gmail.com"));
		email.setSubject("Reliability System: Issue import");
		email.setContent("Hello world!!!");

		emailService.send(email);

		return new MessageDTO();
	}
}
