package ua.edu.lp.reliability.facade.issue;

import java.io.InputStream;
import java.util.List;

import ua.edu.lp.reliability.facade.dto.IssueDTO;
import ua.edu.lp.reliability.facade.dto.message.MessageDTO;

public interface IssueFacade {

	List<IssueDTO> getAll();

	List<IssueDTO> getProjectIssue(Long projectId);

	MessageDTO importIssueFromExcel(Long projectId, InputStream inputStream);
	
	MessageDTO importIssueFromJira(Long projectId);
}
