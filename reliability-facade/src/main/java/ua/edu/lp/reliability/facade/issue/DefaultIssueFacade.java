package ua.edu.lp.reliability.facade.issue;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ua.edu.lp.reliability.facade.converter.Converter;
import ua.edu.lp.reliability.facade.dto.IssueDTO;
import ua.edu.lp.reliability.facade.dto.UserDTO;
import ua.edu.lp.reliability.facade.dto.message.MessageDTO;
import ua.edu.lp.reliability.facade.util.excel.importer.IssueExcelDataImporter;
import ua.edu.lp.reliability.model.annotation.spring.Facade;
import ua.edu.lp.reliability.model.issue.Issue;
import ua.edu.lp.reliability.model.project.Project;
import ua.edu.lp.reliability.model.user.User;
import ua.edu.lp.reliability.service.issue.IssueService;
import ua.edu.lp.reliability.service.project.ProjectService;

@Facade
@Transactional
public class DefaultIssueFacade implements IssueFacade {

	@Autowired
	private IssueService issueService;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private Converter<Issue, IssueDTO> issueConverter;

	@Autowired
	private Converter<User, UserDTO> userConverter;

	@Override
	public List<IssueDTO> getAll() {
		List<Issue> issues = issueService.getAll();

		return issueConverter.convertAll(issues);
	}

	@Override
	public List<IssueDTO> getProjectIssue(Long projectId) {
		List<IssueDTO> projectIssue = new ArrayList<>();
		
		Project project = projectService.getDetails(projectId);

		if (project != null) {
			for (Issue issue : project.getIssues()) {
				IssueDTO issueDTO = issueConverter.convert(issue);
				issueDTO.setReporter(userConverter.convert(issue.getReporter()));
				
				projectIssue.add(issueDTO);
			}
		}

		return projectIssue;
	}

	@Override
	public MessageDTO importIssueFromExcel(Long projectId, InputStream inputStream) {
		MessageDTO message = new MessageDTO();
		IssueExcelDataImporter importer = new IssueExcelDataImporter();

		List<Issue> issues = importer.parseIssue(inputStream, 0, 5);
		Project project = projectService.getDetails(projectId);

		for (Issue issue : issues) {
			project.addIssue(issue);
		}

		projectService.save(project);

		return message;
	}

}
