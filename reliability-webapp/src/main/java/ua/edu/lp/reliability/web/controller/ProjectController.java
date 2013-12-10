package ua.edu.lp.reliability.web.controller;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ua.edu.lp.reliability.facade.dto.ProjectDTO;
import ua.edu.lp.reliability.facade.dto.message.MessageDTO;
import ua.edu.lp.reliability.facade.project.ProjectFacade;
import ua.edu.lp.reliability.mail.service.EmailService;
import ua.edu.lp.reliability.model.email.Email;
import ua.edu.lp.reliability.web.util.View;

@Controller
@RequestMapping("/project")
public class ProjectController {

	@Autowired
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
	
	@RequestMapping(value = "/mail", method = RequestMethod.GET)
	public MessageDTO sendEmail(){
		Email email = new Email();
		email.setSender("Iurii: <iurii@i.com>");
		email.setRecipients(Collections.singletonList("pufik536@gmail.com"));
		email.setSubject("Reliability System: Issue import");
		email.setContent("Hello world!!!");
		
		emailService.send(email);
		
		return new MessageDTO();
	}
}
