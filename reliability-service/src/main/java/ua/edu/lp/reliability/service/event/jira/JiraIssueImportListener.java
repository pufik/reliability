package ua.edu.lp.reliability.service.event.jira;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import ua.edu.lp.reliability.model.event.JiraIssueImportEvent;
import ua.edu.lp.reliability.model.issue.Issue;
import ua.edu.lp.reliability.service.event.BaseApplicationEventListener;
import ua.edu.lp.reliability.utils.Callback;

@Component("jiraIssueImportListener")
public class JiraIssueImportListener extends BaseApplicationEventListener<List<Issue>, JiraIssueImportEvent> {
	
	private static final Logger LOG = LoggerFactory.getLogger(JiraIssueImportListener.class);
	
	@Resource(name = "jiraIssueImportCallback")
	private Callback<List<Issue>> issueImportCallback;

	@Override
	public void handle(JiraIssueImportEvent event) {
		LOG.info("Handle Jira import event: " + event);
		
		issueImportCallback.execute(event.getSource());
	}
}
