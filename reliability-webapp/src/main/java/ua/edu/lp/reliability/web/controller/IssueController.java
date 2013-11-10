package ua.edu.lp.reliability.web.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import ua.edu.lp.reliability.facade.dto.IssueDTO;
import ua.edu.lp.reliability.facade.dto.message.MessageDTO;
import ua.edu.lp.reliability.facade.issue.IssueFacade;
import ua.edu.lp.reliability.facade.report.IssueReportFacade;
import ua.edu.lp.reliability.model.report.issue.ReportIntervalType;
import ua.edu.lp.reliability.web.util.Constants;

@Controller
@RequestMapping(value = "/issue")
public class IssueController {

	@Resource(name = "issueFacade")
	private IssueFacade issueFacade;

	@Resource(name = "issueReportFacade")
	private IssueReportFacade issueReportFacade;

	@RequestMapping(value = "/all")
	public @ResponseBody
	List<IssueDTO> getAllIssue() {
		return issueFacade.getAll();
	}

	@RequestMapping(value = "/project/{projectId}")
	public @ResponseBody
	List<IssueDTO> getProjectIssue(@PathVariable(value = "projectId") Long projectId) {
		return issueFacade.getProjectIssue(projectId);
	}

	@RequestMapping(value = "/import/{projectId}")
	public @ResponseBody
	MessageDTO importIssueFromExcel(@PathVariable(value = "projectId") Long projectId, @RequestParam(value = "file") MultipartFile file) throws IOException {
		MessageDTO message = issueFacade.importIssueFromExcel(projectId, file.getInputStream());

		return message;
	}
	
	@RequestMapping(value = "/import/jira/{projectId}")
	public @ResponseBody
	MessageDTO importIssueFromJira(@PathVariable(value = "projectId") Long projectId) throws IOException {
		MessageDTO message = issueFacade.importIssueFromJira(projectId);

		return message;
	}
	
	@RequestMapping(value = "/report/project/{projectId}/{fileName}")
	public void generateIssueReportForProject(@PathVariable(value = "projectId") Long projectId, @PathVariable(value = "fileName") String fileName,
			HttpServletResponse response) throws IOException {
		List<IssueDTO> issues = issueFacade.getProjectIssue(projectId);

		response.setContentType(Constants.HTTP_CONTENT_TYPE_MS_EXCEL);

		issueReportFacade.generateIssueReport(issues, ReportIntervalType.MOUNTH, response.getOutputStream());
	}
}
